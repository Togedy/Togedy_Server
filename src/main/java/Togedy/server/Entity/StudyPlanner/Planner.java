package Togedy.server.Entity.StudyPlanner;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Planner")
@Getter
@NoArgsConstructor
public class Planner extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "planner_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyPlan> studyPlans = new ArrayList<>();

    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyGoal> studyGoals = new ArrayList<>();

    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyTag> studyTags = new ArrayList<>();

    @OneToMany(mappedBy = "planner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudyRecord> studyRecords = new ArrayList<>();

}
