package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

data class RequestExt(
    @field:NotNull(message = "ID_REQUERIDO") @field:NotEmpty(message = "ID_REQUERIDO") val id: String?,
    @field:NotNull(message = "NICKNAME_REQUERIDO") @field:NotEmpty(message = "NICKNAME_REQUERIDO") var nickname: String,
    @field:NotNull(message = "PASS_REQUERIDO") @field:NotEmpty(message = "PASS_REQUERIDO") var contrasenna: String
)

data class RequestBusquedaExt(
    @field:NotNull(message = "ID_REQUERIDO") @field:NotEmpty(message = "ID_REQUERIDO") val id: String?
)
