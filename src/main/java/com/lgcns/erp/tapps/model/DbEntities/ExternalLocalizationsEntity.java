package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Muslimbek on 06.10.2016.
 */
@Entity
@Table(name = "external_localization", schema = "public", catalog = "LgErpSystem")
public class ExternalLocalizationsEntity {
    private int id;
    private String externalName;

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "exter_name", nullable = false, length = 100)
    public String getExternalName() {
        return externalName;
    }

    public void setExternalName(String externalName) {
        this.externalName = externalName;
    }

}
