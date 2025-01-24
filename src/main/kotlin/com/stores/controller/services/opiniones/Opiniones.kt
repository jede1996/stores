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
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registrarOpinion(
        request: RequestRegistroOpinion?,
        opinionesRepository: OpinionesRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de registro de opiniones: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun cerrarOpinion(request: RequestOpiniones?, opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de cerrado de opiniones: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun consultarOpinion(
        request: RequestOpiniones?,
        opinionesRepository: OpinionesRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de opiniones: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun listarOpiniones(opinionesRepository: OpinionesRepository): ResponseEntity<Respuesta> {
        try {

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }
}