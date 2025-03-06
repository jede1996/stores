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
class ConsultaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaUsuario(
        request: RequestConsultaUsuario
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de usuario: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)


            val extentidoLunaConsultado: Optional<ExtLunaVet>?
            val extentidoSafariConsultado: Optional<ExtSafariVet>?
            val extentidoCamaConsultado: Optional<ExtCamaDelPerro>?

            var lunaVet: ExtendidosRespuesta? = null
            var safariVet: ExtendidosRespuesta? = null
            var camaDelPerro: ExtendidosRespuesta? = null


            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(encrypt(request.usuario))
            })


            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            when (request.aplicacion) {
                Aplicaciones().lunaVet -> {
                    extentidoLunaConsultado =
                        tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<ExtLunaVet> {
                            return extLunaVetRepository.findById(usuarioConsultado.get().usuario)
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
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}