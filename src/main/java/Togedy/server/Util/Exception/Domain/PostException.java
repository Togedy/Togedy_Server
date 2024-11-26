package Togedy.server.Util.Exception.Domain;

import Togedy.server.Util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class PostException extends CustomException{

    public PostException(BaseResponseStatus status) { super(status); }
}
