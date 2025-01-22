package com.stores.config

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

data class DetallesResponseError(
    val codigo: String?, val mensaje: String?, val detalle: String?
)

data class Respuesta(
    val status: Int, val respuesta: Any
)

enum class ResponseStatus(val status: Int, val httpStatus: HttpStatus) {
    ERROR_INESPERADO(-2, HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_PROTOCOLO(-1, HttpStatus.INTERNAL_SERVER_ERROR),
    EXITO(0, HttpStatus.OK),
    PETICION_INCORRECTA(2, HttpStatus.OK),
}

enum class CatalogoResponses(
    val estatus: ResponseStatus, val subcodigo: Int, val mensajeDefault: String
) {
    NOMBRE_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO,
        0,
        "El nombre no lo mando"
    ),
    AP_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO,
        0,
        "El apellido paterno no lo mando"
    ),
    AM_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO,
        0,
        "El apellido materno no lo mando"
    ),
    FN_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "La fecha de nacimiento no lo mando"), GENERO_REQUERIDO(
        ResponseStatus.ERROR_PROTOCOLO,
        0,
        "La genero no lo mando"
    ),
    NOTIFICACION_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El estado de notificaciones no lo mando"), BODY_NULL(
        ResponseStatus.ERROR_PROTOCOLO,
        0,
        "El request no puede ser nulo"
    ),
    ERROR_INESPERADO(ResponseStatus.PETICION_INCORRECTA, 1, "Error inesperado"),
}

fun buildresponse(
    response: Any? = null,
    descripcion: CatalogoResponses? = null,
    detalle: String = ""
): ResponseEntity<Any> {
    if (response != null) return ResponseEntity(Respuesta(0, response), ResponseStatus.EXITO.httpStatus)
    return ResponseEntity(
        Respuesta(
            descripcion!!.estatus.status, DetallesResponseError(
                "${descripcion.estatus.status}.${descripcion.subcodigo}", descripcion.mensajeDefault, detalle
            )
        ), descripcion.estatus.httpStatus
    )
}
