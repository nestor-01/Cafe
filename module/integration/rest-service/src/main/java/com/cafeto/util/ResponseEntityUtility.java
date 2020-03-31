package com.cafeto.util;


import com.cafeto.core.api.events.ResponseCode;
import com.cafeto.core.api.events.ResponseEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public final class ResponseEntityUtility {

    public static <T> ResponseEntity<ResponseEvent<T>> buildHttpResponse(ResponseEvent<T> responseEvent) {
        log.debug("method: buildHttpResponse({})", responseEvent);
        final HttpStatus httpStatus = parseResponseCode(responseEvent.getCode());
        return new ResponseEntity<>(responseEvent, httpStatus);
    }

    public static HttpStatus parseResponseCode(final ResponseCode code) {
        log.debug("method: parseResponseCode({})", code);
        switch (code) {
            case OK:
                return HttpStatus.OK;

            case BAD_REQUEST:
                return HttpStatus.BAD_REQUEST;

            case CONFLICT:
                return HttpStatus.CONFLICT;

            case FORBIDDEN:
                return HttpStatus.FORBIDDEN;

            case NOT_FOUND:
                return HttpStatus.NOT_FOUND;

            case NO_CONTENT:
                return HttpStatus.NO_CONTENT;

            case NOT_IMPLEMENTED:
                return HttpStatus.NOT_IMPLEMENTED;

            case UNAUTHORIZED:
                return HttpStatus.UNAUTHORIZED;

            case APPLICATION_ERROR:
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }
}
