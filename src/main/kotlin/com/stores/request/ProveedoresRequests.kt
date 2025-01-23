package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class RequestsRegistroProveedor(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String?,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String?,
    val apellidoMaterno: String?,
    @field:NotNull(message = "GENERO_REQUERIDO") @field:NotEmpty(message = "GENERO_REQUERIDO") val genero: String?,
    val telefono: String?,
    val correo: String?,
 )

class RequestActualizacionProveedor(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String?,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String?,
    val apellidoMaterno: String?,
    @field:NotNull(message = "GENERO_REQUERIDO") @field:NotEmpty(message = "GENERO_REQUERIDO") val genero: String?,
    val telefono: String?,
    val correo: String?,
   )

class RequestConsultaProveedor(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val proveedor: String?
)