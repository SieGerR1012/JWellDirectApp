package com.exemple.controller.mvc;

import com.exemple.model.Region;
import com.exemple.service.RegionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/regions")
public class RegionMvcController {

    private final RegionService service;

    public RegionMvcController(RegionService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "region/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Region());
        return "region/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Region region) {
        service.create(region);
        return "redirect:/regions";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "region/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Region region) {
        service.update(id, region);
        return "redirect:/regions";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/regions";
    }
}
