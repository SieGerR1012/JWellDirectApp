package com.exemple.controller.mvc;

import com.exemple.model.Field;
import com.exemple.service.FieldService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/fields")
public class FieldMvcController {

    private final FieldService service;

    public FieldMvcController(FieldService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "field/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Field());
        return "field/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Field field) {
        service.create(field);
        return "redirect:/fields";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "field/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Field field) {
        service.update(id, field);
        return "redirect:/fields";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/fields";
    }
}
