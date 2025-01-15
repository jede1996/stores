package com.store.luna.controller

import com.store.luna.config.CatalogoResponses
import com.store.luna.config.buildresponse
import com.store.luna.request.RequestsRegistroUsuario
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("usuarios")
class Usuarios {
    @PostMapping("registro")
    fun registro(@Valid @RequestBody request: RequestsRegistroUsuario?): ResponseEntity<Any> {
        if(request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(descripcion = CatalogoResponses.NOTIFICACION_REQUERIDO)
    }

    @PostMapping("baja")
    fun baja(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("modificacion")
    fun modificacion(): ResponseEntity<Any> {
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