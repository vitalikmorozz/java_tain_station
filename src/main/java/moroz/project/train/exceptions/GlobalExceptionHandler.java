package moroz.project.train.exceptions;

import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {NotFoundException.class})
    public ErrorMessage handleNotFoundException(NotFoundException ex) {
        return new ErrorMessage(ex.getMessage(), HttpStatus.NOT_FOUND, 404, LocalDateTime.now());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = {BadRequestException.class})
    public ErrorMessage handleBadRequestException(BadRequestException ex) {
        return processResponseStatusException(ex);
    }

    @ResponseStatus(value = HttpStatus.CONFLICT)
    @ExceptionHandler(value = {ConflictException.class})
    public ErrorMessage handleConflictException(ConflictException ex) {
        return processResponseStatusException(ex);
    }

    private ErrorMessage processResponseStatusException(ResponseStatusException ex) {
        return new ErrorMessage(ex.getReason(), ex.getStatus(), ex.getRawStatusCode(), LocalDateTime.now());
    }
}
