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

import java.util.List;

@Controller
public class PizzaController {

        private final PizzaService pizzaService;

        public PizzaController(PizzaService pizzaService) {
            this.pizzaService = pizzaService;
        }

    @GetMapping("/pizza")
    public String visPizzaMenu(Model model) {
        Bruger bruger = BrugerService.getCurrentBruger(); // Get the logged-in user
        if (bruger == null) {
            return "redirect:/bruger/login"; // Redirect to login if no user is found
        }

        List<Pizza> pizzas = pizzaService.getAllPizzas();
        model.addAttribute("pizzaer", pizzas);
        model.addAttribute("bruger", bruger); // Pass the user to the template
        return "pizza"; // Load pizza.html
    }



    @GetMapping("/pizza/opret")
        public String visPizzaForm(Model model) {
            model.addAttribute("pizza", new Pizza());
            return "pizza/opret";
        }

        @PostMapping("/pizza/opret")
        public String gemPizza(@ModelAttribute Pizza pizza) {
            pizzaService.gemPizza(pizza);
            return "redirect:/pizza";
        }
    }

