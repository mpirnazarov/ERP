package com.lgcns.erp.workflow.DBEntities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by Muslimbek Pirnazarov on 1/23/2017.
 */
@Entity
@Table(name = "trip_types", schema = "workflow", catalog = "LgErpSystem")
public class TripTypesEntity {
    private int id;
    private String name;
    private Collection<RequestsEntity> requestssById;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
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

        TripTypesEntity that = (TripTypesEntity) o;

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

    @OneToMany(mappedBy = "tripTypesByTripTypeId")
    public Collection<RequestsEntity> getRequestssById() {
        return requestssById;
    }

    public void setRequestssById(Collection<RequestsEntity> requestssById) {
        this.requestssById = requestssById;
    }
}
