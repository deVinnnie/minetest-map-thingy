package be.devinnnie

import be.devinnnie.parsers.AreasParser
import be.devinnnie.parsers.PlayersParser
import be.devinnnie.parsers.TravelNetParser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestApiController {

    @Autowired
    lateinit var areas : AreasParser

    @Autowired
    lateinit var travelnets : TravelNetParser

    @Autowired
    lateinit var players: PlayersParser

    @GetMapping(value= ["/areas"], produces=[MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun index(): String {
        return areas.parse()
    }

    @GetMapping(value= ["/travelnets"], produces=[MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun travelnets(): String {
        return travelnets.parse()
    }

    @GetMapping("/players")
    fun players(): List<Player>{
        return players.parse().toList()
    }

}
