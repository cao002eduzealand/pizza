package presentation;

import applicationLayer.OrdreService;
import domain.Ordre;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class OrdreController {

    private final OrdreService ordreService;

    public OrdreController(OrdreService ordreService) {
        this.ordreService = ordreService;
    }

    @GetMapping("/ordre/opret")
    public String visOrdreForm(Model model) {
        model.addAttribute("ordre", new Ordre());
        return "ordre/opret";
    }

    @PostMapping("/ordre/opret")
    public String gemOrdre(@ModelAttribute Ordre ordre) {
        ordreService.gemOrdre(ordre);
        return "redirect:/ordre/" + ordre.getId();
    }

    @GetMapping("/ordre/{id}")
    public String visOrdre(@PathVariable int id, Model model) {
        Ordre ordre = ordreService.getOrdreById(id);
        model.addAttribute("ordre", ordre);
        return "ordre/vis";
    }
}