package com.stores.controller.services.productos

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ProductoRepository
import com.stores.request.RequestConsultaProducto
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaProducto  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaInventario(request: RequestConsultaProducto?, productoRepository: ProductoRepository): ResponseEntity<Respuesta>{
        return buildresponse(respuesta = "")
    }

}