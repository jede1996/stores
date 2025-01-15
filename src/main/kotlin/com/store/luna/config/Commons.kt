package com.store.luna.config

import org.springframework.http.ResponseEntity


interface DetalleMensajeDescriptor {
    val estatus: ResponseStatus
    val subcodigo: Int
    val mensajeDefault: String
}

data class Respuesta(
    val estatus: Int, val respuesta: DetallesResponseError?
)

data class DetallesResponseError(
    val codigo: String?, val mensaje: String?, val detalle: String?
)

enum class CatalogoResponses(
    override val estatus: ResponseStatus, override val subcodigo: Int, override val mensajeDefault: String
) : DetalleMensajeDescriptor {
    NOMBRE_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El nombre no lo mando"),
    AP_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El apellido paterno no lo mando"),
    AM_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El apellido materno no lo mando"),
    FN_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "La fecha de nacimiento no lo mando"),
    GENERO_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "La genero no lo mando"),
    NOTIFICACION_REQUERIDO(ResponseStatus.ERROR_PROTOCOLO, 0, "El estado de notificaciones no lo mando"),
    BODY_NULL(ResponseStatus.ERROR_PROTOCOLO, 0, "El request no puede ser nulo"),
    ERROR_INESPERADO(ResponseStatus.PETICION_INCORRECTA, 1, "Error inesperado"),
}

fun buildresponse(
    descripcion: CatalogoResponses, detalle: String = ""
): ResponseEntity<Any> {
    return ResponseEntity(
        Respuesta(
            descripcion.estatus.status, DetallesResponseError(
                "${descripcion.estatus.status}.${descripcion.subcodigo}", descripcion.mensajeDefault, detalle
            )
        ), descripcion.estatus.httpStatus
    )
}
