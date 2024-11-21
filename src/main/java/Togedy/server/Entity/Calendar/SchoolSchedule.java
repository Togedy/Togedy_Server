package Togedy.server.Entity.Calendar;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Schedule_School")
@Getter
@NoArgsConstructor
public class SchoolSchedule extends Schedule {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id", nullable = false)
    private School school;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "personal_schedule_id")
    private PersonalSchedule sharedPersonal;
}
