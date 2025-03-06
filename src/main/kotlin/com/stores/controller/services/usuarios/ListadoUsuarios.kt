package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.UsersConsultado
import com.stores.entities.responseUsuarios
import com.stores.repository.ClienteRepository
import com.stores.request.RequestConsultaUsuarios
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ListadoUsuarios @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val clienteRepository: ClienteRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun listadoUsuarios(
        request: RequestConsultaUsuarios
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de listado de usaurios: $request")

            val usuariosConsultado = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): List<UsersConsultado> {
                return clienteRepository.findByAllUsers(encrypt(request.aplicacion!!))
            })

            return buildresponse(respuesta =  usuariosConsultado)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}