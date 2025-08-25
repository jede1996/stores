package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.*
import com.stores.repository.ClienteRepository
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import com.stores.request.RequestsRegistroUsuario
import com.stores.responses.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.Date
import java.util.Optional
import java.util.UUID

@Service
class RegistroUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository,
    private val passwordEncoder: PasswordEncoder
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(request: RequestsRegistroUsuario): ResponseEntity<Respuesta> {
        var registroNuevo = false
        val idUser = cifrado(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de usuarios: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            if (!validaRoles(request.rol.name)) return buildresponse(error = CatalogoResponses.ROL_INVALIDO)

            var extentidoLunaConsultado: Optional<ExtLunaVet>?
            var extentidoSafariConsultado: Optional<ExtSafariVet>?
            var extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            var lunaVet: ExtendidosRespuesta? = null
            var safariVet: ExtendidosRespuesta? = null
            var camaDelPerro: ExtendidosRespuesta? = null

            var usuarioConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): Optional<Usuario> {
                return clienteRepository.findByBatosBasicos(
                    cifrado(request.nombre),
                    cifrado(request.apellidoPaterno),
                    cifrado(request.apellidoMaterno),
                    cifrado(request.fechaNacimiento)
                )
            })

            if (!usuarioConsultado.isPresent) {
                registroNuevo = true
                registraUsuario(idUser, request)
                usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                    return clienteRepository.findById(idUser)
                })
            }

            when (request.aplicacion) {
                Aplicaciones.LunaVet.name -> {
                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoLunaConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtLunaVet, fun() {
                            extLunaVetRepository.save(
                                ExtLunaVet(
                                    usuarioConsultado.get().usuario,
                                    cifrado(request.nickname),
                                    passwordEncoder.encode( "999999"),
                                    request.rol,
                                    Date(),
                                    Date()
                                )
                            )
                        })
                    }

                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(idUser)
                        })

                    if (extentidoLunaConsultado.isPresent) {
                        lunaVet = ExtendidosRespuesta(
                            cifrado(extentidoLunaConsultado.get().usernameLuna, false), extentidoLunaConsultado.get().rol
                        )
                    }
                }

                Aplicaciones.SafariVet.name -> {
                    extentidoSafariConsultado =
                        tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                            return extSafariVetRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoSafariConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtSafariVet, fun() {
                            extSafariVetRepository.save(
                                ExtSafariVet(
                                    usuarioConsultado.get().usuario,
                                    cifrado(request.nickname),
                                    passwordEncoder.encode( "999999"),
                                    request.rol,
                                    Date(),
                                    Date()
                                )
                            )
                        })
                    }
                    extentidoSafariConsultado =
                        tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                            return extSafariVetRepository.findById(idUser)
                        })

                    if (extentidoSafariConsultado.isPresent) {
                        safariVet = ExtendidosRespuesta(
                            cifrado(extentidoSafariConsultado.get().usernameSafary, false),
                            extentidoSafariConsultado.get().rol
                        )
                    }

                }

                Aplicaciones.LaCamaDelPerro.name -> {
                    extentidoCamaConsultado =
                        tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                            return extCamaDelPerroRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoCamaConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtCamaDelPerro, fun() {
                            extCamaDelPerroRepository.save(
                                ExtCamaDelPerro(
                                    usuarioConsultado.get().usuario,
                                    cifrado(request.nickname),
                                    passwordEncoder.encode( "999999"),
                                    request.rol,
                                    Date(),
                                    Date()
                                )
                            )
                        })
                    }
                    extentidoCamaConsultado =
                        tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                            return extCamaDelPerroRepository.findById(idUser)
                        })

                    if (extentidoCamaConsultado.isPresent) {
                        camaDelPerro = ExtendidosRespuesta(
                            cifrado(extentidoCamaConsultado.get().usernameCamaPerro, false), extentidoCamaConsultado.get().rol
                        )
                    }
                }
            }

            val respuesta: ResponseUsuaro = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseUsuaro {
                return preparaResponseUsuario(
                    usuarioConsultado.get(), Extendidos(lunaVet, safariVet, camaDelPerro)
                )
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if (registroNuevo) {
                anulaRegistro(idUser)
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun registraUsuario(idUser: String, request: RequestsRegistroUsuario) {
        tracer.duration(Servicios().registroUsuario, fun() {
            clienteRepository.save(
                Usuario(
                    idUser,
                    cifrado(request.nombre),
                    cifrado(request.apellidoPaterno),
                    cifrado(request.apellidoMaterno),
                    cifrado(request.genero),
                    cifrado(request.fechaNacimiento),
                    cifrado(request.aplicacion),
                    Date(),
                    Date()
                )
            )
        })
    }

    fun anulaRegistro(idUser: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            clienteRepository.deleteById(idUser)
            extLunaVetRepository.deleteById(idUser)
            extSafariVetRepository.deleteById(idUser)
            extCamaDelPerroRepository.deleteById(idUser)
        })
    }
}
