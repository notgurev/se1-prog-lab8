package se1_prog_lab.server.api;

import se1_prog_lab.util.AuthStrings;

import java.io.Serializable;

public class Response implements Serializable {
    private final ResponseType RESPONSE_TYPE;
    private final Object message;
    private final boolean isRejected;

    public Response(ResponseType responseType, Object message) {
        this.RESPONSE_TYPE = responseType;
        this.message = message;
        isRejected = false;
    }

    public Response(ResponseType responseType, Object message, boolean isRejected) {
        this.RESPONSE_TYPE = responseType;
        this.message = message;
        this.isRejected = isRejected;
    }

    public ResponseType getResponseType() {
        return RESPONSE_TYPE;
    }

    public Object getMessage() {
        return message;
    }

    public String getStringMessage() {
        switch (RESPONSE_TYPE) {
            case AUTH_STATUS:
                return ((AuthStrings) message).getMessage();
            case PLAIN_TEXT:
                return message.toString();
            default:
                return "";
        }
    }

    public boolean isRejected() {
        return isRejected;
    }
}