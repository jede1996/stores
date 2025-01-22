package com.stores.controller.services.usuarios

import com.stores.config.ServiceInterceptor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Consulta  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaUsuario(): ResponseEntity<Any> {
        return com.stores.config.buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
    }
}