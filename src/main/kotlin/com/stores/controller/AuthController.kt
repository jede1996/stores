package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.login.LoginUser
import com.stores.request.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("auth")
class AuthController(
    val loginUser: LoginUser
) {
    @PostMapping("login")
    fun inicioSesion(@Valid @RequestBody request: RequesLogin?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return loginUser.login(request)
    }
}
