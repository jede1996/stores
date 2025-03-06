package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RequestsRegistroProveedor(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val empresa: String,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String,
    val apellidoMaterno: String?,
    val telefono: String,
    val correo: String?,
    val aplicacion: String
 )

data class RequestConsultaProveedores(
    val aplicacion: String? = null
)

data class RequestConsultaProveedor(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val empresa: String? = null,
    val aplicacion: String
)

data class RequestsModificaProveedor(
    val idEmpresa: String,
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val empresa: String,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String,
    val apellidoMaterno: String?,
    val telefono: String,
    val correo: String?,
    val aplicacion: String
)