package com.exemple.controller.mvc;

import com.exemple.model.DrillingCrew;
import com.exemple.service.DrillingCrewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/drilling-crews")
public class DrillingCrewMvcController {

    private final DrillingCrewService service;

    public DrillingCrewMvcController(DrillingCrewService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "drilling-crew/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new DrillingCrew());
        return "drilling-crew/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute DrillingCrew crew) {
        service.create(crew);
        return "redirect:/drilling-crews";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "drilling-crew/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute DrillingCrew crew) {
        service.update(id, crew);
        return "redirect:/drilling-crews";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/drilling-crews";
    }
}
