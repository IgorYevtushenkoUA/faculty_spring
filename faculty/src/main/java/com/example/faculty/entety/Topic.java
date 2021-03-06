package com.example.faculty.entety;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "topic")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "topic")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Course> courses;

    public Topic(int i, String name) {
        this.id = i;
        this.name = name;
    }
}
