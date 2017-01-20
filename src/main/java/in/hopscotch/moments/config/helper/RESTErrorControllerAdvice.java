package in.hopscotch.moments.config.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import in.hopscotch.moments.web.exception.ErrorResponse;
import in.hopscotch.moments.web.exception.ErrorResponseBuilder;
import in.hopscotch.moments.web.exception.ResourceNotFoundException;

@ControllerAdvice
public class RESTErrorControllerAdvice {
    @Autowired
    ErrorResponseBuilder errorResponseBuilder;

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse notFound(ResourceNotFoundException e) {
        return errorResponseBuilder.createErrorResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ResponseBody
    public ErrorResponse methodNotAllowed(HttpRequestMethodNotSupportedException e) {
        return errorResponseBuilder.createErrorResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse error(Throwable e) {
        return errorResponseBuilder.createErrorResponse(e);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    public ErrorResponse error(HttpMediaTypeNotSupportedException e) {
        return errorResponseBuilder.createErrorResponse(e);
    }
}
