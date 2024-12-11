package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Study_Plans")
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
    @JoinColumn(name = "study_tag_id", nullable = false)
    private StudyTag studyTag;

    @Enumerated(EnumType.STRING)
    private PlanStatus status = PlanStatus.NOT_STARTED;

    public static StudyPlan createStudyPlan(Planner planner, String name, LocalDate date, StudyTag studyTag) {
        StudyPlan studyPlan = new StudyPlan();
        studyPlan.planner = planner;
        studyPlan.name = name;
        studyPlan.date = date;
        studyPlan.studyTag = studyTag;
        return studyPlan;
    }
}
