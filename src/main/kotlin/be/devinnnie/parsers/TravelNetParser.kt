package be.devinnnie.parsers

import be.devinnnie.WorldConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class TravelNetParser {

    @Autowired
    lateinit var config: WorldConfig

    fun parse(): String {
        val lineList = mutableListOf<String>()

        Paths.get("${config.directory}/mod_travelnet.data")
                .toFile()
                .useLines {
                    lines -> lines.forEach { lineList.add(it) }
                }

        val travelnets = lineList.first()
                .substring(0, lineList.first().length - 2)
                .replace("return {", "")
                .replace("[", "")
                .replace("]", "")
                .replace("=", ":")
                .replace("\"vincent\" : {","")

        return "{$travelnets}"
    }
}
