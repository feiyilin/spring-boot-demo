package com.xkcoding.helloworld.beikong.demo.open;

import java.util.ArrayList;
import java.util.List;

public class BatchOperationService<T> {

    private DataValidator<T> validator;
    private DataRepository<T> repository;

    public BatchOperationService(DataValidator<T> validator, DataRepository<T> repository) {
        this.validator = validator;
        this.repository = repository;
    }

    public BatchOperationResult<T> batchOperation(List<T> dataList) {
        BatchOperationResult<T> result = new BatchOperationResult<>();

        List<T> successList = new ArrayList<>();
        List<BatchOperationFailure<T>> failureList = new ArrayList<>();

        for (int i = 0; i < dataList.size(); i++) {
            T data = dataList.get(i);
            ValidationResult validationResult = validator.validate(data);

            if (validationResult.isValid()) {
                boolean isSuccess = repository.saveOrUpdate(data);
                if (isSuccess) {
                    successList.add(data);
                } else {
                    failureList.add(new BatchOperationFailure<>(i, data, "Save or update failed"));
                }
            } else {
                failureList.add(new BatchOperationFailure<>(i, data, validationResult.getErrorMessage()));
            }
        }

        result.setSuccessList(successList);
        result.setFailureList(failureList);

        return result;
    }
}