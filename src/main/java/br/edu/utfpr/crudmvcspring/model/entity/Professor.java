package br.edu.utfpr.crudmvcspring.model.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "professors")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Professor {

    @Id
    @Column
    private String cpf;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    @Column
    private String name;

    @Column
    private String departament;

    @ManyToOne
    private University university;

    public Professor(String cpf, String name, String departament, University university) {
        this.cpf = cpf;
        this.name = name;
        this.departament = departament;
        this.university = university;
    }
}
