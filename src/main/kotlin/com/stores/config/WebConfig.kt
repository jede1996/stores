package com.stores.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ReadPreference
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.data.domain.Sort
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.index.Index
import org.springframework.data.mongodb.core.index.IndexOperations

@Configuration
class WebConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {
        return "store"
    }

    override fun mongoClient(): MongoClient {
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString(mongoString))
            .retryWrites(true)
            .writeConcern(WriteConcern.MAJORITY)
            .applicationName("store")
            .readPreference(ReadPreference.nearest())
            .build()
        return MongoClients.create(mongoClientSettings)
    }

    override fun mongoTemplate(databaseFactory: MongoDatabaseFactory, converter: MappingMongoConverter): MongoTemplate {
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return super.mongoTemplate(databaseFactory, converter)
    }
}

@Configuration
class MongoIndexConfig(
    @Autowired private val mongoTemplate: MongoTemplate
) {
    init {
        val indicesUsuarios: IndexOperations = mongoTemplate.indexOps("usuario")
        indicesUsuarios.ensureIndex(
            Index().on("telefonos.telefono", Sort.Direction.ASC).named("telefono").unique()
        )
        indicesUsuarios.ensureIndex(
            Index().on("correo_electronico.direccion", Sort.Direction.ASC).named("correo").unique()
        )
        indicesUsuarios.ensureIndex(
            Index().on("nombre", Sort.Direction.ASC).on("apellido_paterno", Sort.Direction.ASC)
            .on("apellido_materno", Sort.Direction.ASC).on("fecha_nacimiento", Sort.Direction.ASC)
            .named("datos_basicos").unique()
        )

        val indicesLunaVet: IndexOperations = mongoTemplate.indexOps("extendido_luna_vet")
        indicesLunaVet.ensureIndex(
            Index().on("nickname", Sort.Direction.ASC).named("nickname").unique()
        )

        val indicesSafariVet: IndexOperations = mongoTemplate.indexOps("extendido_safafi_vet")
        indicesSafariVet.ensureIndex(
            Index().on("nickname", Sort.Direction.ASC).named("nickname").unique()
        )

        val indicesCamaDelPerro: IndexOperations = mongoTemplate.indexOps("extendido_cama_del_perro")
        indicesCamaDelPerro.ensureIndex(
            Index().on("nickname", Sort.Direction.ASC).named("nickname").unique()
        )
    }
}