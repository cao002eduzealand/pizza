package presentation;

import applicationLayer.BrugerService;
import domain.Bruger;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/bruger")
public class BrugerController {

    private final BrugerService brugerService;

    public BrugerController(BrugerService brugerService) {
        this.brugerService = brugerService;
    }

    // Til oprettelse af bruger
    @GetMapping("/opret")
    public String visBrugerForm(Model model) {
        model.addAttribute("bruger", new Bruger());
        return "opret"; // returnerer opret.html under templates
    }



    @PostMapping("/opret")
    public String gemBruger(@ModelAttribute Bruger bruger, Model model, HttpSession session) {
        try {
            int brugerId = brugerService.gemBruger(bruger);

            // Store the newly created user in session
            session.setAttribute("bruger", bruger);

            return "redirect:/bruger/" + brugerId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("bruger", new Bruger());
            model.addAttribute("errorMessage", e.getMessage()); // Pass error to Thymeleaf
            return "opret"; // Stay on the same page if email is taken
        }
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password, Model model, HttpSession session) {
        Bruger bruger = brugerService.authenticateUser(email, password); // Validate credentials

        if (bruger == null) {
            model.addAttribute("errorMessage", "Invalid email or password!"); // Error message if login fails
            return "opret"; // Stay on the same page if authentication fails
        }

        // Store the logged-in user in session
        session.setAttribute("bruger", bruger);

        return "redirect:/bruger/" + bruger.getId();
    }


    @GetMapping("/{id}")
    public String visBrugerProfil(@PathVariable String id, Model model, HttpSession session) {
        // Convert the id to an integer (only proceed if valid)
        int userId;
        try {
            userId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            // If invalid ID, redirect to login page
            return "redirect:/bruger/opret";
        }

        // Get the current user from the session
        Bruger bruger = (Bruger) session.getAttribute("bruger");

        if (bruger == null || bruger.getId() != userId) {
            return "redirect:/bruger/opret"; // Redirect to login/signup if session is missing or user is incorrect
        }

        model.addAttribute("bruger", bruger);
        return "profil"; // Returnerer profil.html under templates
    }

    //logout user
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Clear session data
        return "redirect:/bruger/opret"; // Redirect to login page after logout
    }


}