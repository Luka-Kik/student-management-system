package se.iths.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String name;

    @ManyToOne
    private Teacher teacher;

    @ManyToMany(mappedBy = "subjects")
    private Set<Student> students;

    public Subject() {
    }

    public Subject(String name, Set<Student> students) {
        this.name = name;
        this.students = students;
    }

    public void addStudent(Student student){
        students.add(student);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
