package com.stores.controller.services.mascota

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.MascotaRepository
import com.stores.request.RequestConsultaMascota
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BajaMascota  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaMascota(request: RequestConsultaMascota?, mascotaRepository: MascotaRepository): ResponseEntity<Respuesta>{
        return buildresponse(response = "")
    }

}