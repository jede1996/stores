package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.mascota.*
import com.stores.repository.MascotaRepository
import com.stores.request.RequestConsultaMascota
import com.stores.request.RequestMascota
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("mascota")
class MascotasController(
    val mascotaRepository: MascotaRepository,
    val registroMascota: RegistroMascota,
    val bajaMascota: BajaMascota,
    val modificacionMascota: ModificacionMascota,
    val consultaMascota: ConsultaMascota,
    val listadoMascota: ListadoMascota
) {
    @PostMapping("registro")
    fun registroMascota(@Valid @RequestBody request: RequestMascota?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return registroMascota.registroMascota(request, mascotaRepository)
    }

    @PostMapping("baja")
    fun bajaMascota(@Valid @RequestBody request: RequestConsultaMascota?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return bajaMascota.bajaMascota(request, mascotaRepository)
    }

    @PostMapping("modificacion")
    fun modificacionMascota(@Valid @RequestBody request: RequestMascota?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return modificacionMascota.modificacionMascota(request, mascotaRepository)
    }

    @PostMapping("consulta")
    fun consultaMascota(@Valid @RequestBody request: RequestConsultaMascota?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return consultaMascota.ConsultaMascota(request, mascotaRepository)
    }

    @PostMapping("listado")
    fun listadoMascotas(): ResponseEntity<Respuesta> {
        return listadoMascota.listadoMascotas(mascotaRepository)
    }
}
