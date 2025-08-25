package gr.hua.dit.ds.petadoption.controllers;

import gr.hua.dit.ds.petadoption.entities.AdoptionRequest;
import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.User;
import gr.hua.dit.ds.petadoption.entities.enums.RequestStatus;
import gr.hua.dit.ds.petadoption.services.AdoptionRequestService;
import gr.hua.dit.ds.petadoption.services.PetService;
import gr.hua.dit.ds.petadoption.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/adoption")
public class AdoptionRequestController {

    private final AdoptionRequestService adoptionService;
    private final PetService petService;
    private final UserService userService;

    public AdoptionRequestController(AdoptionRequestService adoptionService,
                                     PetService petService,
                                     UserService userService) {
        this.adoptionService = adoptionService;
        this.petService = petService;
        this.userService = userService;
    }

    // Λίστα όλων (π.χ. για admin/shelter dashboard)
    @GetMapping("")
    public String listAll(Model model) {
        model.addAttribute("requests", adoptionService.findAll());
        return "adoption/requests"; // templates/adoption/requests.html
    }

    // Δημιουργία αίτησης για συγκεκριμένο pet
    @PostMapping("/new")
    public String create(@RequestParam Long petId,
                         @RequestParam Long adopterUserId,
                         @RequestParam(required = false) String message) {
        Pet pet = petService.findById(petId);
        User adopter = userService.findById(adopterUserId);

        if (pet == null || adopter == null) return "redirect:/pet";

        AdoptionRequest req = new AdoptionRequest();
        req.setPet(pet);
        req.setAdopterUser(adopter);
        req.setMessage(message);
        req.setStatus(RequestStatus.PENDING);
        adoptionService.save(req);
        return "redirect:/adoption"; // ή redirect:/pet/{petId}
    }

    // Αλλαγή κατάστασης (π.χ. από shelter/admin)
    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id, @RequestParam RequestStatus status) {
        var req = adoptionService.findById(id);
        if (req == null) return "redirect:/adoption";
        req.setStatus(status);
        adoptionService.save(req);
        return "redirect:/adoption";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        adoptionService.delete(id);
        return "redirect:/adoption";
    }
}
