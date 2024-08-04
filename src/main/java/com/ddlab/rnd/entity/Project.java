package com.ddlab.rnd.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Project")
@Table(name = "project")
@Getter @Setter
@ToString @NoArgsConstructor
public class Project {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	public Project(String name) {
		this.name = name;
	}
}
