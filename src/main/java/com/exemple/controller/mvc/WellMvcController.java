package com.exemple.controller.mvc;

import com.exemple.model.Well;
import com.exemple.model.WellRequest;
import com.exemple.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/wells")
public class WellMvcController {

    private final WellService wellService;
    private final OrganisationService organisationService;
    private final RegionService regionService;
    private final SubsurfacePlotService subsurfacePlotService;
    private final FieldService fieldService;
    private final ClusterService clusterService;
    private final DrillingCrewService drillingCrewService;
    private final CustomerService customerService;

    public WellMvcController(WellService wellService,
                             OrganisationService organisationService,
                             RegionService regionService,
                             SubsurfacePlotService subsurfacePlotService,
                             FieldService fieldService,
                             ClusterService clusterService,
                             DrillingCrewService drillingCrewService,
                             CustomerService customerService) {
        this.wellService = wellService;
        this.organisationService = organisationService;
        this.regionService = regionService;
        this.subsurfacePlotService = subsurfacePlotService;
        this.fieldService = fieldService;
        this.clusterService = clusterService;
        this.drillingCrewService = drillingCrewService;
        this.customerService = customerService;
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("items", wellService.findAll());
        return "well/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("well", new Well());
        model.addAttribute("organisations", organisationService.findAll());
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("subsurfacePlots", subsurfacePlotService.findAll());
        model.addAttribute("fields", fieldService.findAll());
        model.addAttribute("clusters", clusterService.findAll());
        model.addAttribute("drillingCrews", drillingCrewService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return "well/form";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute WellRequest request) {
        Well well = new Well();
        well.setWellNumber(request.getWellNumber());
        wellService.create(well,
                request.getOrganisationId(),
                request.getRegionId(),
                request.getSubsurfacePlotId(),
                request.getFieldId(),
                request.getClusterId(),
                request.getDrillingCrewId(),
                request.getCustomerId());
        return "redirect:/wells";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("well", wellService.findById(id));
        model.addAttribute("organisations", organisationService.findAll());
        model.addAttribute("regions", regionService.findAll());
        model.addAttribute("subsurfacePlots", subsurfacePlotService.findAll());
        model.addAttribute("fields", fieldService.findAll());
        model.addAttribute("clusters", clusterService.findAll());
        model.addAttribute("drillingCrews", drillingCrewService.findAll());
        model.addAttribute("customers", customerService.findAll());
        return "well/form";
    }

    @PostMapping("/{id}/edit")
    public String update(@PathVariable Long id, @ModelAttribute WellRequest request) {
        Well well = new Well();
        well.setWellNumber(request.getWellNumber());
        wellService.update(id, well,
                request.getOrganisationId(),
                request.getRegionId(),
                request.getSubsurfacePlotId(),
                request.getFieldId(),
                request.getClusterId(),
                request.getDrillingCrewId(),
                request.getCustomerId());
        return "redirect:/wells";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        wellService.delete(id);
        return "redirect:/wells";
    }
}
