package org.tutorial.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "DEPT2")
@Entity
@NamedQuery(name = "dept.all", query = "SELECT dept FROM DeptDO dept")
public class DeptDO implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "DEPTNO", nullable = false, columnDefinition = "INT AUTO_INCREMENT")
	private Integer deptno;

    @Column(name = "DNAME", columnDefinition = "VARCHAR(14)")
    private String dname;

    @Column(name = "LOC", columnDefinition = "VARCHAR(13)")
    private String loc;

    @OneToMany(mappedBy = "deptDO", cascade = CascadeType.REMOVE)
    private List<EmpDO> empDOs;
	
	public DeptDO() {
	}
}
