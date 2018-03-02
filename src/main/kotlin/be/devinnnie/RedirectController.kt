package be.devinnnie

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import org.springframework.web.servlet.view.RedirectView

@Controller
class RedirectController {

    @GetMapping(value= ["/"])
    fun index(attributes: RedirectAttributes): RedirectView {
        return RedirectView("www/map.html")
    }
}
