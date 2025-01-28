package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.entities.Usuario
import com.stores.repository.ClienteRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ListadoUsuarios @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun listadoUsuarios(clienteRepository: ClienteRepository): ResponseEntity<Respuesta> {
        try {
            logs.info("Servicio de listado de usaurios")

            val listaUsuario = tracer.duration(Servicios().consultaUsuarioId, fun(): MutableList<Usuario> {
                return clienteRepository.findAll()
            })

            return buildresponse(respuesta =  listaUsuario)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}