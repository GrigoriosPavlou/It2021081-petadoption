package gr.hua.dit.ds.petadoption.controllers;

import gr.hua.dit.ds.petadoption.entities.Pet;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("pet")
public class PetController {

    private List<Pet> pets = new ArrayList<Pet>();

    @PostConstruct
    public void setup() {
        Pet pet1 = new Pet(1,"Rex","Dog","Healthy");
        Pet pet2 = new Pet(2,"Mittens","Cat","Vaccinated");
        Pet pet3 = new Pet(3,"Luna","Dog","Needs check-up");
        pets.add(pet1);
        pets.add(pet2);
        pets.add(pet3);
    }


    public Pet getPet(List<Pet> stdlist, int id) {
        for (Pet pet : stdlist) {
            if (pet.getId() == id) {
                return pet;
            }
        }
        return null;
    }

    @GetMapping("")
    public String showPets(Model model){
        model.addAttribute("pets", pets);
        return "pet/pets";
    }

    @GetMapping("/{id}")
    public String showPet(@PathVariable Integer id, Model model){
        Pet pet = getPet(pets, id);
        model.addAttribute("pets", pet);
        return "pet/pets";
    }

    @GetMapping("/new")
    public String addPet(Model model){
        Pet pet = new Pet ();
        model.addAttribute("pet", pet);
        return "pet/pet";

    }

    @PostMapping("/new")
    public String savePet(@ModelAttribute("pet") Pet pet, Model model) {
        System.out.println(pet);
        System.out.println(pets);
        pets.add(pet);
        model.addAttribute("pets", pets);
        return "pet/pets";
    }
}