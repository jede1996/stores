package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.usuarios.*
import com.stores.repository.ClienteRepository
import com.stores.request.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
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
    fun registroUsuario(@Valid @RequestBody request: RequestsRegistroUsuario?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroUsuario.registroUsuario(request, clienteRepository)
    }

    @PostMapping("baja")
    fun bajaUsuario(@Valid @RequestBody request: RequestActualizacionUsuario?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return bajaUsuario.bajaUsuario(request, clienteRepository)
    }

    @PostMapping("modificacion")
    fun modificacionUsuario(@Valid @RequestBody request: RequestActualizacionUsuario?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.modificacionUsuario(request, clienteRepository)
    }

    @PostMapping("consulta")
    fun consultaUsuario(@Valid @RequestBody request: RequestConsultaUsuario?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return consultaUsuario.consultaUsuario(request, clienteRepository)
    }

    @GetMapping("listado")
    fun listadoUsuarios(): ResponseEntity<Respuesta> {
        return listadoUsuarios.listadoUsuarios(clienteRepository)
    }

    @PostMapping("codigo-email")
    fun envioCodigo(@Valid @RequestBody request: RequestEnvioCodigo?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.envioCodigo(request)
    }

    @PostMapping("valicacion-email")
    fun validacionEmail(@Valid @RequestBody request: RequestValidacionCodigo?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.validacionEmail(request)
    }

    @PostMapping("actualizacion-contrasenna")
    fun actualizacontrasenna(@Valid @RequestBody request: RequestActualizacionUsuario?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionUsuario.actualizaContrasenna(request)
    }
}
