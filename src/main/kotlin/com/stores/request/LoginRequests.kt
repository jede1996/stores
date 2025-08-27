package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class RequesLogin(
    @field:NotNull(message = "PASS_REQUERIDO") @field:NotEmpty(message = "PASS_REQUERIDO") val contrasenna: String?,
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "APP_REQUERIDA") @field:NotEmpty(message = "APP_REQUERIDA") val aplicacion: String?,
)

data class RequestBloqueoUsuario(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "TIPO_BLOQUEO_REQUERIDO") @field:NotEmpty(message = "TIPO_BLOQUEO_REQUERIDO") val bloqueo: String?,
    @field:NotNull(message = "STATUS_BLOQUEO_REQUERIDO") @field:NotEmpty(message = "STATUS_BLOQUEO_REQUERIDO") val estado: Boolean?
)