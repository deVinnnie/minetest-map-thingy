package be.devinnnie

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter


@Configuration
class StaticResourceConfig : WebMvcConfigurerAdapter() {
    override fun addResourceHandlers(registry: ResourceHandlerRegistry?) {
        registry!!.addResourceHandler("/www/tiles/**")
                .addResourceLocations("file:/opt/tiles/")
    }
}