package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class RequestReportes(
    @field:NotNull(message = "FECHA_REQUERIDO") @field:NotEmpty(message = "FECHA_REQUERIDO") val fechaInicio: String?,
    @field:NotNull(message = "FECHA_REQUERIDO") @field:NotEmpty(message = "FECHA_REQUERIDO") val fechaFinal: String?
)
