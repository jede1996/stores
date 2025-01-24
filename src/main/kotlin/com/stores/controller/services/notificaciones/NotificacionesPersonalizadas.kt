package com.stores.controller.services.notificaciones

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.NotificacionesRepository
import com.stores.request.RequestNotificaciones
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class NotificacionesPersonalizadas  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun notificacionesPersonalizadas(request: RequestNotificaciones?, notificacionesRepository: NotificacionesRepository): ResponseEntity<Respuesta>{
        return buildresponse(respuesta = "")
    }

}