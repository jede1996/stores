package com.stores.controller.services.usuarios

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ClienteRepository
import com.stores.request.RequestsRegistroUsuario
import jakarta.validation.Valid
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class RegistroUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroUsuario(
        @Valid @RequestBody request: RequestsRegistroUsuario?,
        clienteRepository: ClienteRepository
    ): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(descripcion = com.stores.config.CatalogoResponses.NOTIFICACION_REQUERIDO)
    }

}