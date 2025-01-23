package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.notificaciones.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notificacion")
class NotificacionesController(
    val notificacionesRecordatorios: NotificacionesRecordatorios,
    val inventariosNotificaciones: InventariosNotificaciones,
    val consultaNotificaciones: ConsultaNotificaciones,
    val notificacionesPersonalizadas: NotificacionesPersonalizadas,
    val listadoNotificaciones: ListadoNotificaciones
) {
    @PostMapping("recordatorios")
    fun notificacionesRecordatorios(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return inventariosNotificaciones.notificacionInventarioBajo(request)
    }


    @PostMapping("invnetario-bajo")
    fun notificacionInventarioBajo(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return notificacionesRecordatorios.notificacionesRecordatorios(request)
    }


    @PostMapping("personalizado")
    fun notificacionesPersonalizado(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return notificacionesPersonalizadas.notificacionesPersonalizadas(request)
    }


    @PostMapping("consulta")
    fun consultaNotificaciones(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return consultaNotificaciones.consultaNotificaciones(request)
    }


    @PostMapping("listado")
    fun listadoNotificaciones(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return listadoNotificaciones.listadoNotificaciones(request)
    }

}