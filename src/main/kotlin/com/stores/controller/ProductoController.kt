package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.productos.*
import com.stores.request.RequestConsulta
import com.stores.request.RequestConsultaProducto
import com.stores.request.RequestProducto
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("producto")
class ProductoController(
    val registroProducto: RegistroProducto,
    val bajaProducto: BajaProducto,
    val modificacionProducto: ModificacionProducto,
    val consultaProducto: ConsultaProducto,
    val listadoProducto: ListadoProducto
) {
    @PostMapping("registro")
    fun registroProducto(@Valid @RequestBody request: RequestProducto?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroProducto.registroInventario(request)
    }

    @PostMapping("baja")
    fun bajaProducto(@Valid @RequestBody request: RequestConsultaProducto?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return bajaProducto.bajaInventario(request)
    }

    @PostMapping("modificacion")
    fun modificacionProducto(@Valid @RequestBody request: RequestProducto?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionProducto.modificacionInventario(request)
    }

    @PostMapping("consulta")
    fun consultaProducto(@Valid @RequestBody request: RequestConsultaProducto?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return consultaProducto.consultaInventario(request)
    }

    @GetMapping("listado")
    fun listadoProductos(@Valid @RequestBody request: RequestConsulta): ResponseEntity<Any> {
        return listadoProducto.listadoInventario(request)
    }

}