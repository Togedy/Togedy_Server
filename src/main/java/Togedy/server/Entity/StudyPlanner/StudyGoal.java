package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Study_Goals")
@Getter
@NoArgsConstructor
public class StudyGoal extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_goal_id")
    private Long id;

    private LocalDate date;

    private LocalTime targetTime; // HH:mm

    private LocalTime actualTime; // HH:mm

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id")
    private Planner planner;

    public int calculateAchievement() {
        // 목표 시간 대비 실제 시간의 비율 계산
        long targetMinutes = targetTime.toSecondOfDay() / 60;
        long actualMinutes = actualTime.toSecondOfDay() / 60;
        return (int) ((actualMinutes / (double) targetMinutes) * 100);
    }

}
