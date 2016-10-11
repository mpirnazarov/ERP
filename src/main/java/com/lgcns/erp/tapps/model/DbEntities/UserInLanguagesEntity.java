package com.lgcns.erp.tapps.model.DbEntities;

import javax.persistence.*;

/**
 * Created by Rafatdin on 06.10.2016.
 */
@Entity
@Table(name = "user_in_languages", schema = "public", catalog = "LgErpSystem")
public class UserInLanguagesEntity {
    private int id;
    private String memberName;
    private int userId;
    private int languageId;
    private String listening;
    private String writing;
    private String speaking;
    private String reading;
    private UsersEntity usersByUserId;
    private LanguagesEntity languagesByLanguageId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "member_name", nullable = false, length = 50)
    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    @Basic
    @Column(name = "user_id", nullable = false, insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "language_id", nullable = false, insertable = false, updatable = false)
    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    @Basic
    @Column(name = "listening", nullable = true, length = 50)
    public String getListening() {
        return listening;
    }

    public void setListening(String listening) {
        this.listening = listening;
    }

    @Basic
    @Column(name = "writing", nullable = true, length = 50)
    public String getWriting() {
        return writing;
    }

    public void setWriting(String writing) {
        this.writing = writing;
    }

    @Basic
    @Column(name = "speaking", nullable = true, length = 50)
    public String getSpeaking() {
        return speaking;
    }

    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }

    @Basic
    @Column(name = "reading", nullable = true, length = 50)
    public String getReading() {
        return reading;
    }

    public void setReading(String reading) {
        this.reading = reading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInLanguagesEntity that = (UserInLanguagesEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (languageId != that.languageId) return false;
        if (memberName != null ? !memberName.equals(that.memberName) : that.memberName != null) return false;
        if (listening != null ? !listening.equals(that.listening) : that.listening != null) return false;
        if (writing != null ? !writing.equals(that.writing) : that.writing != null) return false;
        if (speaking != null ? !speaking.equals(that.speaking) : that.speaking != null) return false;
        if (reading != null ? !reading.equals(that.reading) : that.reading != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (memberName != null ? memberName.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + languageId;
        result = 31 * result + (listening != null ? listening.hashCode() : 0);
        result = 31 * result + (writing != null ? writing.hashCode() : 0);
        result = 31 * result + (speaking != null ? speaking.hashCode() : 0);
        result = 31 * result + (reading != null ? reading.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UsersEntity getUsersByUserId() {
        return usersByUserId;
    }

    public void setUsersByUserId(UsersEntity usersByUserId) {
        this.usersByUserId = usersByUserId;
    }

    @ManyToOne
    @JoinColumn(name = "language_id", referencedColumnName = "id", nullable = false)
    public LanguagesEntity getLanguagesByLanguageId() {
        return languagesByLanguageId;
    }

    public void setLanguagesByLanguageId(LanguagesEntity languagesByLanguageId) {
        this.languagesByLanguageId = languagesByLanguageId;
    }
}
