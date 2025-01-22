package com.stores.controller.services.opiniones

import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Opiniones  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registrarOpinion(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun cerrarOpinion(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun consultarOpinion(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }

    fun listarOpiniones(): ResponseEntity<Any> {
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }
}