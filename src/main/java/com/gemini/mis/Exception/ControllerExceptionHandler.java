package com.gemini.mis.Exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.jooq.exception.DataAccessException;
import org.jooq.exception.DataChangedException;
import org.jooq.exception.DataTypeException;
import org.jooq.exception.DetachedException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.io.FileReader;
import java.io.ObjectStreamException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ControllerAdvice
@EnableWebMvc
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ControllerExceptionHandler.class);

    public static JSONObject getJsonObject() {
        JSONObject jsonObject = null;
        try {
            FileReader fileReader = new FileReader("src/main/resources/controllerExceptionHandlerJson.json");
            jsonObject = (JSONObject) new JSONParser().parse(fileReader);
            fileReader.close();
        } catch (Exception e) {
            log.info(e.getMessage(), e.getCause());
        }
        return jsonObject;
    }


    public static String getTime() {
        Date date = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = dateFormat.format(date);
        return strDate;
    }

    public static ErrorMessage errorBody(int statusCode, String date, String Description, String errorMessage) {
        ErrorMessage message = new ErrorMessage(statusCode, date, Description, errorMessage);
        return message;
    }

    @ExceptionHandler({DataAccessException.class, DetachedException.class, DataTypeException.class, DataChangedException.class, SQLException.class, BadSqlGrammarException.class, DataIntegrityViolationException.class})
    public ResponseEntity<ErrorMessage> daoException(Exception ex, WebRequest request) throws Exception {
        if (ex instanceof DataAccessException) {
            if ((ex.getMessage().contains("Connection") || ex.getMessage().contains("connection")) && (ex.getMessage().contains("closed") || (ex.getMessage().contains("close")))) {
                log.error("{},{}", ex.getClass().getName(), ex.getMessage());
                return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataAccessException")).get("statusCode")), getTime(), request.getDescription(false), "Connection Handle Already Closed"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataAccessException")).get("responseStatus")));
            }
            if (ex.getMessage().contains("SQL syntax")) {
                log.error("{},{}", ex.getClass().getName(), ex.getMessage());
                return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataAccessException")).get("statusCode")), getTime(), request.getDescription(false), "Error in SQL Syntax "), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataAccessException")).get("responseStatus")));
            }
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataAccessException")).get("statusCode")), getTime(), request.getDescription(false), "DataAccessException has been occurred"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataAccessException")).get("responseStatus")));
        } else if (ex instanceof DetachedException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DetachedException")).get("statusCode")), getTime(), request.getDescription(false), "DetachedException has been occurred"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DetachedException")).get("responseStatus")));
        } else if (ex instanceof DataTypeException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataTypeException")).get("statusCode")), getTime(), request.getDescription(false), "DataTypeException has been occurred"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataTypeException")).get("responseStatus")));
        } else if (ex instanceof DataChangedException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataChangedException")).get("statusCode")), getTime(), request.getDescription(false), " DataChangedException has been occurred"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataChangedException")).get("responseStatus")));
        } else if (ex instanceof SQLException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("SQLException")).get("statusCode")), getTime(), request.getDescription(false), "SQLException has been occurred"), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("SQLException")).get("responseStatus")));
        } else if (ex instanceof BadSqlGrammarException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("BadSqlGrammarException")).get("statusCode")), getTime(), request.getDescription(false), getErrorMessage(ex.getMessage())), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("BadSqlGrammarException")).get("responseStatus")));
        } else if (ex instanceof DataIntegrityViolationException) {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            return new ResponseEntity<ErrorMessage>(errorBody(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("DataIntegrityViolationException")).get("statusCode")), getTime(), request.getDescription(false), getErrorMessage(ex.getMessage())), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("DataIntegrityViolationException")).get("responseStatus")));
        } else {
            log.error("{},{}", ex.getClass().getName(), ex.getMessage());
            throw ex;
        }
    }

    public String getErrorMessage(String errorMessage) {
        String[] words = errorMessage.split(":");
        String exceptedError = words[words.length - 1].trim();
        return exceptedError;
    }


    @ExceptionHandler({ObjectStreamException.class})
    public ResponseEntity<Object> handleObjectStreamException(
        final ConstraintViolationException ex, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final ErrorMessage apiError =
                new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("ObjectStreamException")).get("statusCode")), getTime(), request.getDescription(false), "exception in serialization process");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("ObjectStreamException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            final MethodArgumentNotValidException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.error("{},{}", ex.getClass().getName(), ex.getMessage());
        final List<String> errors = new ArrayList<String>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("MethodArgumentNotValidException")).get("statusCode")), getTime(), request.getDescription(false), errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("MethodArgumentNotValidException")).get("responseStatus")), request);
    }


    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
        final MissingServletRequestParameterException ex, final HttpHeaders headers,
        final HttpStatus status, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        String error = ex.getParameterName() + " parameter is missing";

        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("MissingServletRequestParameterException")).get("statusCode")), getTime(), request.getDescription(false), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("MissingServletRequestParameterException")).get("responseStatus")));
    }


    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
        final MethodArgumentTypeMismatchException ex, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        final ErrorMessage apiError =
                new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("MethodArgumentTypeMismatchException")).get("statusCode")), getTime(), request.getDescription(false), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("MethodArgumentTypeMismatchException")).get("responseStatus")));
    }


    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Object> handleConstraintViolation(
        final ConstraintViolationException ex, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        List<String> errors = new ArrayList<String>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }
        final ErrorMessage apiError =
                new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("ConstraintViolationException")).get("statusCode")), getTime(), request.getDescription(false), String.valueOf(errors));
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("ConstraintViolationException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        final NoHandlerFoundException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("NoHandlerFoundException")).get("statusCode")), getTime(), request.getDescription(false), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("NoHandlerFoundException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final List<String> errors = new ArrayList<String>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("BindException")).get("statusCode")), getTime(), request.getDescription(false), errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("BindException")).get("responseStatus")), request);
    }


    @Override
    protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final String error = ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("TypeMismatchException")).get("statusCode")), getTime(), request.getDescription(false), error);
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("TypeMismatchException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            final HttpRequestMethodNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {

        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(
                " method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("HttpRequestMethodNotSupportedException")).get("statusCode")), getTime(), request.getDescription(false)
                , builder.toString());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("HttpRequestMethodNotSupportedException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
            final HttpMediaTypeNotSupportedException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getContentType());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("HttpMediaTypeNotSupportedException")).get("statusCode")), getTime(), request.getDescription(false)
                , builder.substring(0, builder.length() - 2));
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("HttpMediaTypeNotSupportedException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
            final ServletRequestBindingException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("ServletRequestBindingException")).get("statusCode")), getTime(), request.getDescription(false)
                , ex.getLocalizedMessage());
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("ServletRequestBindingException")).get("responseStatus")));
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            final HttpMessageNotReadableException ex,
            final HttpHeaders headers,
            final HttpStatus status,
            final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("HttpMessageNotReadableException")).get("statusCode")), getTime(), request.getDescription(false)
                , "Type of request body is not in right format");
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("HttpMessageNotReadableException")).get("responseStatus")));
    }

    @ExceptionHandler({InvalidFormatException.class})
    public ResponseEntity<Object> handleInvalidFormatException(final InvalidFormatException ex, final WebRequest request) {
        log.error("{},{}", ex.getClass().getName(), ex.getMessage());
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("Exception")).get("statusCode")), getTime(), request.getDescription(false), "InvalidFormat");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("Exception")).get("responseStatus")));
    }


    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<Object> handleNullPointerException(final NullPointerException ex, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        log.error("error", ex);
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("Exception")).get("statusCode")), getTime(), request.getDescription(false), "Object cannot be null");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("Exception")).get("responseStatus")));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        log.info("{},{}", ex.getClass().getName(), ex.getMessage());
        final ErrorMessage apiError = new ErrorMessage(Math.toIntExact((Long) ((JSONObject) getJsonObject().get("Exception")).get("statusCode")), getTime(), request.getDescription(false), "error occurred");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.valueOf((String) ((JSONObject) getJsonObject().get("Exception")).get("responseStatus")));
    }

}
















