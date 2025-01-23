package com.stores.controller


import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.usuarios.*
import com.stores.repository.ClienteRepository
import com.stores.request.RequestsRegistroUsuario
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("usuario")
class UsuariosController(
    val clienteRepository: ClienteRepository,
    val registroUsuario: RegistroUsuario,
    val bajaUsuario: BajaUsuario,
    val modificacionUsuario: ModificacionUsuario,
    val consultaUsuario: ConsultaUsuario,
    val listadoUsuarios: ListadoUsuarios
) {

    @PostMapping("registro")
    fun registroUsuario(@Valid @RequestBody request: RequestsRegistroUsuario?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return registroUsuario.registroUsuario(request, clienteRepository)
    }

    @PostMapping("baja")
    fun bajaUsuario(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return bajaUsuario.bajaUsuario(request, clienteRepository)
    }

    @PostMapping("modificacion")
    fun modificacionUsuario(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.modificacionUsuario(request, clienteRepository)
    }

    @PostMapping("consulta")
    fun consultaUsuario(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return consultaUsuario.consultaUsuario(request, clienteRepository)
    }

    @PostMapping("listado")
    fun listadoUsuarios(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return listadoUsuarios.listadoUsuarios(request, clienteRepository)
    }
}