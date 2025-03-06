package com.stores.controller

import com.stores.config.CatalogoResponses
import com.stores.config.Respuesta
import com.stores.config.buildresponse
import com.stores.controller.services.proveedores.*
import com.stores.repository.ProveedorRepository
import com.stores.request.*
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("proveedor")
class ProveedoresController(
    val proveedorRepository: ProveedorRepository,
    val registroProveedores: RegistroProveedores,
    val bajaProveedores: BajaProveedores,
    val modificacionProveedores: ModificacionProveedores,
    val consultaProveedores: ConsultaProveedores,
    val listadoProveedores: ListadoProveedores
) {
    @PostMapping("registro")
    fun registroProveedores(@Valid @RequestBody request: RequestsRegistroProveedor?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return registroProveedores.registroProveedores(request)
    }

    @PostMapping("baja")
    fun bajaProveedores(@Valid @RequestBody request: RequestConsultaProveedor?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return bajaProveedores.bajaProveedores(request)
    }

    @PostMapping("modificacion")
    fun modificacionProveedor(@Valid @RequestBody request: RequestsModificaProveedor?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return modificacionProveedores.modificacionProveedores(request)
    }

    @PostMapping("consulta")
    fun consultaProveedor(@Valid @RequestBody request: RequestConsultaProveedor?): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return consultaProveedores.consultaProveedor(request)
    }

    @PostMapping("listado")
    fun listadoProveedores(request: RequestConsultaProveedores): ResponseEntity<Respuesta> {
        if (request == null) return buildresponse(error = CatalogoResponses.BODY_NULL)
        return listadoProveedores.listadoProveedores(request)
    }
}