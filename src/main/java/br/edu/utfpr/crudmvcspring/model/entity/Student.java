package br.edu.utfpr.crudmvcspring.model.entity;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "students")
@Data
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long registration;

    @Enumerated(EnumType.STRING)
    private GenderEnum gender;

    private Date created;
    private Date updated;

    @Column(updatable = false, nullable = false)
    private String name;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column
    private String course;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "certificate")
    private String certificateURL;

    @ManyToMany
    private Set<Subject> subjects;

    public Student() {
        super();
    }

    public Student(String name, String course, String email, Date birthDate) {
        this.name = name;
        this.course = course;
        this.email = email;
        this.birthDate = birthDate;
    }

    @PreUpdate
    public void onUpdate() {
        this.updated = new Date();
    }

    @PrePersist
    public void onSave() {
        final Date now = new Date();
        this.created = now;
        this.updated = now;
    }

    @Override
    public String toString() {
        return "Student{" + "registration=" + registration + ", name=" + name + ", course=" + course + ", birthDate=" + birthDate +'}';
    }

}
