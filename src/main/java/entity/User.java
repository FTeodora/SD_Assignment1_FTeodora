package entity;

import entity.enums.Roles;
import org.hibernate.annotations.ColumnDefault;
import presentation.util.formConverter.SwingField;
import presentation.util.formConverter.fields.CharArrayWrapper;
import presentation.util.formConverter.fields.JPasswordFieldForm;
import service.validators.*;

import java.util.UUID;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.ConcurrentSkipListSet;

@Entity
@Table(name="user")
public class User implements Serializable {
	
	@Id
	@Column(name="user_id")
	private String id;
	@SwingField(labelName = "e-mail*:",validator = InsertEmailValidator.class)
	@Column(unique = true,nullable = false)
	private String eMail;
	@SwingField(labelName = "password*:",componentType = JPasswordFieldForm.class,validator= PassWordValidator.class)
	@Column(nullable = false,columnDefinition = "varchar(255)")
	private char[] password;
	@SwingField(labelName = "first name*:",validator = MandatoryFieldValidator.class)
	@Column(nullable = false)
	private String firstName;
	@SwingField(labelName = "last name*",validator = MandatoryFieldValidator.class)
	@Column(nullable = false)
	private String lastName;
	@SwingField(labelName = "CNP*:",validator = CNPValidator.class)
	@Column(nullable = false,columnDefinition = "varchar(13)")
	private String cnp;
	@SwingField(labelName = "phone:",validator = PhoneValidator.class)
	@Column(columnDefinition = "varchar(10)")
	@ColumnDefault("NULL")
	private String phone;
	@Column(nullable = false)
	private Roles role;
	@Column(nullable = false)
	private Date createdAt;
	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private List<PersonalProperty> homeAddress;
	@OneToMany(mappedBy = "requester",fetch=FetchType.LAZY,cascade = CascadeType.ALL)
	private Set<Request> requests;
	public User() {
		requests=new ConcurrentSkipListSet<>();
	}

	public List<PersonalProperty> getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(List<PersonalProperty> homeAddress) {
		this.homeAddress = homeAddress;
	}

	public Set<Request> getRequests() {
		return requests;
	}

	public void setRequests(Set<Request> requests) {
		this.requests = requests;
	}
	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getEMail() {
		return eMail;
	}

	public void setEMail(String eMail) {
		this.eMail = eMail;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
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

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String CNP) {
		this.cnp = CNP;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void autofillFields(){
		this.id=this.id=UUID.randomUUID().toString();
		this.createdAt=new Date();
	}
	@Override
	public String toString(){
		return this.firstName+" "+this.lastName;
	}
}
