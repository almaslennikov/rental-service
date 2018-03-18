package com.nntu.containers.responses;

import com.nntu.containers.info.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorizationResponse extends Response {
    private UserInfo userInfo;

    public AuthorizationResponse(RequestStatus status) {
        super(status);
    }

    public AuthorizationResponse(RequestStatus status, UserInfo userInfo) {
        super(status);
        this.userInfo = userInfo;
    }
}
