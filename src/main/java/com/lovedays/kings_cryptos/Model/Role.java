package com.lovedays.kings_cryptos.Model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Role  {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private Set<ApplicationUser> applicationUser;

	public Role( String name, Set<ApplicationUser> applicationUser) {
		super();
		this.name = name;
		this.applicationUser = applicationUser;
	}
	
	public Role() {}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Set<ApplicationUser> getApplicationUser() {
		return applicationUser;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setApplicationUser(Set<ApplicationUser> applicationUser) {
		this.applicationUser = applicationUser;
	}

		@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", applicationUser=" + applicationUser + "]";
	}
}
