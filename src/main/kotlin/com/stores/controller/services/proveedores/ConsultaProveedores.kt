package com.stores.controller.services.proveedores

import com.stores.config.Respuesta
import com.stores.config.ServiceInterceptor
import com.stores.config.buildresponse
import com.stores.repository.ProveedorRepository
import com.stores.request.RequestConsultaProveedor
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ConsultaProveedores @Autowired constructor(
    private val tracer: ServiceInterceptor
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaProveedor(
        request: RequestConsultaProveedor?, proveedorRepository: ProveedorRepository
    ): ResponseEntity<Respuesta> {
        return buildresponse(respuesta = "")
    }
}