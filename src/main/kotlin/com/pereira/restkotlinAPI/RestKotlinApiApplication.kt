package com.pereira.restkotlinAPI

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.sql.Connection
import java.sql.DriverManager

@SpringBootApplication
class RestKotlinApiApplication

fun main(args: Array<String>) {
	runApplication<RestKotlinApiApplication>(*args)
}
