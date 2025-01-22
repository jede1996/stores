package com.stores.controller.services.reportes

import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Reportes  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun reporteDiario(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun reportePorMes(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun reportePorAnno(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun repostePorFecha(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

}