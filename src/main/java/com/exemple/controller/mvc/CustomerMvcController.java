package com.exemple.controller.mvc;

import com.exemple.model.Customer;
import com.exemple.service.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customers")
public class CustomerMvcController {

    private final CustomerService service;

    public CustomerMvcController(CustomerService service) {
        this.service = service;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", service.findAll());
        return "customer/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("item", new Customer());
        return "customer/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Customer customer) {
        service.create(customer);
        return "redirect:/customers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("item", service.findById(id));
        return "customer/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute Customer customer) {
        service.update(id, customer);
        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/customers";
    }
}
