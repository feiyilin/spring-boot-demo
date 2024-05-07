package com.xkcoding.helloworld.beikong.demo.open;

import lombok.Data;

import java.util.List;

@Data
public class BatchOperationResult<T> {
    private List<T> successList;
    private List<BatchOperationFailure<T>> failureList;

    public List<T> getSuccessList() {
        return successList;
    }

    public void setSuccessList(List<T> successList) {
        this.successList = successList;
    }

    public List<BatchOperationFailure<T>> getFailureList() {
        return failureList;
    }

    public void setFailureList(List<BatchOperationFailure<T>> failureList) {
        this.failureList = failureList;
    }
}