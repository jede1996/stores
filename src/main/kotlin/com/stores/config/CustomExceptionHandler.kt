package com.stores.config

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

data class ErrorResponse(
    val message: String?, val details: List<String?>?,
)

@ControllerAdvice
class CustomExceptionHandler : ResponseEntityExceptionHandler() {
    @ExceptionHandler(Exception::class)
    fun handleAllExceptions(ex: Exception, request: WebRequest?): ResponseEntity<Any?> {
        val details: MutableList<String?> = ArrayList()
        details.add(ex.localizedMessage)
        val error = ErrorResponse("Server Error", details)
        return ResponseEntity(error, HttpStatus.INTERNAL_SERVER_ERROR)
    }

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException, headers: HttpHeaders, status: HttpStatusCode, request: WebRequest,
    ): ResponseEntity<Any>? {
        var errorMessage: String? = ""
        for (error in ex.bindingResult.allErrors) {
            errorMessage = error.defaultMessage.toString()
            break
        }
        return when (errorMessage!!.toString()) {
            "CONSULTA_REQUERIDO" -> buildresponse(error = CatalogoResponses.CONSULTA_REQUERIDO) as ResponseEntity<Any>?
            "NICKNAME_REQUERIDO" -> buildresponse(error = CatalogoResponses.NICKNAME_REQUERIDO) as ResponseEntity<Any>?
            "TIPO_BLOQUEO_REQUERIDO" -> buildresponse(error = CatalogoResponses.TIPO_BLOQUEO_REQUERIDO) as ResponseEntity<Any>?
            "STATUS_BLOQUEO_REQUERIDO" -> buildresponse(error = CatalogoResponses.STATUS_BLOQUEO_REQUERIDO) as ResponseEntity<Any>?
            "MASCOTA_REQUERIDO" -> buildresponse(error = CatalogoResponses.MASCOTA_REQUERIDO) as ResponseEntity<Any>?
            "PASS_REQUERIDO" -> buildresponse(error = CatalogoResponses.PASS_REQUERIDO) as ResponseEntity<Any>?
            "CODIGO_REQUERIDO" -> buildresponse(error = CatalogoResponses.CODIGO_REQUERIDO) as ResponseEntity<Any>?
            "MAIL_REQUERIDO" -> buildresponse(error = CatalogoResponses.MAIL_REQUERIDO) as ResponseEntity<Any>?
            "APLICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.APLICACION_REQUERIDO) as ResponseEntity<Any>?
            "ASUNTO_REQUERIDO" -> buildresponse(error = CatalogoResponses.ASUNTO_REQUERIDO) as ResponseEntity<Any>?
            "MENSAJE_REQUERIDO" -> buildresponse(error = CatalogoResponses.MENSAJE_REQUERIDO) as ResponseEntity<Any>?
            "PROGRAMACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.PROGRAMACION_REQUERIDO) as ResponseEntity<Any>?
            "USUARIO_REQUERIDO" -> buildresponse(error = CatalogoResponses.USUARIO_REQUERIDO) as ResponseEntity<Any>?
            "COMENTARIO_REQUERIDO" -> buildresponse(error = CatalogoResponses.COMENTARIO_REQUERIDO) as ResponseEntity<Any>?
            "CALIFICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.CALIFICACION_REQUERIDO) as ResponseEntity<Any>?
            "OPINION_REQUERIDO" -> buildresponse(error = CatalogoResponses.OPINION_REQUERIDO) as ResponseEntity<Any>?
            "PRODUCTO_REQUERIDO" -> buildresponse(error = CatalogoResponses.PRODUCTO_REQUERIDO) as ResponseEntity<Any>?
            "NOMBRE_REQUERIDO" -> buildresponse(error = CatalogoResponses.NOMBRE_REQUERIDO) as ResponseEntity<Any>?
            "AP_REQUERIDO" -> buildresponse(error = CatalogoResponses.AP_REQUERIDO) as ResponseEntity<Any>?
            "GENERO_REQUERIDO" -> buildresponse(error = CatalogoResponses.GENERO_REQUERIDO) as ResponseEntity<Any>?
            "FECHA_REQUERIDO" -> buildresponse(error = CatalogoResponses.GENERO_REQUERIDO) as ResponseEntity<Any>?
            "FN_REQUERIDO" -> buildresponse(error = CatalogoResponses.FN_REQUERIDO) as ResponseEntity<Any>?
            "NOTIFICACION_REQUERIDO" -> buildresponse(error = CatalogoResponses.NOTIFICACION_REQUERIDO) as ResponseEntity<Any>?
            else -> buildresponse(error = CatalogoResponses.ERROR_INESPERADO) as ResponseEntity<Any>?
        }
    }
}
