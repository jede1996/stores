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
class BajaProducto @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val productoRepository: ProductoRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaInventario(
        request: RequestConsultaProducto
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de baja de productos: $request")

            val productoConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Producto> {
                return productoRepository.findById(encrypt(request.producto))
            })

            if (!productoConsultado.isPresent) return buildresponse(error = CatalogoResponses.PRODUCTO_INEXISTENTE)

            tracer.duration(Servicios().eliminaProducto, fun() {
                return productoRepository.deleteById(productoConsultado.get().producto)
            })

            logs.info("Informacion a regresar: Producto eliminado")
            return buildresponse(respuesta = "Producto eliminado")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}