/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.utfpr.crudmvcspring.model.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author ronifabio
 */
@Entity
@Table(name = "universities")
@Data
public class University implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String abbvr;

    @Column
    private String city;

    @Column
    private String state;

    @OneToMany
    Set<Professor> professors;

    public University() {
    }

    public University(String name, String abbvr, String city, String state) {
        this.name = name;
        this.abbvr = abbvr;
        this.city = city;
        this.state = state;
    }

    @Override
    public String toString() {
        return "br.edu.utfpr.model.Universidade[ id=" + id + " ]";
    }

}
