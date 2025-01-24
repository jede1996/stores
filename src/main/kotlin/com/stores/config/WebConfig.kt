package com.stores.config

import com.mongodb.ConnectionString
import com.mongodb.MongoClientSettings
import com.mongodb.ReadPreference
import com.mongodb.WriteConcern
import com.mongodb.client.MongoClient
import com.mongodb.client.MongoClients
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper
import org.springframework.data.mongodb.core.convert.MappingMongoConverter

@Configuration
class WebConfig : AbstractMongoClientConfiguration() {
    override fun getDatabaseName(): String {
        return "store"
    }

    override fun mongoClient(): MongoClient {
        val mongoClientSettings = MongoClientSettings.builder()
            .applyConnectionString(ConnectionString("mongodb+srv://store:store@store.ymz8d.mongodb.net"))
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
