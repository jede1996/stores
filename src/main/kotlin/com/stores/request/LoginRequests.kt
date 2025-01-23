package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class RequesLogin(
    @field:NotNull(message = "PASS_REQUERIDO") @field:NotEmpty(message = "PASS_REQUERIDO") val contrasenna: String?,

    @field:NotNull(message = "USER_REQUERIDO") @field:NotEmpty(message = "USER_REQUERIDO") val usuario: String?,
)

class RequestBloqueoUsuario(
    @field:NotNull(message = "USER_REQUERIDO") @field:NotEmpty(message = "USER_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "TIPO_BLOQUEO_REQUERIDO") @field:NotEmpty(message = "TIPO_BLOQUEO_REQUERIDO") val bloqueo: String?,
    @field:NotNull(message = "STATUS_BLOQUEO_REQUERIDO") @field:NotEmpty(message = "STATUS_BLOQUEO_REQUERIDO") val estado: Boolean?,
)
