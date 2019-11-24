package com.stream.tweets.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Response skeleton class
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class BaseResponse {

    private int statusCode;
    private Object data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
