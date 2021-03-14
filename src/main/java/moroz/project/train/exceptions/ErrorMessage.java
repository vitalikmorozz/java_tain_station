package moroz.project.train.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ErrorMessage {
    private String message;
    private HttpStatus status;
    private int statusCode;
    private LocalDateTime timestamp;
}
