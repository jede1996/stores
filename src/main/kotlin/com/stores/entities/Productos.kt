package com.stores.entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("producto")
data class Producto(
    @Field("_id") val producto: String,
    var nombre: String,
    var descripcion: String,
    var categoria: String,
    var subcategoria: String,
    var estado: String,
    var stock: Stock,
    var precios: Precios,
    var proveedor: String,
    @Field("fecha_registro") val fechaRegistro: Date,
    @Field("fecha_modificacion") var fechaModificacion: Date,
)

data class Stock(
    @Field("cantidad_disponible") val cantidadDisponible: String,
    @Field("unidad_medida") val unidadMedida: String,
    val maximo: String,
    val minimo: String,
)

data class Precios(
    @Field("precio_compra") val precioCompra: String,
    @Field("precio_venta") val precioVenta: String,
)

data class EstadoInventario(
    val bajo: String? = "bajo",
    val disponible: String = "disponible",
    val agotado: String = "agotado",
)