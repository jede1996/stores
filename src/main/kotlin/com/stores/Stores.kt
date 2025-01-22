package com.stores

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class VetApplication

fun main(args: Array<String>) {
    runApplication<VetApplication>(*args)
}
