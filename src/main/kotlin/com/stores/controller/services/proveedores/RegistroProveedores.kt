package com.stores.controller.services.proveedores

import com.stores.config.*
import com.stores.entities.Proveedor
import com.stores.repository.ProveedorRepository
import com.stores.request.RequestsRegistroProveedor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class RegistroProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val proveedorRepository: ProveedorRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroProveedores(request: RequestsRegistroProveedor): ResponseEntity<Respuesta> {
        var registroNuevo = false
        val idUser = encrypt(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de proveedores: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            var proveedorConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): Optional<Proveedor> {
                return proveedorRepository.findByAplicacion(request.aplicacion, request.empresa)

            })

            if (!proveedorConsultado.isPresent) {
                registroNuevo = true
                registraProveedor(idUser, request)
                proveedorConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Proveedor> {
                    return proveedorRepository.findById(idUser)
                })
            }

            val respuesta: Proveedor = tracer.duration(Servicios().preparacionRespuesta, fun(): Proveedor {
                return proveedorConsultado.get()
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

    fun anulaRegistro(idUser: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            proveedorRepository.deleteById(idUser)
        })
    }

    fun registraProveedor(idUser: String, request: RequestsRegistroProveedor) {
        tracer.duration(Servicios().registroUsuario, fun() {
            proveedorRepository.save(
                Proveedor(
                    idUser,
                    encrypt(request.empresa),
                    encrypt(request.nombre),
                    encrypt(request.apellidoPaterno),
                    encrypt(request.apellidoMaterno),
                    request.correo,
                    request.telefono,
                    encrypt(request.aplicacion),
                    Date(),
                    Date()
                )
            )
        })
    }

}