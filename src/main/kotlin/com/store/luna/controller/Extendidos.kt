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
@RequestMapping("extendidos")
class Extendidos {
    @PostMapping("registro")
    fun registro(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
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
}