package Togedy.server.Util.Exception;

import Togedy.server.Util.BaseResponseStatus;
import io.jsonwebtoken.JwtException;
import lombok.Getter;

@Getter
public class CustomJwtException extends JwtException {

    private final BaseResponseStatus status;

    public CustomJwtException(BaseResponseStatus status) {
        super(status.getResponseMessage());
        this.status = status;
    }
}
