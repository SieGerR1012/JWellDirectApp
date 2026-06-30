package com.exemple.model;

public class WellRequest {

    private String wellNumber;
    private Long organisationId;
    private Long regionId;
    private Long subsurfacePlotId;
    private Long fieldId;
    private Long clusterId;
    private Long drillingCrewId;
    private Long customerId;

    public String getWellNumber() {
        return wellNumber;
    }

    public void setWellNumber(String wellNumber) {
        this.wellNumber = wellNumber;
    }

    public Long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    public Long getRegionId() {
        return regionId;
    }

    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    public Long getSubsurfacePlotId() {
        return subsurfacePlotId;
    }

    public void setSubsurfacePlotId(Long subsurfacePlotId) {
        this.subsurfacePlotId = subsurfacePlotId;
    }

    public Long getFieldId() {
        return fieldId;
    }

    public void setFieldId(Long fieldId) {
        this.fieldId = fieldId;
    }

    public Long getClusterId() {
        return clusterId;
    }

    public void setClusterId(Long clusterId) {
        this.clusterId = clusterId;
    }

    public Long getDrillingCrewId() {
        return drillingCrewId;
    }

    public void setDrillingCrewId(Long drillingCrewId) {
        this.drillingCrewId = drillingCrewId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
