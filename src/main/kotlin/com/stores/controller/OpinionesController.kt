package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.opiniones.Opiniones
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("opinion")
class OpinionesController(
    val opinion: Opiniones
) {
    @PostMapping("registro")
    fun registroOpinion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.registrarOpinion(request)
    }


    @PostMapping("cerrar")
    fun cerrarOpinion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.cerrarOpinion(request)
    }


    @PostMapping("consulta")
    fun consultarOpinion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.consultarOpinion(request)
    }


    @PostMapping("listado")
    fun listadoOpiniones(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.listarOpiniones(request)
    }

}