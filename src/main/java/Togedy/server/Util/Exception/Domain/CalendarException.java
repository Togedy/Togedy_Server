package Togedy.server.Util.Exception.Domain;

import Togedy.server.Util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class CalendarException extends CustomException{
    public CalendarException(BaseResponseStatus status) { super(status); }
}
