/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitwaves.SimpleCrudJpa1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Version;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Where;

/**
 *
 * @author amit
 */
@Data
@Entity
@Table(name = "EMPLOYER")
@Where(clause = "deleted = false")
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Version
    @JsonIgnore
    private Long version = 1L;

    @Column(name = "name")
    private String name;

    @Column(name = "last_updated_timestamp")
    private ZonedDateTime lastUpdatedTimestamp;

    @Column(name = "total_cost")
    private Double totalCost;

    @Column(name = "deleted")
    private Boolean deleted = false;

    @OneToMany(mappedBy = "employer", fetch = FetchType.EAGER, 
            cascade = {CascadeType.ALL}, orphanRemoval = true)
//    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    @Fetch(FetchMode.SUBSELECT)
    private List<Project> projects = new ArrayList<>();

    public void calculateTotalCost() {
        //        Double sum = projects.stream().mapToDouble(Project::getCost).sum();
        totalCost = projects.stream().collect(Collectors.summingDouble(Project::getCost));
    }

    private void calculateLastUpdateTimeStamp() {
        lastUpdatedTimestamp = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        calculateTotalCost();
        calculateLastUpdateTimeStamp();
    }

    @PrePersist
    void prePersist() {
        calculateLastUpdateTimeStamp();
    }

    public void addProject(Project project) {
        if (null == project.getEmployer()) {
            project.setEmployer(this);
        }
    }

    public void removeProject(Project project) {
        project.setEmployer(null);
    }

}
