package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.notificaciones.*
import com.stores.repository.NotificacionesRepository
import com.stores.request.RequestConsultaNotificaciones
import com.stores.request.RequestNotificaciones
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notificacion")
class NotificacionesController(
    val notificacionesRepository: NotificacionesRepository,
    val consultaNotificaciones: ConsultaNotificaciones,
    val notificacionesPersonalizadas: NotificacionesPersonalizadas,
) {
    @PostMapping("registro")
    fun notificacionesPersonalizado(@Valid @RequestBody request: RequestNotificaciones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return notificacionesPersonalizadas.notificacionesPersonalizadas(request, notificacionesRepository)
    }

    @PostMapping("enviar")
    fun enviarNotificaciones(@Valid @RequestBody request: RequestConsultaNotificaciones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return consultaNotificaciones.enviarNotificaciones(request, notificacionesRepository)
    }

    @PostMapping("listado")
    fun consultaNotificacionesPorApp(@Valid @RequestBody request: RequestConsultaNotificaciones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return consultaNotificaciones.consultaNotificaciones(request, notificacionesRepository)
    }

}
