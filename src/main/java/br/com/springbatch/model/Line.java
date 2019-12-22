package br.com.springbatch.model;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * classe que atuará como um DTO para transferir os dados entre as etapas.
 * De acordo com o Spring Batch, os objetos transferidos entre as etapas 
 * devem ser serializáveis .
 *
 */
public class Line implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String name;
	private LocalDate localDate;
	private Long age;
	
	public Line(String name, LocalDate localDate) {
		this.name = name;
		this.localDate = localDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getLocalDate() {
		return localDate;
	}

	public void setLocalDate(LocalDate localDate) {
		this.localDate = localDate;
	}

	public Long getAge() {
		return age;
	}

	public void setAge(Long age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Line [name=" + name + ", localDate=" + localDate + ", age=" + age + "]";
	}
	
}
