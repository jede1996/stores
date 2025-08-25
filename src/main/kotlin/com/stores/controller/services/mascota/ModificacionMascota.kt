package com.stores.controller.services.mascota

import com.stores.config.*
import com.stores.entities.Mascota
import com.stores.repository.MascotaRepository
import com.stores.request.RequestMascota
import com.stores.responses.ResponseMascota
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import java.util.*

@Service
class ModificacionMascota  @Autowired constructor(
    private val tracer : ServiceInterceptor,
    private val  mascotaRepository: MascotaRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun modificacionMascota(request: RequestMascota): ResponseEntity<Any>{
        try {
            logs.info("Request para el servicio de actualizacion de mascota: $request")

            if (!validaAplicaiones(request.aplicacion)) return buildresponse(error = CatalogoResponses.APLICACION_INVALIDA)

            val mascotaConsultada = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Mascota> {
                return mascotaRepository.findById(cifrado(request.idPropietario))
            })

            if (!mascotaConsultada.isPresent) return buildresponse(error = CatalogoResponses.MASCOTA_INEXISTENTE)

            val mascota: Mascota = mascotaConsultada.get()

            if (cifrado(mascota.propietario, false) != request.propietario) mascota.propietario = cifrado(request.propietario)
            if (cifrado(mascota.nombre, false) != request.nombre) mascota.nombre = cifrado(request.nombre)
            if (cifrado(mascota.especie, false) != request.especie) mascota.especie = cifrado(request.especie)
            if (cifrado(mascota.raza, false) != request.raza) mascota.raza = cifrado(request.raza)
            if (cifrado(mascota.genero, false) != request.genero) mascota.genero = cifrado(request.genero)
            if (mascota.edad != request.edad) mascota.edad = request.edad
            if (mascota.fechaNacimiento != request.fechaNacimiento) mascota.fechaNacimiento = request.fechaNacimiento
            if (cifrado(mascota.caracteristicas, false) != request.caracteristicas) mascota.caracteristicas = cifrado(request.caracteristicas)
            if (mascota.esterilizado != request.esterilizado) mascota.esterilizado = request.esterilizado
            if (cifrado(mascota.chip, false) != request.chip) mascota.chip = cifrado(request.chip)
            if (cifrado(mascota.peso, false) != request.peso) mascota.peso = cifrado(request.peso)
            if (cifrado(mascota.tamanno, false) != request.tamanno) mascota.tamanno = cifrado(request.tamanno)
            if (mascota.vacunas != request.vacunas) mascota.vacunas = request.vacunas
            if (mascota.alergias != request.alergias) mascota.alergias = request.alergias
            if (mascota.foto != request.foto) mascota.foto = cifrado(mascota.foto)
            mascota.fechaModificacion = Date()

            tracer.duration(Servicios().actualizacionMascota, fun() {
                mascotaRepository.save(mascota)
            })

            val mascotaUpdated = tracer.duration(Servicios().consultaMascotasPorId, fun(): Mascota {
                return mascotaRepository.findById(mascota.mascota).get()
            })

            val respuesta: ResponseMascota = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseMascota {
                return ResponseMascota(
                    mascotaUpdated.mascota,
                    mascotaUpdated.propietario,
                    mascotaUpdated.nombre,
                    mascotaUpdated.especie,
                    mascotaUpdated.raza,
                    mascotaUpdated.genero,
                    mascotaUpdated.edad,
                    mascotaUpdated.fechaNacimiento,
                    mascotaUpdated.caracteristicas,
                    mascotaUpdated.esterilizado,
                    mascotaUpdated.chip,
                    mascotaUpdated.peso,
                    mascotaUpdated.tamanno,
                    mascotaUpdated.vacunas,
                    mascotaUpdated.alergias,
                    mascotaUpdated.fechaRegistro,
                    mascotaUpdated.foto
                )
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        }catch (e: Exception){
            logs.error("Error al realizar la peticion: $e")
            return buildresponse(error =  CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

}