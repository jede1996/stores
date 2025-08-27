package com.stores.controller.services.login

import com.stores.config.*
import com.stores.request.RequesLogin
import com.stores.responses.ResponseAuth
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service


@Service
class LoginUser(
    private val jwtService: JwtService,
    private val tracer: ServiceInterceptor,
    private val authenticationManager: AuthenticationManager
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun login(request: RequesLogin): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de login: $request")

            val oauth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(
                "${cifrado(request.usuario)}:${request.aplicacion}", request.contrasenna))

            return buildresponse(respuesta = ResponseAuth(jwtService.getToken(oauth.principal as UserDetails)))
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}
