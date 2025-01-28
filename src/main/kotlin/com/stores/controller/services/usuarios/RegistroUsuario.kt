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
import com.stores.responses.preparaRespopnseUsuario
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody
import java.util.Date
import java.util.Optional
import java.util.UUID

@Service
class RegistroUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(
        @Valid @RequestBody request: RequestsRegistroUsuario?,
        clienteRepository: ClienteRepository,
        extLunaVetRepository: ExtLunaVetRepository,
        extSafariVetRepository: ExtSafariVetRepository,
        extCamaDelPerroRepository: ExtCamaDelPerroRepository,
    ): ResponseEntity<Respuesta> {
        var registroNuevo = false
        val idUser = encrypt(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de usuarios: ${request!!}")

            val extLunaVet = ExtLunaVet(
                idUser, encrypt(request.nickname), encrypt(request.nickname), encrypt(request.rol), Date(), Date()
            )
            val extSafariVet = ExtSafariVet(
                idUser, encrypt(request.nickname), encrypt(request.nickname), encrypt(request.rol), Date(), Date()
            )
            val extCamaDelPerro = ExtCamaDelPerro(
                idUser, encrypt(request.nickname), encrypt(request.nickname), encrypt(request.rol), Date(), Date()
            )

            var usuarioConsultado: Optional<Usuario>
            var extentidoLunaConsultado: Optional<ExtLunaVet>? = null
            var extentidoSafariConsultado: Optional<ExtSafariVet>? = null
            var extentidoCamaConsultado: Optional<ExtCamaDelPerro>? = null

            var lunaVet: ExtendidosRespuesta? = null
            var safariVet: ExtendidosRespuesta? = null
            var camaDelPerro: ExtendidosRespuesta? = null

            usuarioConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): Optional<Usuario> {
                return clienteRepository.findByBatosBasicos(
                    encrypt(request.nombre),
                    encrypt(request.apellidoPaterno),
                    encrypt(request.apellidoMaterno.toString()),
                    encrypt(request.fechaNacimiento)
                )
            })

            if (usuarioConsultado.isPresent) {
                when (request.aplicacion) {
                    Aplicaciones().lunaVet -> {
                        extentidoLunaConsultado =
                            tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                                return extLunaVetRepository.findById(usuarioConsultado.get().usuario!!)
                            })
                        if (!extentidoLunaConsultado.isPresent) {
                            tracer.duration(Servicios().registroExtLunaVet, fun() {
                                extLunaVet.usuario = usuarioConsultado.get().usuario!!
                                extLunaVetRepository.save(extLunaVet)
                            })
                        }
                    }

                    Aplicaciones().safariVet -> {
                        extentidoSafariConsultado =
                            tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                                return extSafariVetRepository.findById(usuarioConsultado.get().usuario!!)
                            })
                        if (!extentidoSafariConsultado.isPresent) {
                            tracer.duration(Servicios().registroExtSafariVet, fun() {
                                extSafariVet.usuario = usuarioConsultado.get().usuario!!
                                extSafariVetRepository.save(extSafariVet)
                            })
                        }
                    }

                    Aplicaciones().laCamaDelPerro -> {
                        extentidoCamaConsultado =
                            tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                                return extCamaDelPerroRepository.findById(usuarioConsultado.get().usuario!!)
                            })
                        if (!extentidoCamaConsultado.isPresent) {
                            tracer.duration(Servicios().registroExtCamaDelPerro, fun() {
                                extCamaDelPerro.usuario = usuarioConsultado.get().usuario!!
                                extCamaDelPerroRepository.save(extCamaDelPerro)
                            })
                        }
                    }
                }
            } else {
                registroNuevo = true
                val cliente = Usuario(
                    idUser,
                    encrypt(request.nombre),
                    encrypt(request.apellidoPaterno),
                    encrypt(request.apellidoMaterno.toString()),
                    encrypt(request.genero),
                    CorreosElectronicos(false, encrypt(request.correo.toString())),
                    Telefonos(false, encrypt(request.telefono.toString())),
                    encrypt(request.rol),
                    encrypt(request.fechaNacimiento),
                    null,
                    null,
                    encrypt(request.aplicacion),
                    request.notificaciones,
                    Date(),
                    Date()
                )

                tracer.duration(Servicios().registroUsuario, fun() {
                    clienteRepository.save(cliente)
                })

                when (request.aplicacion) {
                    Aplicaciones().lunaVet -> {
                        tracer.duration(Servicios().registroExtLunaVet, fun() {
                            extLunaVetRepository.save(extLunaVet)
                        })
                    }

                    Aplicaciones().safariVet -> {
                        tracer.duration(Servicios().registroExtSafariVet, fun() {
                            extSafariVetRepository.save(extSafariVet)
                        })
                    }

                    Aplicaciones().laCamaDelPerro -> {
                        tracer.duration(Servicios().registroExtCamaDelPerro, fun() {
                            extCamaDelPerroRepository.save(extCamaDelPerro)
                        })
                    }
                }

                usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                    return clienteRepository.findById(idUser)
                })
                extentidoLunaConsultado = tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                    return extLunaVetRepository.findById(idUser)
                })
                extentidoSafariConsultado =
                    tracer.duration(Servicios().consultaExtSafariVet, fun(): Optional<ExtSafariVet> {
                        return extSafariVetRepository.findById(idUser)
                    })
                extentidoCamaConsultado =
                    tracer.duration(Servicios().consultaExtCamaDelPerro, fun(): Optional<ExtCamaDelPerro> {
                        return extCamaDelPerroRepository.findById(idUser)
                    })
            }

            if (extentidoLunaConsultado != null) {
                lunaVet = ExtendidosRespuesta(
                    decrypt(extentidoLunaConsultado.get().usuario),
                    decrypt(extentidoLunaConsultado.get().nickname),
                    decrypt(extentidoLunaConsultado.get().rol)
                )
            }

            if (extentidoSafariConsultado != null) {
                safariVet = ExtendidosRespuesta(
                    decrypt(extentidoSafariConsultado.get().usuario),
                    decrypt(extentidoSafariConsultado.get().nickname),
                    decrypt(extentidoSafariConsultado.get().rol)
                )
            }

            if (extentidoCamaConsultado != null) {
                camaDelPerro = ExtendidosRespuesta(
                    decrypt(extentidoCamaConsultado.get().usuario),
                    decrypt(extentidoCamaConsultado.get().nickname),
                    decrypt(extentidoCamaConsultado.get().rol)
                )
            }

            val respuesta: ResponseUsuaro = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseUsuaro {
                return preparaRespopnseUsuario(
                    usuarioConsultado.get(), Extendidos(lunaVet, safariVet, camaDelPerro)
                )
            })

            logs.info("informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if(registroNuevo){
                tracer.duration(Servicios().anulaRegistro, fun() {
                    clienteRepository.deleteById(idUser)
                    extLunaVetRepository.deleteById(idUser)
                    extSafariVetRepository.deleteById(idUser)
                    extCamaDelPerroRepository.deleteById(idUser)
                })
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}
