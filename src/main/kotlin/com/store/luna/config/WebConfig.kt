package com.store.luna.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.http.HttpStatus

@Configuration
class WebConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {
        return "samwell"
    }

    override fun mongoClient(): MongoClient {

        val mongoConnection = "mongodb://localhost:27017/admin"
        val connectionString = ConnectionString(mongoConnection)
        val mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build()
        return MongoClients.create(mongoClientSettings)
    }

    override fun mongoTemplate(databaseFactory: MongoDatabaseFactory, converter: MappingMongoConverter): MongoTemplate {
        converter.setTypeMapper(DefaultMongoTypeMapper(null))
        return super.mongoTemplate(databaseFactory, converter)
    }
}

enum class ResponseStatus(val status: Int, val httpStatus: HttpStatus) {
    ERROR_INESPERADO(-2, HttpStatus.INTERNAL_SERVER_ERROR),
    ERROR_PROTOCOLO(-1, HttpStatus.INTERNAL_SERVER_ERROR),
    EXITO(0, HttpStatus.OK),
    PETICION_INCORRECTA(2, HttpStatus.OK),
}

data class Respuestas(
    val codigo: Int,
    val httpstatus: HttpStatus
)