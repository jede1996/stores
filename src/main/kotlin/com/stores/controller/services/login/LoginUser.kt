package com.stores.controller.services.login

import com.stores.config.*
import com.stores.repository.ExtCamaDelPerroRepository
import com.stores.request.RequesLogin
import com.stores.responses.ResponseAuth
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.stereotype.Service


@Service
class LoginUser(
    private val jwtService: JwtService,
    private val tracer: ServiceInterceptor,
    private val authenticationManager: AuthenticationManager,
    private val camaDelPerroRepository: ExtCamaDelPerroRepository,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun login(request: RequesLogin): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de login: $request")
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    cifrado(request.usuario), request.contrasenna
                )
            )
            val user = camaDelPerroRepository.findByUserName(cifrado(request.usuario)).orElseThrow()
            return buildresponse(respuesta = ResponseAuth(jwtService.getToken(user)))
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}
