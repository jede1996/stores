package com.stores.controller.services.usuarios

import com.stores.config.*
import com.stores.repository.ClienteRepository
import com.stores.request.RequestActualizacionUsuario
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class BajaUsuario @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaUsuario(
        request: RequestActualizacionUsuario?,
        clienteRepository: ClienteRepository
    ): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de eliminacion de usuario: $request")
            val consultarRegistro = tracer.duration(Servicios().consultaUsuarioId, fun(): Boolean {
                return clienteRepository.findById(request!!.usuario!!).isPresent
            })

            if (consultarRegistro) {
                tracer.duration(Servicios().eliminaUsuario, fun() {
                    return clienteRepository.deleteById(request!!.usuario!!)
                })
            }

            return buildresponse(respuesta =  "Eliminado")
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }
}