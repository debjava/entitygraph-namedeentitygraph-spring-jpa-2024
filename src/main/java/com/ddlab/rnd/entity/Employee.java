package com.ddlab.rnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Employee")
@Table(name = "emp")
@Getter @Setter
@ToString @NoArgsConstructor
public class Employee {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	public Employee(String name) {
		this.name = name;
	}
}
