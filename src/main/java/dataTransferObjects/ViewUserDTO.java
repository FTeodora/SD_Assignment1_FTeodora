package dataTransferObjects;

import entity.User;
import entity.enums.Roles;
import presentation.util.formConverter.SearchSwingField;
import presentation.util.formConverter.SwingField;

import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;
import service.validators.CNPValidator;
import service.validators.EMailValidator;
import service.validators.MandatoryFieldValidator;
import service.validators.PhoneValidator;

public class ViewUserDTO {
    private String id;
    @SwingField(labelName = "e-mail:",validator = EMailValidator.class)
    @SearchSwingField(labelName = "e-mail:")
    @SwingWindow(additionalLabel = "\ne-mail: ",positionInWindow = FieldPosition.LEFT)
    private String eMail;
    @SwingField(labelName = "First Name:",validator = MandatoryFieldValidator.class)
    @SearchSwingField(labelName = "First Name:")
    @SwingWindow(positionInWindow = FieldPosition.UP)
    private String firstName;
    @SwingField(labelName = "Last Name:",validator = MandatoryFieldValidator.class)
    @SearchSwingField(labelName = "Last Name:")
    @SwingWindow(additionalLabel = "  ",positionInWindow = FieldPosition.UP)
    private String lastName;
    @SwingField(validator = CNPValidator.class)
    @SearchSwingField
    @SwingWindow(additionalLabel = "\nCNP: ",positionInWindow = FieldPosition.CENTER)
    private String cnp;
    @SwingField(labelName = "phone:",validator = PhoneValidator.class)
    @SearchSwingField(labelName = "phone:")
    @SwingWindow(additionalLabel = "\nphone:",positionInWindow = FieldPosition.RIGHT)
    private String phoneNumber;
    @SwingWindow(additionalLabel="\n")
    private Roles role;
    public ViewUserDTO(){}
    public ViewUserDTO(User user){
        this.id=user.getId();
        this.eMail=user.getEMail();
        this.firstName=user.getFirstName();
        this.lastName=user.getLastName();
        this.phoneNumber=user.getPhone();
        this.cnp=user.getCnp();
        this.role=user.getRole();
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEMail() {
        return eMail;
    }

    public void setEMail(String eMail) {
        this.eMail = eMail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String CNP) {
        this.cnp = CNP;
    }
    public User toUser(){
        User user=new User();
        user.setId(this.id);
        user.setEMail(this.eMail);
        user.setCnp(this.cnp);
        user.setFirstName(this.firstName);
        user.setLastName(this.lastName);
        user.setPhone(this.phoneNumber);

        return user;
    }
    public String toString(){
        return this.getFirstName()+" "+this.lastName;
    }
}
