package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class RequestRegistroConsulta(
    val usuario: String,
    val cliente: String,
    val mascota: String,
    val motivo: String,
    val sintomas: String,
    val diagnostico: String,
    val tratamiento: String,
    val peso: String,
    val temperatura: String,
    val notasPropietario: String,
    val notasDoctor: String,
    val procedimientosRealizados: ArrayList<String>,
    val montoPorCobrar: Float,
    val pagado: Boolean,
    val agendado: Boolean,
    val duracion: String,
    val tipoServicio: String,
    val estadoCita: String,
    val aplicacion: String,
    val rolUsuario: String,
    val datosUsuarios: String
)

data class RequestBusquedaConsulta(
    @field:NotNull(message = "CONSULTA_REQUERIDO") @field:NotEmpty(message = "CONSULTA_REQUERIDO") val consulta: String? = null,
)

data class RequestProgramacionConsulta(
    @field:NotNull(message = "CONSULTA_REQUERIDO") @field:NotEmpty(message = "CONSULTA_REQUERIDO") val consulta: String,
    @field:NotNull(message = "FECHA_REQUERIDO") @field:NotEmpty(message = "FECHA_REQUERIDO") val fecha: Date,
)
