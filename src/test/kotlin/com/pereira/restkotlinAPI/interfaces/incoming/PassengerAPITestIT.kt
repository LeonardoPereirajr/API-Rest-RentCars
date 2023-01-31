package com.pereira.restkotlinAPI.interfaces.incoming

import com.google.maps.GeoApiContext
import com.google.maps.GeocodingApi
import com.google.maps.model.GeocodingResult
import io.restassured.RestAssured
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

import io.restassured.RestAssured.given
import org.hamcrest.Matchers.equalTo
import org.hamcrest.Matchers.notNullValue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PassengerAPITestIT {

    @LocalServerPort
    private var port: Int = 0

    @BeforeEach
    fun setup() {
        RestAssured.port = port
    }

    @Test
    fun testCreatePassenger() {
        val createPassengerJSON = """
            {"name":"Alexandre Saudate"}
        """.trimIndent()

        given()
            .contentType(io.restassured.http.ContentType.JSON)
            .body(createPassengerJSON)
            .post("/passengers")
            .then()
            .statusCode(200)
            .body("id", notNullValue())
            .body("name", equalTo("Alexandre Saudate"))

    }
}
//    @Test
//    fun main(args: Array<String>) {
//        val apiKey = "ArTyyCYgKb61IbSE2bmgtRHRkbyYY7zwaSSoVrYiHIdqUNtk6cyb_F1tcPII_6lb"
//        val context = GeoApiContext.Builder().apiKey(apiKey).build()
//
//        val addresses = listOf("Felipe Camarão, Blumenau", "Rua quinze de Novembro 1305, Blumenau")
//        for (address in addresses) {
//            val results: Array<GeocodingResult> = GeocodingApi.geocode(context, address).await()
//            val location = results[0].geometry.location
//            println("Endereço: $address | Latitude: ${location.lat} | Longitude: ${location.lng}")
//        }
//    }
