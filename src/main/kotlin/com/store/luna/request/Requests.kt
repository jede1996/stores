package com.store.luna.request

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

class RequestsRegistroUsuario(
    @field:NotNull(message = "NOMBRE_REQUERIDO")
    @field:NotEmpty(message = "NOMBRE_REQUERIDO")
    val nombre: String?,

    @field:NotNull(message = "AP_REQUERIDO")
    @field:NotEmpty(message = "AP_REQUERIDO")
    val apellidoPaterno: String?,

    @field:NotNull(message = "AM_REQUERIDO")
    @field:NotEmpty(message = "AM_REQUERIDO")
    val apellidoMaterno: String?,

    @field:NotNull(message = "FN_REQUERIDO")
    @field:NotEmpty(message = "FN_REQUERIDO")
    val fechaNacimiento: String?,

    @field:NotNull(message = "GENERO_REQUERIDO")
    @field:NotEmpty(message = "GENERO_REQUERIDO")
    val genero: String?,

    val telefono: String?,

    val correo: String?,

    @field:NotNull(message = "NOTIFICACION_REQUERIDO")
    val notificaciones: Boolean?
    )