package be.devinnnie.parsers

import be.devinnnie.Player
import be.devinnnie.Position
import be.devinnnie.WorldConfig
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.io.File
import java.io.IOException
import java.nio.file.Paths

@Component
class PlayersParser {

    @Autowired
    lateinit var config: WorldConfig

    @Throws(IOException::class)
    fun parse() : Sequence<Player> {
        return Paths.get("${config.directory}/players")
            .toFile()
            .walkTopDown()
            .filter { it.isFile }
            .map{ parsePlayerFile(it) }
    }

    @Throws(IOException::class)
    fun parsePlayerFile(playerFile: File): Player {
        val lineList = mutableListOf<String>()

        playerFile.useLines {
            lines -> lines.forEach { lineList.add(it) }
        }

        val position = lineList.filter { it.startsWith("position") }
                .map {
                    it.replace("position", "")
                            .replace("=", "")
                            .replace("\\s".toRegex(), "")
                            .replace("(", "")
                            .replace(")", "")
                            .split(",".toRegex())
                }
                .map {
                    Position(
                    it[0].toFloat() / 10,
                    it[1].toFloat() / 10,
                    it[2].toFloat() / 10
                    )
                }
                .first()


        val name =
                lineList.filter { it.startsWith("name")  }
                        .map {
                                it.replace("name", "")
                                    .replace("=", "")
                                    .replace("\\s".toRegex(), "")
                        }
                        .first()

        return Player(name, position)
    }
}