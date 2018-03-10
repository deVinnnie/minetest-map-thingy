package be.devinnnie.parsers

import be.devinnnie.WorldConfig
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class TravelNetParserTest {

    lateinit var parser: TravelNetParser

    @BeforeEach
    fun setUp() {
        val worldConfig = WorldConfig()
        worldConfig.directory = this.javaClass.getResource("./world").file

        parser = TravelNetParser()
        parser.config = worldConfig
    }

    @Test
    fun parserGivesCorrectNumberOfStations() {
        val travelnets = parser.parse()

        assertTrue(travelnets.containsKey("vincent"))

        val owner = travelnets["vincent"]
        assertEquals(1, owner?.size)

        val network = owner?.get("porters")
        assertEquals(4, network?.size)
    }
}