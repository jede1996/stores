package com.stores.controller.services.extendidos

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.request.RequestBusquedaExt
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaExtendidos @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaExtendidos(
        request: RequestBusquedaExt?,
        extLunaVetRepository: ExtLunaVetRepository,
        extCamaDelPerroRepository: ExtCamaDelPerroRepository
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de extendidos: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}