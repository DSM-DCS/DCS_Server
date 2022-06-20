package com.dsm.dcs.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    PASSWORD_NOT_MATCH(400, "AUTH-400-1", "Password Not Match"),

    EXPIRED_JWT(401, "COMMON-401-1", "Expired Jwt"),
    INVALID_JWT(401, "COMMON-401-2", "Invalid Jwt"),
    EFFECTIVE_JWT(401, "COMMON-401-3", "Effective Access Token"),
    NOT_REFRESH_TOKEN(401, "COMMON-401-4", "Not Refresh Token"),
    UNAUTHORIZED_USER_AUTH_CODE(401, "USER-401-1", "Unauthorized User AuthCode"),
    INVALID_AUTH_CODE(401, "USER-401-2", "Invalid Auth Code"),
    UNVERIFIED_AUTH_CODE(401, "USER-401-3", "Unverified Auth Code"),

    FORBIDDEN(403, "COMMON-403-1", "Forbidden"),

    USER_NOT_FOUND(404, "USER-404-1", "User Not Found"),
    USER_AUTH_CODE_NOT_FOUND(404, "USER-404-2", "User AuthCode Not Found"),
    ADMIN_NOT_FOUND(404, "ADMIN-404-1", "Admin Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404, "AUTH-404-1", "Refresh Token Not Found"),
    DELIVERY_NOT_FOUND(404, "DELIVERY-404-1", "Delivery Not Found"),
    POST_NOT_FOUND(404, "POST-404-1", "Post Not Found"),

    USER_EXISTS(409, "USER-409-1", "User Exists"),
    ACCOUNT_ID_EXISTS(409, "USER-409-2", "AccountId Exists"),
    PHONE_NUMBER_EXISTS(409, "USER-409-3", "PhoneNumber Exists"),
    STUDENT_NUMBER_EXISTS(409, "USER-409-4", "StudentNumber Exists"),
    USER_AUTH_CODE_ALREADY_VERIFIED(409, "USER-409-5", "AuthCode already exists"),
    EMAIL_EXISTS(409, "USER_409-6", "Email Exists"),

    AUTH_CODE_REQUEST_OVER_LIMIT(429, "USER_429-1", "Auth Code Request Over Limit"),

    INTERNAL_SERVER_ERROR(500, "SERVER-500-1", "Internal Server Error");

    private final int status;
    private final String code;
    private final String message;
}
