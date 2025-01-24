package com.stores.controller.services.notificaciones

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.NotificacionesRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaNotificaciones @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaNotificaciones(
        request: Any?,
        notificacionesRepository: NotificacionesRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de notificaciones: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun enviarNotificaciones(
        request: Any?,
        notificacionesRepository: NotificacionesRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de envio de notificaciones: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}