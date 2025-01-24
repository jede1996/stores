package com.stores.controller.services.usuarios

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ClienteRepository
import com.stores.request.RequestConsultaUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaUsuario(
        request: RequestConsultaUsuario?,
        clienteRepository: ClienteRepository
    ): ResponseEntity<Respuesta> {
        return buildresponse(error = CatalogoResponses.BODY_NULL)
    }
}