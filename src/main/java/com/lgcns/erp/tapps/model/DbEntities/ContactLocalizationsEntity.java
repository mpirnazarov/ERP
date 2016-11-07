package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "contact_localizations", schema = "public", catalog = "LgErpSystem")
public class ContactLocalizationsEntity {
    private int id;
    private String name;
    private String address;
    private int contactId;
    private int lenguageId;
    private ContactsEntity contactsByContactId;
    private LanguagesEntity languagesByLenguageId;

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
    @Column(name = "name ", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "contact_id", nullable = false, insertable = false, updatable = false)
    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    @Basic
    @Column(name = "lenguage_id", nullable = false, insertable = false, updatable = false)
    public int getLenguageId() {
        return lenguageId;
    }

    public void setLenguageId(int lenguageId) {
        this.lenguageId = lenguageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactLocalizationsEntity that = (ContactLocalizationsEntity) o;

        if (id != that.id) return false;
        if (contactId != that.contactId) return false;
        if (lenguageId != that.lenguageId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + contactId;
        result = 31 * result + lenguageId;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "contact_id", referencedColumnName = "id", nullable = false)
    public ContactsEntity getContactsByContactId() {
        return contactsByContactId;
    }

    public void setContactsByContactId(ContactsEntity contactsByContactId) {
        this.contactsByContactId = contactsByContactId;
    }

    @ManyToOne
    @JoinColumn(name = "lenguage_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLenguageId() {
        return languagesByLenguageId;
    }

    public void setLanguagesByLenguageId(LanguagesEntity languagesByLenguageId) {
        this.languagesByLenguageId = languagesByLenguageId;
    }
}
