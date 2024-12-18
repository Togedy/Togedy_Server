package Togedy.server.Entity.Calendar;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.BaseStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Schedules")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "schedule_type")
@Getter
@NoArgsConstructor
public abstract class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "schedule_id")
    private Long id;

    private String name;

    private String memo;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_status")
    private BaseStatus baseStatus = BaseStatus.ACTIVE;

    public Schedule(String name, String memo, LocalDate startDate, LocalDate endDate, Category category) {
        this.name = name;
        this.memo = memo;
        this.startDate = startDate;
        this.endDate = endDate;
        this.category = category;
    }

    public void updateInactive() {
        this.baseStatus = BaseStatus.INACTIVE;
    }
}
