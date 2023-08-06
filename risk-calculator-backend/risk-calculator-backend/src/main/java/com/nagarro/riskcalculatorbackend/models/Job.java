package com.nagarro.riskcalculatorbackend.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import com.nagarro.riskcalculatorbackend.enums.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class for Job
 * @author parasgautam
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="job_status")
public class Job {
	
	@Id
	@GeneratedValue
	@Column(name="Id")
	private Long id;
	
	@Column(name="Timestamp")
	private String date;
	
	@Column(name="Description")
	private String desc;
	
	@Enumerated(EnumType.STRING)
	private JobStatus jobStatus;
	
	
}