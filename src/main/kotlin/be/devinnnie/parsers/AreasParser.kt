package be.devinnnie.parsers

import be.devinnnie.WorldConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.nio.file.Paths

@Component
class AreasParser {

    @Autowired
    lateinit var config: WorldConfig

    fun parse(): String {
        val lineList = mutableListOf<String>()

        Paths.get("${config.directory}/areas.dat")
             .toFile()
             .useLines {
                lines -> lines.forEach { lineList.add(it) }
            }

        val areas = lineList.first()
                .substring(0, lineList.first().length - 1)
                .replace("return {", "")
                .replace("[", "")
                .replace("]", "")
                .replace("=", ":")

        return "[$areas]"
    }
}