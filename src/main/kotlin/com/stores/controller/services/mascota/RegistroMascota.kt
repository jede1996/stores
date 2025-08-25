package com.stores.controller.services.mascota

import com.stores.config.*
import com.stores.entities.Mascota
import com.stores.entities.Usuario
import com.stores.repository.ClienteRepository
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
class RegistroMascota @Autowired constructor(
    private val tracer: ServiceInterceptor,
    private val mascotaRepository: MascotaRepository,
    private val clienteRepository: ClienteRepository
) {
    private val logs: Logger = LoggerFactory.getLogger(this::class.java)

    fun registroMascota(request: RequestMascota): ResponseEntity<Any> {
        var registroNuevo = false
        val idMascota = cifrado(UUID.randomUUID().toString().replace("-", ""))
        try {
            logs.info("Request para el servicio de registro de mascotas: $request")

            val usuarioConsultado = tracer.duration(Servicios().consultaUsuarioId, fun(): Optional<Usuario> {
                return clienteRepository.findById(cifrado(request.idPropietario))
            })

            if (!usuarioConsultado.isPresent) return buildresponse(error = CatalogoResponses.USUARIO_INEXISTENTE)

            val mascotasConsultadas = tracer.duration(Servicios().consultaMascotasPorusuario, fun(): List<Mascota> {
                return mascotaRepository.findByAllMascotasByUser(usuarioConsultado.get().usuario)
            })

            mascotasConsultadas.forEach {
                if (it.nombre == request.nombre) return buildresponse(error = CatalogoResponses.MASCOTA_EXISTENTE)
            }

            registroNuevo = true
            mascotaRepository.save(
                Mascota(
                    idMascota,
                    cifrado(usuarioConsultado.get().usuario),
                    cifrado(request.nombre),
                    cifrado(request.especie),
                    cifrado(request.raza),
                    cifrado(request.genero),
                    request.edad,
                    request.fechaNacimiento,
                    cifrado(request.caracteristicas),
                    request.esterilizado,
                    cifrado(request.chip),
                    cifrado(request.peso),
                    cifrado(request.tamanno),
                    request.vacunas,
                    request.alergias,
                    null,
                    Date(),
                    Date()
                )
            )

            val mascota = tracer.duration(Servicios().consultaMascotasPorId, fun(): Mascota {
                return mascotaRepository.findById(idMascota).get()
            })

            val respuesta: ResponseMascota = tracer.duration(Servicios().preparacionRespuesta, fun(): ResponseMascota {
                return ResponseMascota(
                    mascota.mascota,
                    mascota.propietario,
                    mascota.nombre,
                    mascota.especie,
                    mascota.raza,
                    mascota.genero,
                    mascota.edad,
                    mascota.fechaNacimiento,
                    mascota.caracteristicas,
                    mascota.esterilizado,
                    mascota.chip,
                    mascota.peso,
                    mascota.tamanno,
                    mascota.vacunas,
                    mascota.alergias,
                    mascota.fechaRegistro,
                    mascota.foto
                )
            })

            logs.info("Informacion a regresar: $respuesta")
            return buildresponse(respuesta = respuesta)
        } catch (e: Exception) {
            if (registroNuevo) {
                anulaRegistro(idMascota)
            }
            logs.error("Error al realizar la peticion: ${e.message}")
            if (e.message!!.contains("duplicate key error collection")) {
                return buildresponse(error = CatalogoResponses.VALOR_EXISTENTE, detalle = regresaLlaveDuplicada(e))
            }
            return buildresponse(error = CatalogoResponses.ERROR_INESPERADO, detalle = e.message)
        }
    }

    fun anulaRegistro(idUser: String) {
        tracer.duration(Servicios().anulaRegistro, fun() {
            mascotaRepository.deleteById(idUser)
        })
    }

}