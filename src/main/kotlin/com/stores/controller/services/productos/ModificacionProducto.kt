package com.stores.controller.services.productos

import com.stores.config.*
import com.stores.entities.Producto
import com.stores.repository.ProductoRepository
import com.stores.request.RequestProducto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ModificacionProducto  @Autowired constructor(
    private val tracer : ServiceInterceptor,
    private val productoRepository: ProductoRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionInventario(request: RequestProducto): ResponseEntity<Respuesta>{
        try {
            logs.info("Request para el servicio de actualizacion de productos: $request")

            var productoConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Producto> {
                return productoRepository.findById(encrypt(request.producto))
            })

            if (!productoConsultado.isPresent) return buildresponse(error = CatalogoResponses.PRODUCTO_INEXISTENTE)

            val producto = productoConsultado.get()

            if (decrypt(producto.nombre) != request.nombre) producto.nombre = encrypt(request.nombre)

            if (decrypt(producto.descripcion) != request.descripcion) producto.descripcion = encrypt(request.descripcion)
            if (decrypt(producto.categoria) != request.categoria) producto.categoria = encrypt(request.categoria)
            if (decrypt(producto.subcategoria) != request.subcategoria) producto.subcategoria = encrypt(request.subcategoria)
            if (decrypt(producto.estado) != request.estado) producto.estado = encrypt(request.estado)
            if (producto.stock != request.stock) producto.stock = request.stock
            if (producto.precios != request.precios) producto.precios = request.precios
            if (decrypt(producto.proveedor) != request.proveedor) producto.proveedor = encrypt(request.proveedor)
            producto.fechaModificacion = Date()

            tracer.duration(Servicios().actualizacionUsuario, fun() {
                productoRepository.save(producto)
            })


            productoConsultado =
                tracer.duration(Servicios().consultaExtLunaVet, fun(): Optional<Producto> {
                    return productoRepository.findById(producto.producto)
                })

            val respuesta: Producto = tracer.duration(Servicios().preparacionRespuesta, fun(): Producto {
                return productoConsultado.get()
            })

            logs.info("informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}