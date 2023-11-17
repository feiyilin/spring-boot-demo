package com.xkcoding.mongodb.util;

public class BaseMongoEntity extends BaseEntity {
    private Long count;

    public Long getCount() {
        return this.count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public BaseMongoEntity() {
    }
}