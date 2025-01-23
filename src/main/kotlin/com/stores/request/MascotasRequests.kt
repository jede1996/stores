package com.stores.request

import com.stores.Entities.Vacunas
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import kotlin.collections.ArrayList

class RequestConsultaMascota(
    @field:NotNull(message = "MASCOTA_REQUERIDO") @field:NotEmpty(message = "MASCOTA_REQUERIDO") val mascota: String?
)

data class RequestMascota(
    val idPropietario: String?,
    var nombre: String?,
    var especie: String?,
    var raza: String?,
    var genero: String?,
    var edad: Int?,
    var fechaNacimiento: Int?,
    var caracteristicas: String,
    var esterilizado: Boolean,
    var chip: String,
    var peso: String?,
    var tamanno: String?,
    var vacunas: ArrayList<Vacunas?>?,
    var alergias: ArrayList<String?>?
)