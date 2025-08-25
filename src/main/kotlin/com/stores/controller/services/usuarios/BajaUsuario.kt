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
import kotlin.jvm.optionals.getOrNull

@Service
class BajaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaUsuario(request: RequestConsultaUsuario): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de eliminacion de usuario: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val extentidoLunaConsultado: ExtLunaVet?
            val extentidoSafariConsultado: ExtSafariVet?
            val extentidoCamaConsultado: ExtCamaDelPerro?

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(cifrado(request.usuario))
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)


            when (request.aplicacion) {
                Aplicaciones.LunaVet.name -> {
                    tracer.duration(Servicios().eliminaExtLunaVet, fun() {
                        extLunaVetRepository.deleteById(usuarioConsultado.get().usuario)
                    })
                }

                Aplicaciones.SafariVet.name -> {
                    tracer.duration(Servicios().eliminaExtSafariVet, fun() {
                        extSafariVetRepository.deleteById(usuarioConsultado.get().usuario)
                    })
                }

                Aplicaciones.LaCamaDelPerro.name -> {
                    tracer.duration(Servicios().eliminaExtCamaDelPerro, fun() {
                        extCamaDelPerroRepository.deleteById(usuarioConsultado.get().usuario)
                    })
                }
            }

            extentidoLunaConsultado = tracer.duration(Servicios().consultaExtLunaVet, fun(): ExtLunaVet? {
                return extLunaVetRepository.findById(usuarioConsultado.get().usuario).getOrNull()
            })
            extentidoSafariConsultado = tracer.duration(Servicios().consultaExtSafariVet, fun(): ExtSafariVet? {
                return extSafariVetRepository.findById(usuarioConsultado.get().usuario).getOrNull()
            })
            extentidoCamaConsultado = tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): ExtCamaDelPerro? {
                return extCamaDelPerroRepository.findById(usuarioConsultado.get().usuario).getOrNull()
            })

            if (extentidoLunaConsultado == null && extentidoSafariConsultado == null && extentidoCamaConsultado == null) {
                tracer.duration(Servicios().eliminaUsuario, fun() {
                    return clienteRepository.deleteById(usuarioConsultado.get().usuario)
                })
            }

            logs.info("Informacion a regresar: Usuario eliminado")
            return buildresponse(respuesta = "usuario eliminado")
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}