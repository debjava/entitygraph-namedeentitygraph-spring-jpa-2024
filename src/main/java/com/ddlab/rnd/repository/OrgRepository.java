package com.ddlab.rnd.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ddlab.rnd.entity.Organization;

@Repository
public interface OrgRepository extends CrudRepository<Organization, Long> {

	@EntityGraph(attributePaths = {"branches", "branches.employees"})
	Optional<Organization> findById(Long id);
	
	@EntityGraph(value = "Org.branch-with-emp-and-project-eager-type")
	Organization getOrganizationByName(String name);
	
	// EntityGraph with @Query 
	@EntityGraph(attributePaths = {"projects"})
	@Query("select org from Organization org where org.name=:name")
	Organization getOrgAlongWithProject(@Param("name") String name);
}
