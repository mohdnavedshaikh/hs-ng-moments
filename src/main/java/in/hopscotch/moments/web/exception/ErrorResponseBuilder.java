package in.hopscotch.moments.web.exception;

import org.springframework.stereotype.Component;

@Component
public class ErrorResponseBuilder {

    public ErrorResponse createErrorResponse(Throwable e) {
        ErrorResponse error = new ErrorResponse();
        error.message = e.getMessage();
        error.exceptionClass = e.getClass().getName();
        error.exceptionTrace = ExceptionUtils.stackTrace(e);
        return error;
    }

}
