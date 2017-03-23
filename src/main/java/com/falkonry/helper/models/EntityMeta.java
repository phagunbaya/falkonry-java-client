package com.falkonry.helper.models;

/*!
 * falkonry-java-client
 * Copyright(c) 2016 Falkonry Inc
 * MIT Licensed
 */
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EntityMeta {

    private String id;
    private String sourceId;
    private String datastream;
    private String path;
    private String label;
    private String tenant;
    private String createdBy;
    private Long createTime;
    private String updatedBy;
    private Long updateTime;
    private Long type;

    public String getTenant() {
        return tenant;
    }

    public String getDatastream() {
        return datastream;
    }

    public void setDatastream(String datastream) {
        this.datastream = datastream;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }

    public void setTenant(String tenant) {
        this.tenant = tenant;
    }

    public String getId() {
        return id;
    }

    public EntityMeta setId(String id) {
        this.id = id;
        return this;
    }

    public String getSourceId() {
        return sourceId;
    }

    public EntityMeta setSourceId(String sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    

    @JsonProperty("tenant")
    public String getAccount() {
        return tenant;
    }

    @JsonProperty("tenant")
    public EntityMeta setAccount(String account) {
        this.tenant = account;
        return this;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public EntityMeta setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public EntityMeta setCreateTime(Long createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public EntityMeta setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        return this;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public EntityMeta setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
        return this;
    }
}
