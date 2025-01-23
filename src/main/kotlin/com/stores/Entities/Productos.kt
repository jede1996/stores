package com.stores.Entities

import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.util.*

@Document("producto")
data class Producto(
    @Field("_id") val producto: String?,
    val nombre: String,
    val descripcion: String,
    val categoria: String,
    val subcategoria: String,
    val estado: String,
    val stock: Stock,
    val precios: Precios,
    val proveedor: String,
    @Field("fecha_registro") val fechaRegistro: Date?,
    @Field("fecha_modificacion") var fechaModificacion: Date?,
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