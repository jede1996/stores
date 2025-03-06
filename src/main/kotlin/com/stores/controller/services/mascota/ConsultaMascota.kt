package com.stores.controller.services.mascota

import com.stores.config.*
import com.stores.entities.Mascota
import com.stores.repository.MascotaRepository
import com.stores.request.RequestConsultaMascota
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ConsultaMascota @Autowired constructor(
    private val tracer: ServiceInterceptor, private val mascotaRepository: MascotaRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun consultaMascota(request: RequestConsultaMascota): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de consulta de mascotas: $request")

            val mascotaConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Mascota> {
                return mascotaRepository.findById(encrypt(request.mascota))
            })

            if (!mascotaConsultada.isPresent) return buildresponse(error = CatalogoResponses.MASCOTA_INEXISTENTE)


            val respuesta: Mascota = tracer.duration(Servicios().preparacionRespuesta, fun(): Mascota {
                return mascotaConsultada.get()
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}