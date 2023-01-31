package com.pereira.restkotlinAPI

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.io.File
import java.io.PrintWriter
import java.nio.charset.Charset


class MapsApplication

fun main(args: Array<String>) {
val apiKey = "AIzaSyByS0LWKZd35s-FZTMVYQGci2aBRi35ZZ8"
	val context = GeoApiContext.Builder().apiKey(apiKey).build()

	val inputFile = File("C:\\temp\\enderecos5.csv")
	val addresses = inputFile.readLines(Charset.defaultCharset())

	val file = File("C:\\temp\\result5.csv")
	val writer = PrintWriter(file)
	writer.println("Endereço,Latitude,Longitude")
	for (address in addresses) {
		val parts = address.split(",")
		val street = parts[0]
		val city = parts[1]
		val state = parts[2]
		val addressWithState = "$street, $city, $state"
		val results: Array<GeocodingResult> = GeocodingApi.geocode(context, addressWithState).await()
		if (results.isNotEmpty()) {
			val location = results[0].geometry.location
			val line = "$address,${location.lat},${location.lng}"
			println("Endereço: $address | Latitude: ${location.lat} | Longitude: ${location.lng}")
			writer.println(line)
		} else {
			println("Nenhum resultado encontrado para o endereço $address")
		}
	}
	writer.close()
}


