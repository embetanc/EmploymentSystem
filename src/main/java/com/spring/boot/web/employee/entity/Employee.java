package com.spring.boot.web.employee.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.OptBoolean;

@Entity
@Table(name = "employee", schema = "targetSchemaName")
public class Employee implements Comparable<Employee> {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id",unique=true, columnDefinition="text")
	private Long id;
	@Column(name = "uid")
	private String uid;
	@Column(name = "firstname")
	@Valid
	@Size(min = 2, max = 20, message="Length should be between 2 and 20 characters.")
	@Pattern(regexp="^$|[A-Za-z]+",message="Only Alphabetic [A-Z] characters allowed")
	private String firstName;
	@Column(name = "middlename")
	@Valid
	@Pattern(regexp="(^$|.{2,20})", message="Length should be between 2 and 20 characters.")
	@Pattern(regexp="^$|[A-Za-z]+",message="Only Alphabetic [A-Z] characters allowed")
	//@Pattern(regexp = "|.{2,20}", message="Length should be between 2 and 20 characters.")
	private String middleName;
	@Column(name = "lastname")
	@Valid
	@Size(min = 2, max = 20, message="Length should be between 2 and 20 characters.")
	@Pattern(regexp="^$|[A-Za-z]+",message="Only Alphabetic [A-Z] characters allowed")
	private String lastName;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday", columnDefinition="text")
	@Valid
	@Past(message="Birthday must be in the past")
	@NotNull(message="Birthday must not be empty")
	private Date birthday;
	@Column(name = "position")
	@Valid
	@Size(min = 2, max = 20, message="Length should be between 2 and 20 characters.")
	private String position;
	

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getUid() {
		return uid;
	}


	public void setUid(String uid) {
		this.uid = uid;

	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getMiddleName() {
		return middleName;
	}


	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "Employee [id=" + id + ", uid=" + uid + ", firstName=" + firstName + ", middleName=" + middleName
				+ ", lastName=" + lastName + ", birthday=" + birthday + ", position=" + position + "]";
	}


	@Override
	public int compareTo(Employee employee) {
		return this.id.compareTo(employee.id);
	}
}
