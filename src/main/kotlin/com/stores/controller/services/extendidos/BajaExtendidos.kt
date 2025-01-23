package com.stores.controller.services.extendidos

import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BajaExtendidos  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaExtendidos(request: Any?, extLunaVetRepository: ExtLunaVetRepository, extCamaDelPerroRepository: ExtCamaDelPerroRepository): ResponseEntity<Any>{
        return buildresponse(response = "")
    }
}