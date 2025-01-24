package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull


data class RequestRegistroOpinion(
    @field:NotNull(message = "USUARIO_REQUERIDO") @field:NotEmpty(message = "USUARIO_REQUERIDO") val usuario: String?,
    @field:NotNull(message = "COMENTARIO_REQUERIDO") @field:NotEmpty(message = "COMENTARIO_REQUERIDO") val comentario: String?,
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") var aplicacion: String?,
    @field:NotNull(message = "CALIFICACION_REQUERIDO") @field:NotEmpty(message = "CALIFICACION_REQUERIDO") var calificacion: Int?,
)

data class RequestOpiniones(
    @field:NotNull(message = "OPINION_REQUERIDO") @field:NotEmpty(message = "ID_REQUERIDO") val opinion: String?
)