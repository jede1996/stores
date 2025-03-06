package com.stores.controller.services.mascota

import com.stores.config.*
import com.stores.entities.Mascota
import com.stores.entities.UsersConsultado
import com.stores.entities.responseUsuarios
import com.stores.repository.MascotaRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ListadoMascota @Autowired constructor(
    private val tracer: ServiceInterceptor, private val mascotaRepository: MascotaRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun listadoMascotas(mascotaRepository: MascotaRepository): ResponseEntity<Respuesta> {
        try {
            logs.info("Servicio de listado de mascotas")

            val mascotasConsultadas = tracer.duration(Servicios().consultaUsuarioDatosBasicos, fun(): List<Mascota> {
                return mascotaRepository.findAll()
            })
            return buildresponse(respuesta = mascotasConsultadas)
        } catch (e: Exception) {
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}