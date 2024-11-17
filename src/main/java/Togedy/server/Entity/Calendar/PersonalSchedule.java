package Togedy.server.Entity.Calendar;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "Schedule_Personal")
@Getter
public class PersonalSchedule extends Schedule{

    private boolean isShared = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_schedule_id")
    private SchoolSchedule schoolSchedule;


}
