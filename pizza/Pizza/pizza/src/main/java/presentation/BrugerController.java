package presentation;

import applicationLayer.BrugerService;
import domain.Bruger;
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
    public String gemBruger(@ModelAttribute Bruger bruger, Model model) {
        try {
            int brugerId = brugerService.gemBruger(bruger);
            return "redirect:/bruger/" + brugerId;
        } catch (IllegalArgumentException e) {
            model.addAttribute("bruger", new Bruger());
            model.addAttribute("errorMessage", e.getMessage()); // Pass error to Thymeleaf
            return "opret"; // Stay on the same page if email is taken
        }
    }

    @PostMapping("/login")
    public String authenticateUser(@RequestParam String email, @RequestParam String password, Model model) {
        Bruger bruger = brugerService.authenticateUser(email, password); // Validate credentials

        model.addAttribute("bruger", new Bruger());

        if (bruger == null) {
            model.addAttribute("errorMessage", "Invalid email or password!"); // Error message if login fails
            return "opret"; // Stay on the same page if authentication fails
        }

        // Redirect to the user's profile page after successful login
        BrugerService.setCurrentBruger(bruger);
        return "redirect:/bruger/" + bruger.getId();
    }


    // Til visning af brugerprofil
    @GetMapping("/{id}")
    public String visBrugerProfil(@PathVariable int id, Model model) {
        Bruger bruger = brugerService.getBrugerById(id);
        
        if (bruger == null) {
            return "redirect:/bruger/login"; // Redirect if not found
        }
        model.addAttribute("bruger", bruger);
        return "profil"; // returnerer profil.html under templates
    }
}