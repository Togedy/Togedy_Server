package Togedy.server.Entity.Board.Post;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
@DiscriminatorValue("MARKET")
public class MarketPost extends Post {

    @Enumerated(EnumType.STRING)
    private MarketStatus status = MarketStatus.ONGOING; // 거래 상태

}
