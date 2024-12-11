package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Study_Plan")
@Getter
@NoArgsConstructor
public class StudyPlan extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_plan_id")
    private Long id;

    private LocalDate date;
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id")
    private Planner planner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "study_tag_id")
    private StudyTag tag;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;
}
