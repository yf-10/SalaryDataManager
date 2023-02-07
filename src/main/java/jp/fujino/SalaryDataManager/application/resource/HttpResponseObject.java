package jp.fujino.SalaryDataManager.application.resource;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HttpResponseObject {

    // HTTP Status
    private String httpStatus;
    // Message
    private String message;
    // Object
    private Object object;

    public HttpResponseObject(){};

}
