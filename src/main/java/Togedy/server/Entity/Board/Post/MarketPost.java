package Togedy.server.Entity.Board.Post;

import Togedy.server.Entity.Board.PostImage;
import Togedy.server.Entity.User.User;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@DiscriminatorValue("MARKET")
@NoArgsConstructor
public class MarketPost extends Post {
    @Enumerated(EnumType.STRING)
    @Column(name = "market_status")
    private MarketStatus status = MarketStatus.ONGOING; // 거래 상태

    @Builder
    public MarketPost(User user, String title, String content, MarketStatus status, List<PostImage> postImages) {
        super(user, title, content, postImages);
        this.status = status;
    }

}
