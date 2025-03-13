package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.usuarios.*
import com.stores.request.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("usuarios")
class UsuariosController(
    val registroUsuario: RegistroUsuario,
    val bajaUsuario: BajaUsuario,
    val modificacionUsuario: ModificacionUsuario,
    val consultaUsuario: ConsultaUsuario,
    val listadoUsuarios: ListadoUsuarios
) {

    @PostMapping("registro")
    fun registroUsuario(@Valid @RequestBody request: RequestsRegistroUsuario?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroUsuario.registroUsuario(request)
    }

    @PostMapping("baja")
    fun bajaUsuario(@Valid @RequestBody request: RequestConsultaUsuario?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return bajaUsuario.bajaUsuario(request)
    }

    @PostMapping("modificacion")
    fun modificacionUsuario(@Valid @RequestBody request: RequestActualizacionUsuario?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.modificacionUsuario(request)
    }

    @PostMapping("consulta")
    fun consultaUsuario(@Valid @RequestBody request: RequestConsultaUsuario?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return consultaUsuario.consultaUsuario(request)
    }

    @PostMapping("listado")
    fun listadoUsuarios(@Valid @RequestBody request: RequestConsultaUsuarios?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return listadoUsuarios.listadoUsuarios(request)
    }

    @PostMapping("actualizacion-contrasenna")
    fun actualizacontrasenna(@Valid @RequestBody request: RequestActualizacionContrasenna?): ResponseEntity<Any> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.actualizaContrasenna(request)
    }
}
