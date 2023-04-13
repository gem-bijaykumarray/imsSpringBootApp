package com.gemini.mis.Exception;
import org.jooq.exception.DataAccessException;

public class ServiceException extends DataAccessException {

private String errorMessage = null;


public ServiceException(String msg) {
super(msg);
}

public ServiceException(String message, String errorMessage) {
super(message);
this.errorMessage = errorMessage;
}

public String getErrorMessage() {
return errorMessage;
}

public void setErrorMessage(String errorMessage) {
this.errorMessage = errorMessage;
}
}
