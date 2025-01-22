package com.stores.repository

import com.stores.Entities.*
import org.springframework.data.mongodb.repository.MongoRepository

interface ConsultasRepository: MongoRepository<Consultas, String>
interface ExtLunaVetRepository: MongoRepository<ExtLunaVet, String>
interface ExtCamaDelPerroRepository: MongoRepository<ExtCamaDelPerro, String>
interface ProductoRepository: MongoRepository<Producto, String>
interface MascotaRepository: MongoRepository<Mascota, String>
interface ProveedorRepository: MongoRepository<Proveedor, String>
interface ClienteRepository: MongoRepository<Usuario, String>