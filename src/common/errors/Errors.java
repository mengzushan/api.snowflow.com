package common.errors;

public class Errors {
    private int httpCode;
    private int code;
    private String message;

    public Errors(int httpCode,int code,String message) {
        this.code = code;
        this.httpCode = httpCode;
        this.message = message;
    }

    public int getHttpCode() {
        return httpCode;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
