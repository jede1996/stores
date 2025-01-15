package com.store.luna.controller

import com.store.luna.config.CatalogoResponses
import com.store.luna.config.buildresponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("notificaciones")
class Notificaciones {
    @PostMapping("recordatorios")
    fun recordatorios(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("invnetario-bajo")
    fun inventarioBajo(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("personalizado")
    fun personalizado(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("consulta")
    fun consulta(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("listado")
    fun listado(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }
}