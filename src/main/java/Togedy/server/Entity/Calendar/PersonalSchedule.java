package Togedy.server.Entity.Calendar;

import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Table(name = "Schedule_Personal")
@Getter
public class PersonalSchedule extends Schedule{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private boolean isShared = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_schedule_id")
    private SchoolSchedule schoolSchedule;

    @Builder
    public PersonalSchedule(User user, String name, String memo, LocalDate startDate, LocalDate endDate, Category category) {
        super(name, memo, startDate, endDate, category);
        this.user = user;

    }
}
