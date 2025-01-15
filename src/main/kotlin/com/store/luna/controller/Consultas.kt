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
@RequestMapping("conultas")
class Consultas {
    @PostMapping("registro")
    fun registro(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("cancelacion")
    fun cancelacion(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("reprogramacion")
    fun reprogramacion(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    @PostMapping("historial")
    fun historial(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }


    @PostMapping("historial-general")
    fun historialGeneral(): ResponseEntity<Any> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }
}