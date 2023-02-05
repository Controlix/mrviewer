package be.mbict.mrviewer

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping(value = ["/ui"])
class UiController(val mrRepository: MrRepository) {

    @GetMapping
    fun showAll(model: Model): String {
        mrRepository.findAll().forEach(::println)

        model.addAttribute("mr", mrRepository.findAll())

        return "mr"
    }
}