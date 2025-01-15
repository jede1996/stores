package com.store.luna.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*


@Document("consultas")
data class Consultas(
    @Field("_id")
    val id: String?,
    val idVeterinario: String,
    val idPropietario: String,
    val idMascota: String,
    val motivo: String,
    val sintomas: String,
    val diagnostico: String,
    val tratamiento: String,
    val peso: String,
    val temperatura: String,
    val notasPropietario: String,
    val notasDoctor: String,
    val procedimientosRealizados: String,
    val montoPorCobrar: String,
    val pagado: Boolean,
    val agendado: Boolean,
    val duracion: String?,
    val tipoServicio: String,
    val estadoCita: String,
    @Field("fecha_agenda")
    val fechaAgenda: Date?
)

data class Procedimientos(
    val consulta: String = "consulta",
    val vacunacion: String = "vacunacion",
    val cirugia: String = "cirugia.",
    val desparacitacion: String = "desparacitacion"
)

data class EstadoCita(
    val pendiente: String = "Registrada, pero no confirmada por el veterinario.",
    val confirmada: String = "Aprobada y asignada a un veterinario.",
    val cancelada: String = "Anulada por el propietario o el sistema.",
    val completada: String = "La cita se llevó a cabo."
)