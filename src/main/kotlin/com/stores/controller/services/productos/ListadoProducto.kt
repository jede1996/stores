package com.stores.controller.services.productos

import com.stores.config.*
import com.stores.entities.Producto
import com.stores.repository.ProductoRepository
import com.stores.request.RequestConsulta
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ListadoProducto  @Autowired constructor(
    private val tracer : ServiceInterceptor,
    private val productoRepository: ProductoRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun listadoInventario(request: RequestConsulta): ResponseEntity<Any>{
        try {
            logs.info("Servicio de listado de productos")

            val productosConsultados = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): List<Producto> {
                return productoRepository.findAll()
            })

            return buildresponse(respuesta =  productosConsultados)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}