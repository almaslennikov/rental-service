package com.nntu.containers.responses;

import com.nntu.containers.info.UserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends Response {
    private UserInfo userInfo;

    public UserResponse(RequestStatus status) {
        super(status);
    }

    public UserResponse(RequestStatus status, UserInfo userInfo) {
        super(status);
        this.userInfo = userInfo;
    }
}
