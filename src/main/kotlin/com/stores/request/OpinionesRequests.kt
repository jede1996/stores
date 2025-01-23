package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


class RequestRegistroOpinion(
    val userId: String?,
    val comentario: String?,
    var aplicacion: String?,
    var calificacion: Int?,
)

class RequestOpiniones(
    @field:NotNull(message = "ID_REQUERIDO") @field:NotEmpty(message = "ID_REQUERIDO") val id: String?
)