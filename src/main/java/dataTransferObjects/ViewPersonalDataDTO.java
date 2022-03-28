package dataTransferObjects;

import entity.User;
import presentation.util.formConverter.SwingField;
import presentation.util.formConverter.fields.JLabelField;

import java.util.Date;

public class ViewPersonalDataDTO {
    @SwingField(componentType = JLabelField.class,labelName = "e-mail:")
    private String eMail;
    @SwingField(componentType = JLabelField.class,labelName = "First name:")
    private String firstName;
    @SwingField(componentType = JLabelField.class,labelName = "Last name:")
    private String lastName;
    @SwingField(componentType = JLabelField.class,labelName = "CNP:")
    private String CNP;
    @SwingField(componentType = JLabelField.class,labelName = "Phone number:")
    private String phone;
    @SwingField(componentType = JLabelField.class,labelName = "Joined at:")
    private Date createdAt;


    public String getEMail() {
        return eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCNP() {
        return CNP;
    }

    public String getPhone() {
        return phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public ViewPersonalDataDTO(){

    }
    public ViewPersonalDataDTO(User user){
        this.eMail=user.getEMail();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.CNP=user.getCnp();
        this.phone=user.getPhone();
        this.createdAt=user.getCreatedAt();
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
