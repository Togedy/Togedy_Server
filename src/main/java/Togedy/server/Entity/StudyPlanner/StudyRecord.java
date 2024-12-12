package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "Study_Records")
@Getter
@NoArgsConstructor
public class StudyRecord extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "study_record_id")
    private Long id;

    private LocalDate date;

    private LocalTime startTime;

    private LocalTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "planner_id")
    private Planner planner;

    @Builder
    public StudyRecord(LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }


}
