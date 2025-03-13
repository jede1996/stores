package com.stores.controller.services.login

import com.stores.config.CatalogoResponses
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.repository.ExtSafariVetRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ActualizacionContrasenna  @Autowired constructor(
    private val tracer : ServiceInterceptor,
    private val extLunaVetRepository: ExtLunaVetRepository,
    private val extSafariVetRepository: ExtSafariVetRepository,
    private val extCamaDelPerroRepository: ExtCamaDelPerroRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun actualizacontrasenna(request: Any?): ResponseEntity<Any>{
        try {
            logs.info("Request para el servicio de actualizacion de contraseña: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}