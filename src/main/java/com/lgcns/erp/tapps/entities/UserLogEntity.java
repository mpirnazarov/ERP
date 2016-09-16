package com.lgcns.erp.tapps.entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by Rafatdin on 16.09.2016.
 */
@Entity
@Table(name = "User_LOG", schema = "public", catalog = "ErpSystem")
public class UserLogEntity {
    private long id;
    private String userId;
    private String oldFName;
    private String firstName;
    private String oldLName;
    private String lastName;
    private String oldPhone;
    private String phone;
    private String oldEmail;
    private String email;
    private Integer oldPosition;
    private Integer position;
    private String oldUsername;
    private String username;
    private String oldPassword;
    private String password;
    private Timestamp operationDate;
    private String operationType;
    private String user;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "UserId", nullable = true, length = 50)
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "OldFName", nullable = true, length = 50)
    public String getOldFName() {
        return oldFName;
    }

    public void setOldFName(String oldFName) {
        this.oldFName = oldFName;
    }

    @Basic
    @Column(name = "FirstName", nullable = true, length = 50)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "OldLName", nullable = true, length = 50)
    public String getOldLName() {
        return oldLName;
    }

    public void setOldLName(String oldLName) {
        this.oldLName = oldLName;
    }

    @Basic
    @Column(name = "LastName", nullable = true, length = 50)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "OldPhone", nullable = true, length = 13)
    public String getOldPhone() {
        return oldPhone;
    }

    public void setOldPhone(String oldPhone) {
        this.oldPhone = oldPhone;
    }

    @Basic
    @Column(name = "Phone", nullable = true, length = 13)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "OldEmail", nullable = true, length = 50)
    public String getOldEmail() {
        return oldEmail;
    }

    public void setOldEmail(String oldEmail) {
        this.oldEmail = oldEmail;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 50)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "OldPosition", nullable = true)
    public Integer getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Integer oldPosition) {
        this.oldPosition = oldPosition;
    }

    @Basic
    @Column(name = "Position", nullable = true)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "OldUsername", nullable = true, length = 50)
    public String getOldUsername() {
        return oldUsername;
    }

    public void setOldUsername(String oldUsername) {
        this.oldUsername = oldUsername;
    }

    @Basic
    @Column(name = "Username", nullable = true, length = 50)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "OldPassword", nullable = true, length = 64)
    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    @Basic
    @Column(name = "Password", nullable = true, length = 64)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "OperationDate", nullable = true)
    public Timestamp getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Timestamp operationDate) {
        this.operationDate = operationDate;
    }

    @Basic
    @Column(name = "OperationType", nullable = true, length = 10)
    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Basic
    @Column(name = "USER", nullable = true, length = 100)
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserLogEntity that = (UserLogEntity) o;

        if (id != that.id) return false;
        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (oldFName != null ? !oldFName.equals(that.oldFName) : that.oldFName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (oldLName != null ? !oldLName.equals(that.oldLName) : that.oldLName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (oldPhone != null ? !oldPhone.equals(that.oldPhone) : that.oldPhone != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (oldEmail != null ? !oldEmail.equals(that.oldEmail) : that.oldEmail != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (oldPosition != null ? !oldPosition.equals(that.oldPosition) : that.oldPosition != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (oldUsername != null ? !oldUsername.equals(that.oldUsername) : that.oldUsername != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (oldPassword != null ? !oldPassword.equals(that.oldPassword) : that.oldPassword != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (operationDate != null ? !operationDate.equals(that.operationDate) : that.operationDate != null)
            return false;
        if (operationType != null ? !operationType.equals(that.operationType) : that.operationType != null)
            return false;
        if (user != null ? !user.equals(that.user) : that.user != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (oldFName != null ? oldFName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (oldLName != null ? oldLName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (oldPhone != null ? oldPhone.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (oldEmail != null ? oldEmail.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (oldPosition != null ? oldPosition.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (oldUsername != null ? oldUsername.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (oldPassword != null ? oldPassword.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (operationDate != null ? operationDate.hashCode() : 0);
        result = 31 * result + (operationType != null ? operationType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }
}
