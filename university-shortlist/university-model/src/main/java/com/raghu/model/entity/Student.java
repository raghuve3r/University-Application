package com.raghu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="Student")
public class Student {
	
	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@NotNull(message="First name cannot be empty!")
	@Column(name="first_name")
	private String firstName;
	
	@NotNull(message="Last name cannot be empty!")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull(message="Age cannot be empty!")
	@Min(value=0, message="Age cannot be negative!")
	@Max(value=100, message="Get out of here! No way you are 100!")
	@Column(name="age")
	private Integer age;
	
	@NotNull(message="Should specify a gender.")
	@Column(name="gender")
	private String gender;
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="university_id")
	@NotNull(message="University should not be null")
	private University university;

	public University getUniversity() {
		return university;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public Student() {
	
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public String toString() {
		return "firstName " + firstName + " lastName " + lastName + ", age " + age;
	}
	
	
}
