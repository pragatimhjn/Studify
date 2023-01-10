package com.itvedant.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Student {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Integer id;
@Column
private String name;
@Column
private String email;
@Column
private Double aggregate;
}
