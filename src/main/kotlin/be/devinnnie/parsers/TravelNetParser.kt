package be.devinnnie.parsers

import be.devinnnie.Station
import be.devinnnie.WorldConfig
import com.fasterxml.jackson.core.type.TypeReference
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Paths
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.HashMap
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

@Component
class TravelNetParser {

    @Autowired
    lateinit var config: WorldConfig

    fun parse(): Map<String, Map<String, Map<String, Station>>> {
        val lineList = mutableListOf<String>()

        Paths.get("${config.directory}/mod_travelnet.data")
                .toFile()
                .useLines {
                    lines -> lines.forEach { lineList.add(it) }
                }

        val parsed = lineList.joinToString("")
                .replace("return ", "")
                .replace("[", "")
                .replace("]", "")
                .replace("=", ":")

        val mapper = ObjectMapper()
        mapper.registerKotlinModule()

        val typeRef = object : TypeReference<HashMap<String, HashMap<String, HashMap<String, Station>>>>() {}

        return mapper.readValue(parsed, typeRef)
    }
}
