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
class BajaMascota @Autowired constructor(
    private val tracer: ServiceInterceptor, private val mascotaRepository: MascotaRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun bajaMascota(request: RequestConsultaMascota): ResponseEntity<Respuesta> {
        try {
            logs.info("Request para el servicio de eliminacion de mascota: $request")

            val mascotaConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Mascota> {
                return mascotaRepository.findById(cifrado(request.mascota))
            })

            if (!mascotaConsultada.isPresent) return buildresponse(error = CatalogoResponses.MASCOTA_INEXISTENTE)


            tracer.duration(Servicios().eliminaUsuario, fun() {
                return mascotaRepository.deleteById(mascotaConsultada.get().mascota)
            })

            logs.info("Informacion a regresar: Mascota eliminada")
            return buildresponse(respuesta = "Mascota eliminada")

        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}