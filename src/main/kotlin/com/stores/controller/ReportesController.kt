package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.reportes.Reportes
import com.stores.request.RequestReportes
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("reporte")
class ReportesController(
    val reportes: Reportes
) {
    @PostMapping("fecha")
    fun reportePorFecha(@Valid @RequestBody request: RequestReportes?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return reportes.repostePorFecha(request)
    }
}