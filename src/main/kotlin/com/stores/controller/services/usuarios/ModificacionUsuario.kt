package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.*
import com.stores.repository.ClienteRepository
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import com.stores.request.RequestActualizacionContrasenna
import com.stores.request.RequestActualizacionUsuario
import com.stores.responses.Extendidos
import com.stores.responses.ExtendidosRespuesta
import com.stores.responses.ResponseUsuaro
import com.stores.responses.preparaResponseUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ModificacionUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionUsuario(request: RequestActualizacionUsuario): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de actualizacion de usuario: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(encrypt(request.usuario))
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            val cliente = usuarioConsultado.get()

            val extentidoLunaConsultado: Optional<ExtLunaVet>?
            val extentidoSafariConsultado: Optional<ExtSafariVet>?
            val extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            var lunaVet: ExtendidosRespuesta? = null
            var safariVet: ExtendidosRespuesta? = null
            var camaDelPerro: ExtendidosRespuesta? = null


            if (decrypt(cliente.nombre) != request.nombre) cliente.nombre = encrypt(request.nombre)
            if (decrypt(cliente.apellidoPaterno) != request.apellidoPaterno) cliente.apellidoPaterno =
                encrypt(request.apellidoPaterno)
            if (decrypt(cliente.apellidoMaterno) != request.apellidoMaterno) cliente.apellidoMaterno =
                encrypt(request.apellidoMaterno)
            if (decrypt(cliente.genero) != request.genero) cliente.genero = encrypt(request.genero)
            if (decrypt(cliente.fechaNacimiento) != request.fechaNacimiento) cliente.fechaNacimiento =
                encrypt(request.fechaNacimiento)
            if (decrypt(cliente.correo?.direccion) != request.correo) cliente.correo?.direccion =
                encrypt(request.correo)
            if (decrypt(cliente.telefono.telefono) != request.telefono) cliente.telefono.telefono =
                encrypt(request.telefono)
            if (cliente.notificaciones != request.notificaciones) cliente.notificaciones = request.notificaciones
            cliente.fechaModificacion = Date()

            tracer.duration(Servicios().actualizacionUsuario, fun() {
                clienteRepository.save(cliente)
            })

            when (request.aplicacion) {
                Aplicaciones.LunaVet.name -> {
                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(cliente.usuario)
                        })

                    if (extentidoLunaConsultado.isPresent) {
                        val extluna = extentidoLunaConsultado.get()
                        extluna.usernameLuna = encrypt(request.nickname)
                        extluna.rol = request.rol
                        lunaVet = ExtendidosRespuesta(
                            decrypt(extluna.usernameLuna), extluna.rol
                        )

                        tracer.duration(Servicios().actualizacionExtLunaVet, fun() {
                            extLunaVetRepository.save(extluna)
                        })
                    }
                }

                Aplicaciones.SafariVet.name -> {
                    extentidoSafariConsultado =
                        tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                            return extSafariVetRepository.findById(cliente.usuario)
                        })

                    if (extentidoSafariConsultado.isPresent) {
                        val extsafary = extentidoSafariConsultado.get()
                        extsafary.usernameSafary = encrypt(request.nickname)
                        extsafary.rol = request.rol
                        safariVet = ExtendidosRespuesta(
                            decrypt(extsafary.usernameSafary), extsafary.rol
                        )

                        tracer.duration(Servicios().actualizacionExtSafariVet, fun() {
                            extSafariVetRepository.save(extsafary)
                        })
                    }
                }

                Aplicaciones.LaCamaDelPerro.name -> {
                    extentidoCamaConsultado =
                        tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                            return extCamaDelPerroRepository.findById(cliente.usuario)
                        })

                    if (extentidoCamaConsultado.isPresent) {
                        val extsaCamaperro = extentidoCamaConsultado.get()
                        extsaCamaperro.usernameCamaPerro = encrypt(request.nickname)
                        extsaCamaperro.rol = request.rol
                        camaDelPerro = ExtendidosRespuesta(
                            decrypt(extsaCamaperro.usernameCamaPerro), extsaCamaperro.rol
                        )

                        tracer.duration(Servicios().actualizacionExtCamaDelPerro, fun() {
                            extCamaDelPerroRepository.save(extsaCamaperro)
                        })
                    }
                }
            }

            val respuesta: ResponseUsuaro = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseUsuaro {
                return preparaResponseUsuario(
                    usuarioConsultado.get(), Extendidos(lunaVet, safariVet, camaDelPerro)
                )
            })

            logs.info("informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun actualizaContrasenna(request: RequestActualizacionContrasenna): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de actualizacion de contraseña: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(encrypt(request.usuario))
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            val cliente = usuarioConsultado.get()
            val extentidoLunaConsultado: Optional<ExtLunaVet>?
            val extentidoSafariConsultado: Optional<ExtSafariVet>?
            val extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            when (request.aplicacion) {
                Aplicaciones.LunaVet.name -> {
                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(cliente.usuario)
                        })

                    if (extentidoLunaConsultado.isPresent) {
                        val extluna = extentidoLunaConsultado.get()
                        extluna.passwordLuna = encrypt(request.contrasennaNueva)

                        tracer.duration(Servicios().actualizacionExtLunaVet, fun() {
                            extLunaVetRepository.save(extluna)
                        })
                    }
                }

                Aplicaciones.SafariVet.name -> {
                    extentidoSafariConsultado =
                        tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                            return extSafariVetRepository.findById(cliente.usuario)
                        })

                    if (extentidoSafariConsultado.isPresent) {
                        val extsafary = extentidoSafariConsultado.get()
                        extsafary.passwordSafary = encrypt(request.contrasennaNueva)

                        tracer.duration(Servicios().actualizacionExtSafariVet, fun() {
                            extSafariVetRepository.save(extsafary)
                        })
                    }
                }

                Aplicaciones.LaCamaDelPerro.name -> {
                    extentidoCamaConsultado =
                        tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                            return extCamaDelPerroRepository.findById(cliente.usuario)
                        })

                    if (extentidoCamaConsultado.isPresent) {
                        val extsaCamaperro = extentidoCamaConsultado.get()
                        extsaCamaperro.passwordCamaPerro = encrypt(request.contrasennaNueva)

                        tracer.duration(Servicios().actualizacionExtCamaDelPerro, fun() {
                            extCamaDelPerroRepository.save(extsaCamaperro)
                        })
                    }
                }
            }

            return buildresponse(respuesta = "Actualizado")
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}