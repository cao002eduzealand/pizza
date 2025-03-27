package presentation;

import applicationLayer.BrugerService;
import applicationLayer.PizzaService;
import domain.Bruger;
import domain.Pizza;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import java.util.List;

@Controller
public class PizzaController {

    private final PizzaService pizzaService;

    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping("/pizza")
    public String visPizzaMenu(Model model, HttpSession session) {
        // Get the logged-in user from session
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger == null) {
            return "redirect:/bruger/login"; // Redirect if no user is found
        }

        List<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzaer", pizzas);
        model.addAttribute("bruger", bruger); // Pass user info to HTML
        return "pizza"; // Load pizza.html
    }

    @GetMapping("/pizza/opret")
    public String visPizzaForm(Model model, HttpSession session) {
        // Ensure only logged-in users can access
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        if (bruger == null) {
            return "redirect:/bruger/login";
        }

        model.addAttribute("pizza", new Pizza());
        return "pizza/opret";
    }

    @PostMapping("/pizza/opret")
    public String gemPizza(@ModelAttribute Pizza pizza, HttpSession session) {
        // Ensure only logged-in users can create pizzas
        Bruger bruger = (Bruger) session.getAttribute("bruger");
        if (bruger == null) {
            return "redirect:/bruger/login";
        }

        pizzaService.gemPizza(pizza);
        return "redirect:/pizza";
    }
}

