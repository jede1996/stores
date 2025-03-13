package com.stores.controller.services.reportes

import com.stores.config.CatalogoResponses
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.request.RequestReportes
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Reportes @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun repostePorFecha(request: RequestReportes?): ResponseEntity<Any> {
        try {
            logs.info("Request para el servicio de reportes: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}