package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
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
    fun registroOpinion(@Valid @RequestBody request: RequestRegistroOpinion?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.registrarOpinion(request, opinionesRepository)
    }

    @PostMapping("cerrar")
    fun cerrarOpinion(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.cerrarOpinion(request, opinionesRepository)
    }

    @PostMapping("consulta")
    fun consultarOpinion(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.consultarOpinion(request, opinionesRepository)
    }

    @PostMapping("listado-usuario")
    fun consultarOpinionPorUsuario(@Valid @RequestBody request: RequestOpiniones?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return opinion.consultarOpinion(request, opinionesRepository)
    }

    @GetMapping("listado")
    fun listadoOpiniones(): ResponseEntity<Respuesta> {
        return opinion.listarOpiniones(opinionesRepository)
    }
}