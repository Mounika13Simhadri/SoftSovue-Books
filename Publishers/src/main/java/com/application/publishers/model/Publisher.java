package com.application.publishers.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="publisher")
public class Publisher {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@NotNull
	private String name;
	@NotNull
	private String URL;
	@NotNull
	private String suffix;
	private String logo;
	
	public Publisher() {
		super();
	}
	public Publisher(Long id, @NotNull String name, @NotNull String uRL, @NotNull String suffix, String logo) {
		super();
		this.id = id;
		this.name = name;
		this.URL = uRL;
		this.suffix = suffix;
		this.logo = logo;
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
	public String getURL() {
		return URL;
	}
	public void setURL(String uRL) {
		URL = uRL;
	}
	public String getSuffix() {
		return suffix;
	}
	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	@Override
	public String toString() {
		return "Publisher [id=" + id + ", name=" + name + ", URL=" + URL + ", suffix=" + suffix + ", logo=" + logo
				+ ", publisherAdmin_Id=" + "]";
	}
		
}
