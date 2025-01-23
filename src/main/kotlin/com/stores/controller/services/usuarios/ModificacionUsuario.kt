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
class ModificacionUsuario  @Autowired constructor(
    private val tracer : ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionUsuario(request: RequestActualizacionUsuario?, clienteRepository: ClienteRepository): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun envioCodigo(request: RequestEnvioCodigo?): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun validacionEmail(request: RequestValidacionCodigo?): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }

    fun actualizaContrasenna(request: RequestActualizacionUsuario?): ResponseEntity<Respuesta> {
        return buildresponse(descripcion = CatalogoResponses.BODY_NULL)
    }
}