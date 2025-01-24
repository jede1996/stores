package com.stores.controller.services.consultas

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ConsultasRepository
import com.stores.request.RequestBusquedaConsulta
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class HistorialConsultas  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun historialConsultasCliente(request: RequestBusquedaConsulta?, consultasRepository: ConsultasRepository): ResponseEntity<Respuesta>{
        try {
            logs.info("Request para el servicio de historial de conultas por clientes: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun historialConsultasGeneral(consultasRepository: ConsultasRepository): ResponseEntity<Respuesta>{
        try {
            logs.info("Servicio de historial general de consultas")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

}