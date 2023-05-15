package org.tutorial.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "EMP2")
@Entity
@NamedQuery(name = "emp.all", query = "select emp from EmpDO emp")
public class EmpDO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EMPNO", nullable = false, columnDefinition = "NUMBER(4)")
	private Integer empno;

    @Column(name = "ENAME", columnDefinition = "VARCHAR2(10 CHAR)")
    private String ename;

    @Column(name = "JOB", columnDefinition = "VARCHAR2(9 CHAR)")
    private String job;

    @Column(name = "HIREDATE", columnDefinition = "DATE")
    private LocalDate hiredate;

    @Column(name = "SAL", columnDefinition = "NUMBER(7, 2)")
    private Double sal;

    @Column(name = "COMM", columnDefinition = "NUMBER(7, 2)")
    private Double comm;

    @ManyToOne
    @JoinColumn(name = "DEPTNO")
    private DeptDO deptDO;
	
	public EmpDO() {
	}
}
