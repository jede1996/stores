package com.stores.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RequestsRegistroUsuario(
    @field:NotNull(message = "NOMBRE_REQUERIDO") @field:NotEmpty(message = "NOMBRE_REQUERIDO") val nombre: String,
    @field:NotNull(message = "AP_REQUERIDO") @field:NotEmpty(message = "AP_REQUERIDO") val apellidoPaterno: String,
    val apellidoMaterno: String?,
    @field:NotNull(message = "FN_REQUERIDO") @field:NotEmpty(message = "FN_REQUERIDO") val fechaNacimiento: String,
    @field:NotNull(message = "GENERO_REQUERIDO") @field:NotEmpty(message = "GENERO_REQUERIDO") val genero: String,
    val telefono: String?,
    val correo: String?,
    val rol: String,
    val nickname: String,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String,
    @field:NotNull(message = "NOTIFICACION_REQUERIDO") val notificaciones: Boolean,
)

data class RequestEnvioCodigo(
    @field:NotNull(message = "TIPO_REQUERIDO") @field:NotEmpty(message = "TIPO_REQUERIDO") val tipo: String,
    @field:NotNull(message = "MEDIO_REQUERIDO") @field:NotEmpty(message = "MEDIO_REQUERIDO") val medio: String
)

data class RequestValidacionCodigo(
    @field:NotNull(message = "TIPO_REQUERIDO") @field:NotEmpty(message = "TIPO_REQUERIDO") val tipo: String,
    @field:NotNull(message = "MEDIO_REQUERIDO") @field:NotEmpty(message = "MEDIO_REQUERIDO") val medio: String,
    @field:NotNull(message = "CODIGO_REQUERIDO") @field:NotEmpty(message = "CODIGO_REQUERIDO") val codigo: String
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
    val nickname: String,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String,
    @field:NotNull(message = "NOTIFICACION_REQUERIDO") val notificaciones: Boolean,
)

data class RequestActualizacionContrasenna(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String,
    @field:NotNull(message = "PASS_REQUERIDO") @field:NotEmpty(message = "PASS_REQUERIDO") val contrasennaNueva: String,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String,
    @field:NotNull(message = "NOTIFICACION_REQUERIDO") val notificaciones: Boolean,
)

data class RequestConsultaUsuario(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String,
)

data class RequestConsultaUsuarios(
    @field:NotBlank(message = "APLICACION_REQUERIDO") val aplicacion: String? = null
)