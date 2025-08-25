package com.stores.controller.services.opiniones

import com.stores.config.*
import com.stores.entities.Opiniones
import com.stores.repository.OpinionesRepository
import com.stores.request.RequestOpiniones
import com.stores.request.RequestRegistroOpinion
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class Opiniones @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val opinionesRepository: OpinionesRepository,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registrarOpinion(request: RequestRegistroOpinion): ResponseEntity<Any> {
        var registroNuevo = false
        val id = cifrado(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de opiniones: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            registroNuevo = true
            registraUsuario(id, request)
            val opinionConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Opiniones> {
                return opinionesRepository.findById(id)
            })

            val respuesta = tracer.duration(Servicios().preparacionRespuesta, fun(): Opiniones {
                return opinionConsultada.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if (registroNuevo) {
                anulaRegistro(id)
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun cerrarOpinion(request: RequestOpiniones): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de cerrado de opiniones: $request")

            val opiicionConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Opiniones> {
                return opinionesRepository.findById(cifrado(request.opinion))
            })

            if (!opiicionConsultada.isPresent) return buildresponse(error = CatalogoResponses.OPINION_INEXISTENTE)

            val opinion: Opiniones = opiicionConsultada.get()
            opinion.estado = true
            opinion.fechaModificacion = Date()

            tracer.duration(Servicios().actualizacionMascota, fun() {
                opinionesRepository.save(opinion)
            })

            logs.info("Informacion a regresar: Opinion cerrada")
            return buildresponse(respuesta = "Opinion cerrada")
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun consultarOpinion(request: RequestOpiniones): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de consulta de opiniones: $request")

            val opiicionConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Opiniones> {
                return opinionesRepository.findById(cifrado(request.opinion))
            })

            if (!opiicionConsultada.isPresent) return buildresponse(error = CatalogoResponses.OPINION_INEXISTENTE)

            val respuesta = tracer.duration(Servicios().preparacionRespuesta, fun(): Opiniones {
                return opiicionConsultada.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun listarOpiniones(): ResponseEntity<Any> {
        try {
            val productosConsultados = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): List<Opiniones> {
                return opinionesRepository.findAll()
            })

            return buildresponse(respuesta =  productosConsultados)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun registraUsuario(id: String, request: RequestRegistroOpinion) {
        tracer.duration(Servicios().registroUsuario, fun() {
            opinionesRepository.save(
                Opiniones(
                    id,
                    cifrado(request.usuario),
                    cifrado(request.comentario),
                    cifrado(request.aplicacion),
                    request.calificacion,
                    false,
                    Date(),
                    Date()
                )
            )
        })
    }

    fun anulaRegistro(id: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            opinionesRepository.deleteById(id)
        })
    }
}