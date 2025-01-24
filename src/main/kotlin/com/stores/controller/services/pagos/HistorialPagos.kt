package com.stores.controller.services.pagos

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class HistorialPagos  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun historialPagos(request: Any?): ResponseEntity<Respuesta>{
        return buildresponse(respuesta = "")
    }

    fun historialGeneralPagos(request: Any?): ResponseEntity<Respuesta>{
        return buildresponse(respuesta = "")
    }

}