package com.nntu.containers.responses;

import lombok.Data;

@Data
public class Response {
    private RequestStatus status;

    public Response(RequestStatus status) {
        this.status = status;
    }
}
