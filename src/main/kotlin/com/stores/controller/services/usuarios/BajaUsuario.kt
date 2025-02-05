package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.ExtCamaDelPerro
import com.stores.entities.ExtLunaVet
import com.stores.entities.ExtSafariVet
import com.stores.entities.Usuario
import com.stores.repository.ClienteRepository
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import com.stores.request.RequestConsultaUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class BajaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaUsuario(request: RequestConsultaUsuario?): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de eliminacion de usuario: ${request!!}")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val extentidoLunaConsultado: Optional<ExtLunaVet>?
            val extentidoSafariConsultado: Optional<ExtSafariVet>?
            val extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(request.usuario!!)
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)


            when (request.aplicacion) {
                Aplicaciones().lunaVet -> {
                    tracer.duration(Servicios().registroExtLunaVet, fun() {
                        extLunaVetRepository.deleteById(usuarioConsultado.get().usuario!!)
                    })
                }

                Aplicaciones().safariVet -> {
                    tracer.duration(Servicios().registroExtSafariVet, fun() {
                        extSafariVetRepository.deleteById(usuarioConsultado.get().usuario!!)
                    })
                }

                Aplicaciones().laCamaDelPerro -> {
                    tracer.duration(Servicios().registroExtCamaDelPerro, fun() {
                        extCamaDelPerroRepository.deleteById(usuarioConsultado.get().usuario!!)
                    })
                }
            }

            extentidoLunaConsultado = tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                return extLunaVetRepository.findById(usuarioConsultado.get().usuario!!)
            })
            extentidoSafariConsultado =
                tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                    return extSafariVetRepository.findById(usuarioConsultado.get().usuario!!)
                })
            extentidoCamaConsultado =
                tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                    return extCamaDelPerroRepository.findById(usuarioConsultado.get().usuario!!)
                })

            if (extentidoLunaConsultado == null && extentidoSafariConsultado == null && extentidoCamaConsultado == null) {
                tracer.duration(Servicios().eliminaUsuario, fun() {
                    return clienteRepository.deleteById(usuarioConsultado.get().usuario!!)
                })
            }

            return buildresponse(respuesta = "Eliminado")
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}