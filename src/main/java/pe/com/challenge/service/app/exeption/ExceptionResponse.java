package pe.com.challenge.service.app.exeption;

import java.util.Date;

public class ExceptionResponse {
    private Date timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ExceptionResponse(Date timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public Integer getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
