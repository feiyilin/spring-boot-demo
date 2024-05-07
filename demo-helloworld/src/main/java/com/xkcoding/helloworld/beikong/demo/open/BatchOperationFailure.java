package com.xkcoding.helloworld.beikong.demo.open;

import lombok.Data;

@Data
public class BatchOperationFailure<T> {
    private int index;
    private T data;
    private String reason;

    public BatchOperationFailure(int index, T data, String reason) {
        this.index = index;
        this.data = data;
        this.reason = reason;
    }

    // Getters and setters
}