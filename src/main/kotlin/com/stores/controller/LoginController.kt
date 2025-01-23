package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.buildresponse
import com.stores.controller.services.login.ActualizacionContrasenna
import com.stores.controller.services.login.BloqueoLogin
import com.stores.controller.services.login.LoginUser
import com.stores.controller.services.login.validacionUser
import com.stores.repository.ClienteRepository
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("login")
class LoginController(
    val clienteRepository: ClienteRepository,
    val loginUser: LoginUser,
    val bloqueoLogin: BloqueoLogin,
    val validacionUser: validacionUser,
    val actualizacionContrasenna: ActualizacionContrasenna
) {
    @PostMapping("log")
    fun inicioSesion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return loginUser.login(request, clienteRepository)
    }

    @PostMapping("bloqueo")
    fun bloqueoSesion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return bloqueoLogin.BloqueoSesion(request, clienteRepository)
    }

    @PostMapping("validacion")
    fun validacionActualizacion(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return validacionUser.validacionActualizacion(request, clienteRepository)
    }

    @PostMapping("actualizacion-contrasenna")
    fun actualizacontrasenna(@Valid @RequestBody request: Any?): ResponseEntity<Any> {
        if (request == null) return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
        return actualizacionContrasenna.actualizacontrasenna(request, clienteRepository)
    }
}
