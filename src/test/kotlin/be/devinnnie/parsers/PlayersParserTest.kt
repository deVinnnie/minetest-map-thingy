package be.devinnnie.parsers

import be.devinnnie.Position
import be.devinnnie.WorldConfig
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersParserTest {

    lateinit var parser: PlayersParser

    @BeforeEach
    fun setUp(){
        val worldConfig = WorldConfig()
        worldConfig.directory = this.javaClass.getResource("./world").file

        parser = PlayersParser()
        parser.config = worldConfig
    }

    @Test
    fun parserGivesCorrectName(){
        val players = parser.parse()

        assertEquals("vincent", players.first().name)
    }

    @Test
    fun parserGivesCorrectPosition(){
        val players = parser.parse()

        assertEquals(1, players.count())
        assertEquals(Position(248.019f, 47.5f, 246.949f), players.first().position)
    }
}