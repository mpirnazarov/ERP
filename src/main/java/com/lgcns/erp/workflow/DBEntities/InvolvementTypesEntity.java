package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by DS on 1/23/2017.
 */
@Entity
@Table(name = "involvement_types", schema = "workflow", catalog = "LgErpSystem")
public class InvolvementTypesEntity {
    private long id;
    private String name;
    private Collection<StepsEntity> stepssById;

    @Id
    @Column(name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvolvementTypesEntity that = (InvolvementTypesEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "involvementTypesByInvolvementTypeId")
    public Collection<StepsEntity> getStepssById() {
        return stepssById;
    }

    public void setStepssById(Collection<StepsEntity> stepssById) {
        this.stepssById = stepssById;
    }
}
