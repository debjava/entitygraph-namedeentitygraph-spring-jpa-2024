package com.ddlab.rnd.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Branch")
@Table(name = "branch")
@Getter @Setter
@NoArgsConstructor
@ToString(exclude = "employees")
public class Branch {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	public Branch(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Employee> employees = new HashSet<>();
}
