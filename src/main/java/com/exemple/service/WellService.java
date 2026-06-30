package com.exemple.service;

import com.exemple.model.*;
import com.exemple.repository.WellRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class WellService {

    private final WellRepository wellRepository;
    private final OrganisationService organisationService;
    private final CustomerService customerService;
    private final RegionService regionService;
    private final SubsurfacePlotService subsurfacePlotService;
    private final FieldService fieldService;
    private final ClusterService clusterService;
    private final DrillingCrewService drillingCrewService;

    public WellService(WellRepository wellRepository,
                       OrganisationService organisationService,
                       CustomerService customerService,
                       RegionService regionService,
                       SubsurfacePlotService subsurfacePlotService,
                       FieldService fieldService,
                       ClusterService clusterService,
                       DrillingCrewService drillingCrewService) {
        this.wellRepository = wellRepository;
        this.organisationService = organisationService;
        this.customerService = customerService;
        this.regionService = regionService;
        this.subsurfacePlotService = subsurfacePlotService;
        this.fieldService = fieldService;
        this.clusterService = clusterService;
        this.drillingCrewService = drillingCrewService;
    }

    @Transactional(readOnly = true)
    public List<Well> findAll() {
        return wellRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Well findById(Long id) {
        return wellRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Well not found with id: " + id));
    }

    @Transactional
    public Well create(Well well, Long organisationId, Long regionId,
                       Long subsurfacePlotId, Long fieldId,
                       Long clusterId, Long drillingCrewId,
                       Long customerId) {
        well.setOrganisation(organisationService.findById(organisationId));
        well.setRegion(regionService.findById(regionId));
        well.setSubsurfacePlot(subsurfacePlotService.findById(subsurfacePlotId));
        well.setField(fieldService.findById(fieldId));
        well.setCluster(clusterService.findById(clusterId));
        well.setDrillingCrew(drillingCrewService.findById(drillingCrewId));
        if (customerId != null) {
            well.setCustomer(customerService.findById(customerId));
        }
        return wellRepository.save(well);
    }

    @Transactional
    public Well update(Long id, Well well, Long organisationId, Long regionId,
                       Long subsurfacePlotId, Long fieldId,
                       Long clusterId, Long drillingCrewId,
                       Long customerId) {
        Well existing = findById(id);
        if (well.getWellNumber() != null) {
            existing.setWellNumber(well.getWellNumber());
        }
        if (organisationId != null) {
            existing.setOrganisation(organisationService.findById(organisationId));
        }
        if (regionId != null) {
            existing.setRegion(regionService.findById(regionId));
        }
        if (subsurfacePlotId != null) {
            existing.setSubsurfacePlot(subsurfacePlotService.findById(subsurfacePlotId));
        }
        if (fieldId != null) {
            existing.setField(fieldService.findById(fieldId));
        }
        if (clusterId != null) {
            existing.setCluster(clusterService.findById(clusterId));
        }
        if (drillingCrewId != null) {
            existing.setDrillingCrew(drillingCrewService.findById(drillingCrewId));
        }
        if (customerId != null) {
            existing.setCustomer(customerService.findById(customerId));
        }
        return wellRepository.save(existing);
    }

    @Transactional
    public void delete(Long id) {
        Well existing = findById(id);
        wellRepository.delete(existing);
    }
}
