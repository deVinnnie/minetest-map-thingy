package be.devinnnie

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class StaticResourceConfig : WebMvcConfigurerAdapter() {
    @Autowired
    lateinit var worldConfig : WorldConfig

    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/www/tiles/**")
                .addResourceLocations(worldConfig.tiles)
    }
}