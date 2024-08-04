package com.ddlab.rnd.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name="Organization")
@Table(name = "org")
@NoArgsConstructor
@Setter @Getter
@ToString(exclude = {"branches", "departments", "projects"})
@NamedEntityGraph(
		name = "Org.branch-with-emp-and-project-eager-type",
		attributeNodes = 
				{
						@NamedAttributeNode(value = "branches", subgraph = "empList" /* For inner object */),
						@NamedAttributeNode("projects")
				})
public class Organization {

	@Id @GeneratedValue
	private Long id;
	
	private String name;
	
	public Organization(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Branch> branches = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Department> departments = new HashSet<>();
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Project> projects = new HashSet<>();
}
