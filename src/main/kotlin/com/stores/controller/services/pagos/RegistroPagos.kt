package com.stores.controller.services.pagos

import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ConsultasRepository
import com.stores.request.RequestsRegistroUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RegistroPagos  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroPagos(request: Any?): ResponseEntity<Any>{
        return buildresponse(response = "")
    }

}