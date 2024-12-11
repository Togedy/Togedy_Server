package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Study_Tags")
@Getter
@NoArgsConstructor
public class StudyTag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_tag_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id")
    private Planner planner;
}
