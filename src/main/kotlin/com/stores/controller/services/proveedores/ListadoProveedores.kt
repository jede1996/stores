package com.stores.controller.services.proveedores

import com.stores.config.*
import com.stores.entities.Proveedor
import com.stores.entities.UsersConsultado
import com.stores.entities.responseUsuarios
import com.stores.repository.ProveedorRepository
import com.stores.request.RequestConsultaProveedores
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ListadoProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val proveedorRepository: ProveedorRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun listadoProveedores(request: RequestConsultaProveedores): ResponseEntity<Respuesta> {
        try {
            logs.info("Servicio de listado de proveedores")

            val proveedoresConsultados = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): List<Proveedor> {
                return proveedorRepository.findAllByAplicacion(encrypt(request.aplicacion))
            })

            return buildresponse(respuesta =  proveedoresConsultados)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}