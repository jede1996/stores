package com.stores.controller.services.opiniones

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.OpinionesRepository
import com.stores.request.RequestOpiniones
import com.stores.request.RequestRegistroOpinion
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class Opiniones @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registrarOpinion(request: RequestRegistroOpinion?, opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun cerrarOpinion(request: RequestOpiniones?, opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun consultarOpinion(request: RequestOpiniones?, opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun listarOpiniones(opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }
}