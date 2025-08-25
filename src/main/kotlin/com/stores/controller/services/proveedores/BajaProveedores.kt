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
class BajaProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor, private val proveedorRepository: ProveedorRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaProveedores(
        request: RequestConsultaProveedor
    ): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de eliminacion de proveedores: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val proveedorConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Proveedor> {
                return proveedorRepository.findByAplicacion(cifrado(request.aplicacion), cifrado(request.empresa))
            })

            if (!proveedorConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)


            tracer.duration(Servicios().eliminaUsuario, fun() {
                return proveedorRepository.deleteById(proveedorConsultado.get().proveedor)
            })

            logs.info("Informacion a regresar: Proveedor eliminado")
            return buildresponse(respuesta = "Proveedor eliminado")
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}