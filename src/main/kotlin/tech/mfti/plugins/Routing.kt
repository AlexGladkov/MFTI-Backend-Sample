package tech.mfti.plugins

import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import kotlinx.serialization.Serializable
import java.util.UUID
import javax.xml.catalog.Catalog

@Serializable
data class LoginRequest(
    val login: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val token: String
)

@Serializable
data class CatalogResponse(
    val nearest: List<Restaurant>,
    val popular: List<Restaurant>,
    val commercial: Commercial
)

@Serializable
data class Restaurant(
    val id: Int,
    val name: String,
    val deliveryTime: String,
    val image: String
)

@Serializable
data class Commercial(
    val picture: String,
    val url: String
)

fun Application.configureRouting() {
    routing {
        get("/") {
            call.respondText("Hello, World")
        }

        post("/login") {
            val request = call.receive<LoginRequest>()
            if (request.login.isNotBlank() && request.password.isNotBlank()) {
                call.respond(LoginResponse(UUID.randomUUID().toString()))
            } else {
                call.respond(HttpStatusCode.BadRequest, "Login or Password incorrect")
            }
        }

        get("/catalog") {
            call.respond(
                CatalogResponse(
                    nearest = listOf(
                        Restaurant(
                            id = 0,
                            name = "KFC",
                            image = "https://play-lh.googleusercontent.com/s7slUGiae9bq7XuYur0GWd_qDp_UXgo_5BIpzOT_BvKGg17TYG5QDr3ckqPcpq20jVU",
                            deliveryTime = "12 min"
                        ),
                        Restaurant(
                            id = 1,
                            name = "McDonald",
                            image = "https://pbs.twimg.com/profile_images/1579916871654117376/Dxd2l1sN_400x400.png",
                            deliveryTime = "10 min"
                        ),
                        Restaurant(
                            id = 2,
                            name = "Burger King",
                            image = "https://img.wongnai.com/p/1920x0/2019/02/26/cebf62f2742e4756a42b6b505070d6fe.jpg",
                            deliveryTime = "15 min"
                        )
                    ),
                    popular = listOf(
                        Restaurant(
                            id = 3,
                            name = "Stars Coffee",
                            image = "https://static.tildacdn.com/tild6532-3164-4062-b261-613433386237/Stars.svg",
                            deliveryTime = "20 min"
                        ),
                        Restaurant(
                            id = 1,
                            name = "Burger Heroes",
                            image = "https://www.rusfranch.ru/u/www/images/catalog/logo_image_nwna9cyklpaicbxlpzm2c7pvqbck0mgfa.png",
                            deliveryTime = "25 min"
                        ),
                        Restaurant(
                            id = 2,
                            name = "Вкусно и точка",
                            image = "https://play-lh.googleusercontent.com/qXayVb-wdTdSV6VmK9pRPIkzRvAw2y2Y2R4uaYzjvZ2ICkcrEMCS8d-qMPrHSmiXTQ",
                            deliveryTime = "10 min"
                        )
                    ),
                    commercial = Commercial(
                        picture = "https://img.freepik.com/premium-vector/weekend-big-sale-promotion-banner-design-template-last-chance-online-shopping-with-clearance-website-purchase-order-commercial-special-offer-vector-illustration_419341-628.jpg?w=2000",
                        url = "https://google.com/"
                    )
                )
            )
        }
    }
}
