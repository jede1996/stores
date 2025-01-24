package com.stores.controller.services.consultas

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ConsultasRepository
import com.stores.request.RequestRegistroConsulta
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RegistroConsultas @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroConsulta(
        request: RequestRegistroConsulta?,
        consultasRepository: ConsultasRepository,
    ): ResponseEntity<Respuesta> {
        return buildresponse(respuesta = "")
    }

}
