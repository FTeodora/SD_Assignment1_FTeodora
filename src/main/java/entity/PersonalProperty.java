package entity;

import presentation.util.formConverter.SwingField;
import presentation.util.objectWindow.FieldPosition;
import presentation.util.objectWindow.SwingWindow;
import service.validators.MandatoryFieldValidator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "personal_property")
public class PersonalProperty {
    @Id
    @Column(name="address_id")
    private String id;
    @SwingField(validator = MandatoryFieldValidator.class)
    @SwingWindow( positionInWindow = FieldPosition.LEFT)
    @Column(nullable = false)
    private String district;
    @SwingField(validator = MandatoryFieldValidator.class)
    @SwingWindow(additionalLabel = ", ",positionInWindow = FieldPosition.LEFT)
    @Column(nullable = false)
    private String street;
    @SwingField(validator = MandatoryFieldValidator.class)
    @SwingWindow(additionalLabel = ", ", positionInWindow = FieldPosition.LEFT)
    @Column(nullable = false)
    private String number;
    @SwingField
    @SwingWindow(positionInWindow = FieldPosition.RIGHT)
    @Column
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;
    public PersonalProperty(){}
    public PersonalProperty(User user){
        this.user=user;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String city) {
        this.district = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public void autofillFields(){
        this.id=UUID.randomUUID().toString();
    }

}
