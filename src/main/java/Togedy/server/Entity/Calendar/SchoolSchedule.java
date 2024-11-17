package Togedy.server.Entity.Calendar;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Schedule_School")
@Getter
public class SchoolSchedule extends Schedule {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_schedule_id")
    private PersonalSchedule sharedPersonal;
}
