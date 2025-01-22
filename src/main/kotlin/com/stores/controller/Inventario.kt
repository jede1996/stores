package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("inventario")
class Inventario {
    @PostMapping("registro")
    fun registroProducto(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(response = null)
    }


    @PostMapping("baja")
    fun bajaProducto(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(response = null)
    }


    @PostMapping("modificacion")
    fun modificacionProducto(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(response = null)
    }


    @PostMapping("consulta")
    fun consultaProducto(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(response = null)
    }


    @PostMapping("listado")
    fun listadoProductos(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return buildresponse(response = null)
    }

}