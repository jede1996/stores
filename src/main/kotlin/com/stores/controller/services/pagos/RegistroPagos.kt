package com.stores.controller.services.pagos

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
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

    fun registroPagos(request: Any?): ResponseEntity<Respuesta>{
        try {
            logs.info("Request para el servicio de regidtro de pagos: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

}