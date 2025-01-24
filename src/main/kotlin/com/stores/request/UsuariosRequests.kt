package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RequestsRegistroUsuario(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String,
    val apellidoMaterno: String,
    @field:NotNull(message = "FN_REQUERIDO") @field:NotEmpty(message = "FN_REQUERIDO") val fechaNacimiento: String?,
    @field:NotNull(message = "GENERO_REQUERIDO") @field:NotEmpty(message = "GENERO_REQUERIDO") val genero: String?,
    val telefono: String?,
    val correo: String?,
    val rol: String?,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String?,
    @field:NotNull(message = "NOTIFICACION_REQUERIDO") val notificaciones: Boolean,
)

data class RequestEnvioCodigo(
    @field:NotNull(message = "MAIL_REQUERIDO") @field:NotEmpty(message = "MAIL_REQUERIDO") val correo: String?,
)

data class RequestValidacionCodigo(
    @field:NotNull(message = "MAIL_REQUERIDO") @field:NotEmpty(message = "MAIL_REQUERIDO") val correo: String?,
    @field:NotNull(message = "CODIGO_REQUERIDO") @field:NotEmpty(message = "CODIGO_REQUERIDO") val codigo: String?,
)

data class RequestActualizacionUsuario(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "PASS_REQUERIDO") @field:NotEmpty(message = "PASS_REQUERIDO") val contrasennaNueva: String?,
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String?,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String?,
    val apellidoMaterno: String?,
    @field:NotNull(message = "FN_REQUERIDO") @field:NotEmpty(message = "FN_REQUERIDO") val fechaNacimiento: String?,
    @field:NotNull(message = "GENERO_REQUERIDO") @field:NotEmpty(message = "GENERO_REQUERIDO") val genero: String?,
    val telefono: String?,
    val correo: String?,
    val rol: String?,
    @field:NotNull(message = "NOTIFICACION_REQUERIDO") val notificaciones: Boolean?,
)

data class RequestConsultaUsuario(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
)