package com.stores.controller.services.usuarios

import com.stores.config.ServiceInterceptor
import com.stores.request.RequestsRegistroUsuario
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class Registro  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(@Valid @RequestBody request: RequestsRegistroUsuario?): ResponseEntity<Any> {
        if(request == null) return com.stores.config.buildresponse(descripcion = com.stores.config.CatalogoResponses.BODY_NULL)
        return com.stores.config.buildresponse(descripcion = com.stores.config.CatalogoResponses.NOTIFICACION_REQUERIDO)
    }

}