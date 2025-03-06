package com.stores.request

import com.stores.entities.Vacunas
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.Date
import kotlin.collections.ArrayList

data class RequestConsultaMascota(
    @field:NotNull(message = "MASCOTA_REQUERIDO") @field:NotEmpty(message = "MASCOTA_REQUERIDO") val mascota: String?
)

data class RequestMascota(
    val propietario: String,
    val aplicacion: String,
    val foto: String,
    val idPropietario: String,
    val nombre: String,
    val especie: String?,
    val raza: String?,
    val genero: String,
    val edad: Int?,
    val caracteristicas: String,
    val esterilizado: Boolean,
    val chip: String,
    val peso: String?,
    val tamanno: String?,
    val vacunas: ArrayList<Vacunas?>?,
    val alergias: ArrayList<String?>?,
    val fechaNacimiento: Date?
)