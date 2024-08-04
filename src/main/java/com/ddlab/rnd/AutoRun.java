package com.ddlab.rnd;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.entity.Branch;
import com.ddlab.rnd.entity.Department;
import com.ddlab.rnd.entity.Employee;
import com.ddlab.rnd.entity.Organization;
import com.ddlab.rnd.entity.Project;
import com.ddlab.rnd.repository.OrgRepository;

@Component
public class AutoRun {

	@Autowired
	private OrgRepository orgRepo;

	private void saveOrg() {
		Organization org = new Organization("Microsoft");

		Set<Employee> empList1 = Set.of(new Employee("John"), new Employee("Vidya"), new Employee("Rambha"));
		Branch branch1 = new Branch("Delhi");
		branch1.setEmployees(empList1);
		Set<Employee> empList2 = Set.of(new Employee("Urbasi"), new Employee("Meneka"), new Employee("Ramesh"));
		Branch branch2 = new Branch("Bangalore");
		branch2.setEmployees(empList2);

		Set<Branch> branches = Set.of(branch1, branch2);
		Set<Department> departements = Set.of(new Department("Finance"), new Department("IT"));
		Set<Project> projects = Set.of(new Project("Tombola"), new Project("DelTobosso"));

		org.setBranches(branches);
		org.setDepartments(departements);
		org.setProjects(projects);

		orgRepo.save(org);

	}

	public void sleep(long time) {
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void showOrgDetails() {
		Organization org = orgRepo.findById(1L).get();
		System.out.println("Org: " + org);
		System.out.println("------------ GET Branch Details --------------");
		sleep(5);
		org.getBranches().forEach(branch -> {
			System.out.println("Branch: " + branch);
			System.out.println("------------ GET Employee Details --------------");
			sleep(5);
			branch.getEmployees().forEach(emp -> System.out.println("Emp: " + emp));
		});
	}
	
	public void showOrgDetailsByName() {
		Organization org = orgRepo.getOrganizationByName("Microsoft");
		System.out.println("Org: " + org);
		System.out.println("------------ GET Branch Details --------------");
//		sleep(5);
		org.getBranches().forEach(branch -> {
			System.out.println("Branch: " + branch);
			System.out.println("------------ GET Employee Details --------------");
//			sleep(5);
			branch.getEmployees().forEach(emp -> System.out.println("Emp: " + emp));
		});
	}
	
	public void getOrgDetailsAlongWithProject() {
		Organization org = orgRepo.getOrgAlongWithProject("Microsoft");
		System.out.println("Org: " + org);
		System.out.println("------------ GET Project Details --------------");
//		sleep(5);
		org.getProjects().forEach(project -> {
			System.out.println("Project: " + project);
		});
	}
	
	

	@EventListener(ApplicationReadyEvent.class)
	public void run() {
		System.out.println("App running ......");
//		saveOrg();
//		showOrgDetails();
//		showOrgDetailsByName();
		getOrgDetailsAlongWithProject();
	}
}
