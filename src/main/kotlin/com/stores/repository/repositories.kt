package com.stores.repository

import com.stores.entities.*
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import java.util.*

interface ConsultasRepository : MongoRepository<Consultas, String>
interface ExtLunaVetRepository : MongoRepository<ExtLunaVet, String>{
    @Query(value = "{ usernameExt: ?0 }")
    fun findByUserName(username: String): Optional<ExtLunaVet>
}

interface ExtSafariVetRepository : MongoRepository<ExtSafariVet, String>{
    @Query(value = "{ usernameExt: ?0 }")
    fun findByUserName(username: String): Optional<ExtSafariVet>
}

interface ExtCamaDelPerroRepository : MongoRepository<ExtCamaDelPerro, String>{
    @Query(value = "{ usernameExt: ?0 }")
    fun findByUserName(username: String): Optional<ExtCamaDelPerro>
}

interface ProductoRepository : MongoRepository<Producto, String>{
    @Query(value = "{ nombre: ?0 }")
    fun findByNombre(nombre: String): Optional<Producto>
}

interface MascotaRepository : MongoRepository<Mascota, String>{
    @Query(value = "{ propietario: ?0 }")
    fun findByAllMascotasByUser(propietario: String): List<Mascota>
}

interface ProveedorRepository : MongoRepository<Proveedor, String>{
    @Query(value = "{ aplicacion: ?0, empresa: ?1 }")
    fun findByAplicacion(aplicacion: String, empresa: String): Optional<Proveedor>

    @Query(value = "{ aplicacion: ?0")
    fun findAllByAplicacion(aplicacion: String): List<Proveedor>
}

interface ClienteRepository : MongoRepository<Usuario, String> {
    @Query("{ nombre: ?0, apellido_paterno: ?1, apellido_materno: ?2, fecha_nacimiento: ?3 }")
    fun findByBatosBasicos(
        nombre: String,
        apellidoPaterno: String,
        apellidoMaterno: String,
        fechaNacimiento: String,
    ): Optional<Usuario>

    @Query(
        value = "{ aplicacion: ?0 }",
        fields = "{  'nombre': 1, 'apellido_paterno': 1, 'apellido_materno': 1, 'telefono.telefono': 1, 'correo.direccion': 1 }"
    )
    fun findByAllUsers(aplicacion: String): List<UsersConsultado>
}

interface OpinionesRepository : MongoRepository<Opiniones, String>
interface NotificacionesRepository : MongoRepository<Notificaciones, String>