package com.exemple.controller.mvc;

import com.exemple.model.SubsurfacePlot;
import com.exemple.service.SubsurfacePlotService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/subsurface-plots")
public class SubsurfacePlotMvcController {

    private final SubsurfacePlotService service;

    public SubsurfacePlotMvcController(SubsurfacePlotService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "subsurface-plot/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new SubsurfacePlot());
        return "subsurface-plot/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute SubsurfacePlot plot) {
        service.create(plot);
        return "redirect:/subsurface-plots";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "subsurface-plot/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute SubsurfacePlot plot) {
        service.update(id, plot);
        return "redirect:/subsurface-plots";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/subsurface-plots";
    }
}
