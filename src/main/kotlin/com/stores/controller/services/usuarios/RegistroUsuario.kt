package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.*
import com.stores.repository.ClienteRepository
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import com.stores.request.RequestsRegistroUsuario
import com.stores.responses.Extendidos
import com.stores.responses.ExtendidosRespuesta
import com.stores.responses.ResponseUsuaro
import com.stores.responses.preparaResponseUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(request: RequestsRegistroUsuario): ResponseEntity<Respuesta> {
        var registroNuevo = false
        val idUser = encrypt(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de usuarios: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            if (!validaRoles(request.rol)) return buildresponse(error = CatalogoResponses.ROL_INVALIDO)

            var extentidoLunaConsultado: Optional<ExtLunaVet>?
            var extentidoSafariConsultado: Optional<ExtSafariVet>?
            var extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            var lunaVet: ExtendidosRespuesta? = null
            var safariVet: ExtendidosRespuesta? = null
            var camaDelPerro: ExtendidosRespuesta? = null

            var usuarioConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): Optional<Usuario> {
                return clienteRepository.findByBatosBasicos(
                    encrypt(request.nombre),
                    encrypt(request.apellidoPaterno),
                    encrypt(request.apellidoMaterno),
                    encrypt(request.fechaNacimiento)
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
                Aplicaciones().lunaVet -> {
                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoLunaConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtLunaVet, fun() {
                            extLunaVetRepository.save(
                                ExtLunaVet(
                                    usuarioConsultado.get().usuario,
                                    encrypt(request.nickname),
                                    encrypt("999999"),
                                    encrypt(request.rol),
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
                            decrypt(extentidoLunaConsultado.get().nickname), decrypt(extentidoLunaConsultado.get().rol)
                        )
                    }
                }

                Aplicaciones().safariVet -> {
                    extentidoSafariConsultado =
                        tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                            return extSafariVetRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoSafariConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtSafariVet, fun() {
                            extSafariVetRepository.save(
                                ExtSafariVet(
                                    usuarioConsultado.get().usuario,
                                    encrypt(request.nickname),
                                    encrypt("999999"),
                                    encrypt(request.rol),
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
                            decrypt(extentidoSafariConsultado.get().nickname),
                            decrypt(extentidoSafariConsultado.get().rol)
                        )
                    }

                }

                Aplicaciones().laCamaDelPerro -> {
                    extentidoCamaConsultado =
                        tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                            return extCamaDelPerroRepository.findById(usuarioConsultado.get().usuario)
                        })
                    if (!extentidoCamaConsultado.isPresent) {
                        tracer.duration(Servicios().registroExtCamaDelPerro, fun() {
                            extCamaDelPerroRepository.save(
                                ExtCamaDelPerro(
                                    usuarioConsultado.get().usuario,
                                    encrypt(request.nickname),
                                    encrypt("999999"),
                                    encrypt(request.rol),
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
                            decrypt(extentidoCamaConsultado.get().nickname), decrypt(extentidoCamaConsultado.get().rol)
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
                    encrypt(request.nombre),
                    encrypt(request.apellidoPaterno),
                    encrypt(request.apellidoMaterno),
                    encrypt(request.genero),
                    CorreosElectronicos(false, encrypt(request.correo)),
                    Telefonos(false, encrypt(request.telefono)),
                    encrypt(request.rol),
                    encrypt(request.fechaNacimiento),
                    encrypt(request.aplicacion),
                    request.notificaciones,
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
