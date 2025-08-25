package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.pagos.CancelacionPagos
import com.stores.controller.services.pagos.HistorialPagos
import com.stores.controller.services.pagos.RegistroPagos
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("pago")
class PagosController(
    val registroPagos: RegistroPagos,
    val cancelacionPagos: CancelacionPagos,
    val historialPagos: HistorialPagos
) {
    @PostMapping("registro")
    fun registroPagos(@Valid @RequestBody request: Any?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroPagos.registroPagos(request)
    }

    @PostMapping("cancelacion")
    fun cancelacionPagos(@Valid @RequestBody request: Any?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return cancelacionPagos.cancelacionPagos(request)
    }

    @PostMapping("historial")
    fun historialPagos(@Valid @RequestBody request: Any?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return historialPagos.historialPagos(request)
    }


    @PostMapping("historial-general")
    fun historialGeneralPagos(@Valid @RequestBody request: Any?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return historialPagos.historialGeneralPagos(request)
    }
}