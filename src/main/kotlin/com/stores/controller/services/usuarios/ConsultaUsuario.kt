package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.repository.ClienteRepository
import com.stores.request.RequestConsultaUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaUsuario(
        request: RequestConsultaUsuario?
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de usuario: $request")

            val consultarRegistro = tracer.duration(Servicios().consultaUsuarioId, fun(): Boolean {
                return clienteRepository.findById(request!!.usuario!!).isPresent
            })

            return buildresponse(respuesta =  "")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}