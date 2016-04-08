package org.erp.distribution.model;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="pteam")
public class PTeam {

	@Id
	@Column(name="ID")
	private Long id;
	@Column(name="NIP", length=100)
	private String nip;
	@Column(name="NAMA", length=100)
	private String nama;
	@Column(name="ALAMAT", length=100)
	private String alamat;
	
	@Column(name="TEMPATLAHIR", length=100)
	private String tempatlahir;
	@Temporal(TemporalType.DATE)
	private Date tgllahir;

	@Column(name="PENDIDIKAN", length=100)
	private String pendidikan;
	@Column(name="AGAMA")
	private int agama;

//	@ManyToOne
//	@JoinColumn(name="fsalesmanBean", referencedColumnName="spcode")
//	private FSalesman fsalesmanBean;
	
	
	
}