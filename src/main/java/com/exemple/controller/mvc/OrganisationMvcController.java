package com.exemple.controller.mvc;

import com.exemple.model.Organisation;
import com.exemple.service.OrganisationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/organisations")
public class OrganisationMvcController {

    private final OrganisationService service;

    public OrganisationMvcController(OrganisationService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "organisation/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Organisation());
        return "organisation/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Organisation org) {
        service.create(org);
        return "redirect:/organisations";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "organisation/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Organisation org) {
        service.update(id, org);
        return "redirect:/organisations";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/organisations";
    }
}
