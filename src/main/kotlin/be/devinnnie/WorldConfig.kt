package be.devinnnie

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties("world")
class WorldConfig{

    lateinit var directory: String

    lateinit var tiles: String
}