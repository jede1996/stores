package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.opiniones.Opiniones
import com.stores.repository.OpinionesRepository
import com.stores.request.RequestOpiniones
import com.stores.request.RequestRegistroOpinion
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("opinion")
class OpinionesController(
    val opinionesRepository: OpinionesRepository,
    val opinion: Opiniones,

) {
    @PostMapping("registro")
    fun registroOpinion(@Valid @RequestBody request: RequestRegistroOpinion?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return opinion.registrarOpinion(request)
    }

    @PostMapping("cerrar")
    fun cerrarOpinion(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return opinion.cerrarOpinion(request)
    }

    @PostMapping("consulta")
    fun consultarOpinion(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return opinion.consultarOpinion(request)
    }

    @PostMapping("listado-usuario")
    fun consultarOpinionPorUsuario(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return opinion.consultarOpinion(request)
    }

    @GetMapping("listado")
    fun listadoOpiniones(): ResponseEntity<Any> {
        return opinion.listarOpiniones()
    }
}