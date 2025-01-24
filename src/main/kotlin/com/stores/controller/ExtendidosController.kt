package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.extendidos.BajaExtendidos
import com.stores.controller.services.extendidos.ConsultaExtendidos
import com.stores.controller.services.extendidos.ModificacionExtendidos
import com.stores.controller.services.extendidos.RegistroExtendidos
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.repository.ExtLunaVetRepository
import com.stores.request.RequestBusquedaExt
import com.stores.request.RequestExt
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("extendido")
class ExtendidosController(
    val extLunaVetRepository: ExtLunaVetRepository,
    val extCamaDelPerroRepository: ExtCamaDelPerroRepository,
    val bajaExtendidos: BajaExtendidos,
    val consultaExtendidos: ConsultaExtendidos,
    val modificacionExtendidos: ModificacionExtendidos,
    val registroExtendidos: RegistroExtendidos
) {
    @PostMapping("registro")
    fun registroExtendido(@Valid @RequestBody request: RequestExt?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroExtendidos.registroExtendidos(request, extLunaVetRepository, extCamaDelPerroRepository)
    }

    @PostMapping("baja")
    fun bajaExtendido(@Valid @RequestBody request: RequestBusquedaExt?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return bajaExtendidos.bajaExtendidos(request, extLunaVetRepository, extCamaDelPerroRepository)
    }

    @PostMapping("modificacion")
    fun modificacionExtendido(@Valid @RequestBody request: RequestExt?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionExtendidos.modificacionExtendidos(request, extLunaVetRepository, extCamaDelPerroRepository)
    }

    @PostMapping("consulta")
    fun consultaExtendido(@Valid @RequestBody request: RequestBusquedaExt?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return consultaExtendidos.consultaExtendidos(request, extLunaVetRepository, extCamaDelPerroRepository)
    }
}
