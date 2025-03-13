package com.stores.controller.services.proveedores

import com.stores.config.*
import com.stores.entities.Proveedor
import com.stores.repository.ProveedorRepository
import com.stores.request.RequestConsultaProveedor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsultaProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val proveedorRepository: ProveedorRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaProveedor(
        request: RequestConsultaProveedor
    ): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de consulta de proveedores: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val proveedorConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Proveedor> {
                return proveedorRepository.findByAplicacion(encrypt(request.aplicacion), encrypt(request.empresa))
            })

            if (!proveedorConsultado.isPresent) return buildresponse(error = CatalogoResponses.PROVEEDOR_INEXISTENTE)

            val respuesta: Proveedor = tracer.duration(Servicios().preparacionRespuesta, fun(): Proveedor {
                return proveedorConsultado.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}