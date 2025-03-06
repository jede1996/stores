package com.stores.controller.services.consultas

import com.stores.config.*
import com.stores.repository.ConsultasRepository
import com.stores.request.RequestRegistroConsulta
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class RegistroConsultas @Autowired constructor(
    private val tracer: ServiceInterceptor,
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroConsulta(
        request: RequestRegistroConsulta,
        consultasRepository: ConsultasRepository,
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de registro de consulta: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            if (!validaRoles(request.rolUsuario)) return buildresponse(error = CatalogoResponses.ROL_INVALIDO)


            return buildresponse(respuesta =  "Registrado")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}
