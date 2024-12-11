package Togedy.server.Util.Exception.Domain;

import Togedy.server.Util.BaseResponseStatus;
import lombok.Getter;

@Getter
public class PlannerException extends CustomException {
    public PlannerException(BaseResponseStatus status) { super(status); }
}
