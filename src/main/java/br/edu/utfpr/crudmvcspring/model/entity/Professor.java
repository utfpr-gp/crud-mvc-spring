package br.edu.utfpr.crudmvcspring.model.entity;

import javax.persistence.*;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Entity
@Table(name = "professors")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Professor {

    @Id
    @NonNull
    private String cpf;

    @Column(name = "created_date", nullable = false, updatable = false)
    @CreatedDate
    private long createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private long modifiedDate;

    @NonNull
    private String name;

    @NonNull
    private String departament;

    @ManyToOne
    @NonNull
    private University university;
}
