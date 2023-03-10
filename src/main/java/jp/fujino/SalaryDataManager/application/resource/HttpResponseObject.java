package jp.fujino.SalaryDataManager.application.resource;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class HttpResponseObject {

    // HTTP Status
    private HttpStatus httpStatus;
    // Message
    private String message;
    // Response Data
    private Object responseData;

}
