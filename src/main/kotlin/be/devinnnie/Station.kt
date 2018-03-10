package be.devinnnie

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Station(
    val owner: String  = "",
    val network: String  = "",
    val name: String  = "",
    val pos: Position = Position(0f,0f,0f)
)