package Togedy.server.Util.Exception.Domain;

import Togedy.server.Util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class UserException extends CustomException {
    public UserException(BaseResponseStatus status) { super(status); }
}
