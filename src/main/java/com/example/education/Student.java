package com.example.education;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String nameAr;
	private String address;
	private String telephoneNum;
	private String allocatedCourse;

	public Student(Long id, String name, String nameAr, String address, String telephoneNum, String allocatedCourse) {
		super();
		this.id = id;
		this.name = name;
		this.nameAr = nameAr;
		this.address = address;
		this.telephoneNum = telephoneNum;
		this.allocatedCourse = allocatedCourse;
	}

	public Student() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameAr() {
		return nameAr;
	}

	public void setNameAr(String nameAr) {
		this.nameAr = nameAr;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephoneNum() {
		return telephoneNum;
	}

	public void setTelephoneNum(String telephoneNum) {
		this.telephoneNum = telephoneNum;
	}

	public String getAllocatedCourse() {
		return allocatedCourse;
	}

	public void setAllocatedCourse(String allocatedCourse) {
		this.allocatedCourse = allocatedCourse;
	}

}
