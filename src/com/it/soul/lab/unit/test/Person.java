package com.it.soul.lab.unit.test;

import java.sql.Date;
import java.sql.Timestamp;

import com.it.soul.lab.sql.entity.Entity;
import com.it.soul.lab.sql.entity.PrimaryKey;
import com.it.soul.lab.sql.entity.Property;
import com.it.soul.lab.sql.entity.TableName;
import com.it.soul.lab.sql.query.models.DataType;

/*
 * CREATE TABLE Person
(
    uuid varchar(512) PRIMARY KEY NOT NULL,
    name varchar(512),
    age int,
    isActive boolean,
    salary double,
    dob DATETIME,
    height float
);
 */

@TableName(value = "Person", acceptAll = false)
public class Person extends Entity {
	@Property
	@PrimaryKey(name = "uuid", autoIncrement = false)
	private String uuid;
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	@Property(defaultValue="towhid-islam")
	private String name;
	
	@Property(defaultValue="34", type = DataType.INT)
	private Integer age;
	
	@Property(defaultValue="true", type = DataType.BOOL)
	private Boolean isActive;
	
	@Property(defaultValue="0.00", type = DataType.DOUBLE)
	private Double salary;
	
	private Date dob;
	
	@Property(defaultValue="2010-06-21 21:01:01", type=DataType.SQLTIMESTAMP, parseFormat="yyyy-MM-dd HH:mm:ss")
	private Timestamp createDate;
	
	private Float height;
	
	public Person() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public Float getHeight() {
		return height;
	}
	public void setHeight(Float height) {
		this.height = height;
	}
	public Timestamp getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}
}
