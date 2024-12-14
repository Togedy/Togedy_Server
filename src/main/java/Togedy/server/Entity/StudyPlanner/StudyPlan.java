package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
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

    @Builder
    public StudyPlan(Planner planner, String name, LocalDate date, StudyTag studyTag) {
        this.planner = planner;
        this.name = name;
        this.date = date;
        this.studyTag = studyTag;
    }

    public void updateStatus(PlanStatus status) {
        this.status = status;
    }
}
