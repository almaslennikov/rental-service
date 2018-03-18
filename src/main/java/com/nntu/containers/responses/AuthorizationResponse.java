package com.nntu.containers.responses;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AuthorizationResponse extends Response {
    private Long id;

    public AuthorizationResponse(RequestStatus status, Long id) {
        super(status);
        this.id = id;
    }
}
