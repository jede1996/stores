package com.stores.controller.services.usuarios

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ClienteRepository
import com.stores.request.RequestActualizacionUsuario
import com.stores.request.RequestEnvioCodigo
import com.stores.request.RequestValidacionCodigo
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ModificacionUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionUsuario(
        request: RequestActualizacionUsuario?,
        clienteRepository: ClienteRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de actualizacion de usuario: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun envioCodigo(request: RequestEnvioCodigo?): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de envio de codigo de verificacion: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun validacionEmail(request: RequestValidacionCodigo?): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de validacion de email: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }

    fun actualizaContrasenna(request: RequestActualizacionUsuario?): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de actualizacion de contraseña: $request")

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO)
        }
    }
}