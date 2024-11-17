package Togedy.server.Entity.Calendar;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity
@Table(name = "Schedule_Category")
@Getter
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    private String name;

    private String color;

    @OneToMany(mappedBy = "category")
    private List<Schedule> schedules;



}
