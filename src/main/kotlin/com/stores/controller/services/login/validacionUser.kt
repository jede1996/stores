package com.stores.controller.services.login

import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ClienteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class validacionUser  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun validacionActualizacion(request: Any?, clienteRepository: ClienteRepository): ResponseEntity<Any>{
        return buildresponse(response = "")
    }

}