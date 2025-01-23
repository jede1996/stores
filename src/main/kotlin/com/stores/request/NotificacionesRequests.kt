package com.stores.request

import com.stores.Entities.Programacion
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


class RequestConsultaNotificaciones(
    val id: String?,
    val aplicaion: String?,
)

data class RequestNotificaciones(
    @field:NotNull(message = "APLICACION_REQUERIDO") @field:NotEmpty(message = "APLICACION_REQUERIDO") val aplicacion: String?,
    @field:NotNull(message = "ASUNTO_REQUERIDO") @field:NotEmpty(message = "ASUNTO_REQUERIDO") val asunto: String?,
    @field:NotNull(message = "MENSAJE_REQUERIDO") @field:NotEmpty(message = "MENSAJE_REQUERIDO") val mensaje: String?,
    @field:NotNull(message = "PROGRAMACION_REQUERIDO") @field:NotEmpty(message = "PROGRAMACION_REQUERIDO") val programacion: Programacion,
)