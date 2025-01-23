package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.consultas.CancelacionConsultas
import com.stores.controller.services.consultas.HistorialConsultas
import com.stores.controller.services.consultas.RegistroConsultas
import com.stores.controller.services.consultas.ReprogramacionConsultas
import com.stores.repository.ConsultasRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
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
    fun registroConsulta(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return registroConsultas.registroConsulta(request, consultasRepository)
    }

    @PostMapping("cancelacion")
    fun cancelacionConsulta(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return cancelacionConsultas.cancelacionConsulta(request, consultasRepository)
    }

    @PostMapping("reprogramacion")
    fun reprogramacionConsulta(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return reprogramacionConsultas.reprogramacionConsulta(request, consultasRepository)
    }

    @PostMapping("historial")
    fun historialConsultas(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return historialConsultas.historialConsultasCliente(request, consultasRepository)
    }

    @PostMapping("historial-general")
    fun historialGeneralConsultas(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return historialConsultas.historialConsultasGeneral(request, consultasRepository)
    }
}