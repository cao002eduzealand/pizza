package presentation;

import applicationLayer.OrdreService;
import domain.Bruger;
import domain.Ordre;
import domain.Pizza;
import infrastructure.OrdreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
@Controller
public class OrdreController {

    private final OrdreService ordreService;

    public OrdreController(OrdreService ordreService) {
        this.ordreService = ordreService;
    }

    @GetMapping("/ordre")
    public String showOrdre(Model model, HttpSession session) {
        List<Pizza> cart = (List<Pizza>) session.getAttribute("cart");

        if (cart == null || cart.isEmpty()) {
            return "redirect:/pizza"; // Redirect if cart is empty
        }

        double totalPrice = 0;

        for (Pizza pizza : cart) {
            totalPrice += pizza.getPris();
        }

        model.addAttribute("cart", cart);
        model.addAttribute("totalPrice", totalPrice);
        return "ordre"; // Load ordre.html
    }

    @PostMapping("/pay")
    public String processPayment(HttpSession session) {

        Bruger bruger = (Bruger) session.getAttribute("bruger");
        List<Pizza> cart = (List<Pizza>) session.getAttribute("cart");

        if (bruger == null || cart == null || cart.isEmpty()) {
            return "redirect:/pizza"; // If no user or cart, redirect to pizza menu
        }

        double totalPrice = 0;
        for (Pizza pizza : cart) {
            totalPrice += pizza.getPris();
        }

        // Create new order
        Ordre ordre = new Ordre();
        ordre.setBruger(bruger);  // Associate the logged-in user with the order
        ordre.setDato(LocalDate.now());
        ordre.setPris(totalPrice);

        // Save the order and the pizzas in the order (using the service layer)
        ordreService.gemOrdre(ordre, cart);

        // Remove cart from session after successful payment
        session.removeAttribute("cart");

        // Redirect to user profile after payment is processed
        return "redirect:/bruger/" + bruger.getId();
    }
}
