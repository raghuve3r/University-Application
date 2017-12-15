package com.raghu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="UNIVERSITY")

public class University {


	@Id
	@GeneratedValue
	@Column(name="id")
	private Integer id;
	
	@Column(name="university_name")
	@NotNull(message="Field cannot be empty!")
	private String universityName;
	
	@Column(name="university_city")
	@NotNull(message="Field cannot be empty!")
	private String universityCity;
	
	@Column(name="university_country")
	@NotNull(message="Field cannot be empty!")
	private String universityCountry;

	public University() {
		
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUniversityName() {
		return universityName;
	}

	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}

	public String getUniversityCity() {
		return universityCity;
	}

	public void setUniversityCity(String universityCity) {
		this.universityCity = universityCity;
	}

	public String getUniversityCountry() {
		return universityCountry;
	}

	public void setUniversityCountry(String universityCountry) {
		this.universityCountry = universityCountry;
	}

	@Override
	public String toString() {
		return this.universityName;
	}
	
	
	
}
