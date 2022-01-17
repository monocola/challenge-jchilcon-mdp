package pe.com.challenge.service.app.exeption;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorHttp {
    @JsonProperty("timestamp")
    String timestamp;
    @JsonProperty("status")
    String status;
    @JsonProperty("error")
    String error;
    @JsonProperty("message")
    String message;
    @JsonProperty("path")
    String path;
}
