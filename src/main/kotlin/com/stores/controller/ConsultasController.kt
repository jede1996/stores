package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.consultas.CancelacionConsultas
import com.stores.controller.services.consultas.HistorialConsultas
import com.stores.controller.services.consultas.RegistroConsultas
import com.stores.controller.services.consultas.ReprogramacionConsultas
import com.stores.repository.ConsultasRepository
import com.stores.request.RequestBusquedaConsulta
import com.stores.request.RequestProgramacionConsulta
import com.stores.request.RequestRegistroConsulta
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("consulta")
class ConsultasController(
    val consultasRepository: ConsultasRepository,
    val cancelacionConsultas: CancelacionConsultas,
    val historialConsultas: HistorialConsultas,
    val registroConsultas: RegistroConsultas,
    val reprogramacionConsultas: ReprogramacionConsultas
) {
    @PostMapping("registro")
    fun registroConsulta(@Valid @RequestBody request: RequestRegistroConsulta?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return registroConsultas.registroConsulta(request, consultasRepository)
    }

    @PostMapping("cancelacion")
    fun cancelacionConsulta(@Valid @RequestBody request: RequestBusquedaConsulta?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return cancelacionConsultas.cancelacionConsulta(request, consultasRepository)
    }

    @PostMapping("reprogramacion")
    fun reprogramacionConsulta(@Valid @RequestBody request: RequestProgramacionConsulta?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return reprogramacionConsultas.reprogramacionConsulta(request, consultasRepository)
    }

    @PostMapping("historial")
    fun historialConsultasCliente(@Valid @RequestBody request: RequestBusquedaConsulta?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return historialConsultas.historialConsultasCliente(request, consultasRepository)
    }

    @GetMapping("historial-general")
    fun historialGeneralConsultas(): ResponseEntity<Respuesta> {
        return historialConsultas.historialConsultasGeneral(consultasRepository)
    }
}