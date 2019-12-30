package com.udelphi.dto;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AuditableDto {


    protected String createdBy;

    protected Date creationDate;

    protected String lastModifiedBy;

    protected Date lastModifiedDate;

    public String getCreatedBy() {
        return createdBy;
    }

    public AuditableDto setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public AuditableDto setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public AuditableDto setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public AuditableDto setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }
}
