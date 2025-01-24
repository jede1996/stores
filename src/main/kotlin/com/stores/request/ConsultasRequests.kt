package com.stores.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.util.*

data class RequestRegistroConsulta(
    val usuario: String?,
    val cliente: String?,
    val mascota: String?,
    val motivo: String?,
    val sintomas: String?,
    val diagnostico: String?,
    val tratamiento: String?,
    val peso: String?,
    val temperatura: String?,
    val notasPropietario: String?,
    val notasDoctor: String?,
    val procedimientosRealizados: String?,
    val montoPorCobrar: String?,
    val pagado: Boolean?,
    val agendado: Boolean?,
    val duracion: String?,
    val tipoServicio: String?,
    val estadoCita: String?,
)

data class RequestBusquedaConsulta(
    @field:NotNull(message = "CONSULTA_REQUERIDO") @field:NotEmpty(message = "CONSULTA_REQUERIDO") val consulta: String?,
)

data class RequestProgramacionConsulta(
    @field:NotNull(message = "CONSULTA_REQUERIDO") @field:NotEmpty(message = "CONSULTA_REQUERIDO") val consulta: String?,
    @field:NotNull(message = "FECHA_REQUERIDO") @field:NotEmpty(message = "FECHA_REQUERIDO") val fecha: Date?,
)
