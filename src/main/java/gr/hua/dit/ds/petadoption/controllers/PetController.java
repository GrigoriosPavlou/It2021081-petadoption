package gr.hua.dit.ds.petadoption.controllers;

import gr.hua.dit.ds.petadoption.entities.Pet;
import gr.hua.dit.ds.petadoption.entities.Shelter;
import gr.hua.dit.ds.petadoption.entities.enums.PetStatus;
import gr.hua.dit.ds.petadoption.entities.enums.PetSize;
import gr.hua.dit.ds.petadoption.entities.enums.Sex;
import gr.hua.dit.ds.petadoption.entities.enums.Species;
import gr.hua.dit.ds.petadoption.services.PetService;
import gr.hua.dit.ds.petadoption.services.ShelterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final ShelterService shelterService;

    public PetController(PetService petService, ShelterService shelterService) {
        this.petService = petService;
        this.shelterService = shelterService;
    }

    // ΛΙΣΤΑ
    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/pets"; // templates/pet/pets.html
    }

    // ΛΕΠΤΟΜΕΡΕΙΕΣ
    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Pet pet = petService.findById(id);
        model.addAttribute("pet", pet);
        return "pet/details"; // templates/pet/details.html
    }

    // ΝΕΟ - FORM
    @GetMapping("/new")
    public String createForm(Model model) {
        Pet pet = new Pet();
        pet.setStatus(PetStatus.DRAFT);

        // dropdown lists (αν χρησιμοποιείς enums σε select)
        model.addAttribute("pet", pet);
        model.addAttribute("speciesList", Arrays.asList(Species.values()));
        model.addAttribute("sexList", Arrays.asList(Sex.values()));
        model.addAttribute("sizeList", Arrays.asList(PetSize.values()));
        model.addAttribute("statusList", Arrays.asList(PetStatus.values()));

        // Προσωρινά: αν δεν έχεις login, πάρε το πρώτο shelter (ή άφησέ το κενό)
        model.addAttribute("shelters", shelterService.findAll());

        return "pet/pet"; // templates/pet/pet.html
    }

    // ΝΕΟ - SUBMIT
    @PostMapping("/new")
    public String createSubmit(@ModelAttribute("pet") Pet pet, @RequestParam(required = false) Long shelterId) {
        if (shelterId != null) {
            Shelter s = shelterService.findById(shelterId);
            pet.setShelter(s);
        }
        petService.save(pet);
        return "redirect:/pet";
    }

    // EDIT - FORM
    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        Pet pet = petService.findById(id);
        model.addAttribute("pet", pet);
        model.addAttribute("speciesList", Arrays.asList(Species.values()));
        model.addAttribute("sexList", Arrays.asList(Sex.values()));
        model.addAttribute("sizeList", Arrays.asList(PetSize.values()));
        model.addAttribute("statusList", Arrays.asList(PetStatus.values()));
        model.addAttribute("shelters", shelterService.findAll());
        return "pet/pet-edit"; // templates/pet/pet-edit.html (ή ξαναχρησιμοποίησε pet.html)
    }

    // EDIT - SUBMIT
    @PostMapping("/{id}/edit")
    public String editSubmit(@PathVariable Long id,
                             @ModelAttribute("pet") Pet form,
                             @RequestParam(required = false) Long shelterId) {
        Pet pet = petService.findById(id);
        if (pet == null) return "redirect:/pet";

        pet.setName(form.getName());
        pet.setSpecies(form.getSpecies());
        pet.setBreed(form.getBreed());
        pet.setSex(form.getSex());
        pet.setMinAgeMonths(form.getMinAgeMonths());
        pet.setMaxAgeMonths(form.getMaxAgeMonths());
        pet.setSize(form.getSize());
        pet.setStatus(form.getStatus());
        pet.setDescription(form.getDescription());
        if (shelterId != null) {
            Shelter s = shelterService.findById(shelterId);
            pet.setShelter(s);
        }
        petService.save(pet);
        return "redirect:/pet/" + id;
    }

    // ΔΙΑΓΡΑΦΗ
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        petService.delete(id);
        return "redirect:/pet";
    }
}
