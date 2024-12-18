package Togedy.server.Entity.Calendar;

import Togedy.server.Entity.BaseEntity;
import Togedy.server.Entity.BaseStatus;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "Schedule_Category")
@Getter
@NoArgsConstructor
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;

    private String color;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_status")
    private BaseStatus baseStatus = BaseStatus.ACTIVE;

    @Builder
    public Category(User user, String name, String color) {
        this.user = user;
        this.name = name;
        this.color = color;
    }

    public void updateInactive() {
        this.baseStatus = BaseStatus.INACTIVE;
    }

}
