package com.stores.controller.services.productos

import com.stores.config.*
import com.stores.entities.Producto
import com.stores.repository.ProductoRepository
import com.stores.request.RequestConsultaProducto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsultaProducto @Autowired constructor(
    private val tracer: ServiceInterceptor, private val productoRepository: ProductoRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaInventario(request: RequestConsultaProducto ): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de consulta de inventarios: $request")

            val prodductoConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Producto> {
                return productoRepository.findById(encrypt(request.producto))
            })


            if (!prodductoConsultado.isPresent) return buildresponse(error = CatalogoResponses.PRODUCTO_INEXISTENTE)

            val respuesta = tracer.duration(Servicios().preparacionRespuesta, fun(): Producto {
                return prodductoConsultado.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}