package com.exemple.controller.mvc;

import com.exemple.model.Cluster;
import com.exemple.service.ClusterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/clusters")
public class ClusterMvcController {

    private final ClusterService service;

    public ClusterMvcController(ClusterService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "cluster/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Cluster());
        return "cluster/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Cluster cluster) {
        service.create(cluster);
        return "redirect:/clusters";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "cluster/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Cluster cluster) {
        service.update(id, cluster);
        return "redirect:/clusters";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/clusters";
    }
}
