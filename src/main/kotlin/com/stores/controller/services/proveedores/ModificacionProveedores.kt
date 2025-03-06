package com.stores.controller.services.proveedores

import com.stores.config.*
import com.stores.entities.Proveedor
import com.stores.repository.ProveedorRepository
import com.stores.request.RequestsModificaProveedor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ModificacionProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val proveedorRepository: ProveedorRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionProveedores(request: RequestsModificaProveedor): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de actualizacion de proveedores: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            var proveedorConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Proveedor> {
                return proveedorRepository.findById(encrypt(request.idEmpresa))
            })

            if (!proveedorConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            val proveedor = proveedorConsultado.get()

            if (decrypt(proveedor.nombre) != request.nombre) proveedor.nombre = encrypt(request.nombre)
            if (decrypt(proveedor.empresa) != request.empresa) proveedor.empresa = encrypt(request.empresa)
            if (decrypt(proveedor.apellidoPaterno) != request.apellidoPaterno) proveedor.apellidoPaterno = encrypt(request.apellidoPaterno)
            if (decrypt(proveedor.apellidoMaterno) != request.apellidoMaterno) proveedor.apellidoMaterno = encrypt(request.apellidoMaterno)
            if (decrypt(proveedor.correo) != request.correo) proveedor.correo = encrypt(request.correo)
            if (decrypt(proveedor.telefono) != request.telefono) proveedor.telefono = encrypt(request.telefono)
            if (decrypt(proveedor.aplicacion) != request.aplicacion) proveedor.aplicacion = encrypt(request.aplicacion)
            proveedor.fechaModificacion = Date()

            tracer.duration(Servicios().actualizacionUsuario, fun() {
                proveedorRepository.save(proveedor)
            })


            proveedorConsultado =
                tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<Proveedor> {
                    return proveedorRepository.findById(proveedor.proveedor)
                })

            val respuesta: Proveedor = tracer.duration(Servicios().preparacionRespuesta, fun(): Proveedor {
                return proveedorConsultado.get()
            })

            logs.info("informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}