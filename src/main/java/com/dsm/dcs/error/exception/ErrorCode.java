package com.dsm.dcs.error.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // 400
    PASSWORD_NOT_MATCH(400, "Password Not Match"),

    // 401
    EXPIRED_JWT(401,  "Expired Jwt"),
    INVALID_JWT(401,  "Invalid Jwt"),
    EFFECTIVE_JWT(401, "Effective Access Token"),
    NOT_REFRESH_TOKEN(401,  "Not Refresh Token"),

    // 403
    FORBIDDEN(403,  "Forbidden"),

    // 404
    USER_NOT_FOUND(404,  "User Not Found"),
    ADMIN_NOT_FOUND(404,  "Admin Not Found"),
    REFRESH_TOKEN_NOT_FOUND(404,  "Refresh Token Not Found"),
    DELIVERY_NOT_FOUND(404,  "Delivery Not Found"),
    POST_NOT_FOUND(404,  "Post Not Found"),
    DEVICE_TOKEN_NOT_FOUND(404,  "Device Token Not Found"),

    // 409
    USER_EXISTS(409,  "User Exists"),
    ACCOUNT_ID_EXISTS(409,  "AccountId Exists"),
    PHONE_NUMBER_EXISTS(409,  "PhoneNumber Exists"),
    USER_AUTH_CODE_ALREADY_VERIFIED(409,  "Auth Code already exists"),
    FIREBASE_EXCEPTION(409,  "알림을 성공적으로 전송하지 못함"),

    // 429
    AUTH_CODE_REQUEST_OVER_LIMIT(429,  "Auth Code Request Over Limit"),

    // 500
    INTERNAL_SERVER_ERROR(500,  "Internal Server Error");

    private final int status;
    private final String message;
}
