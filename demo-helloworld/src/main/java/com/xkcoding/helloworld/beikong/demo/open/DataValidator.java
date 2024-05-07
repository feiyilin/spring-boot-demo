package com.xkcoding.helloworld.beikong.demo.open;

interface DataValidator<T> {
    ValidationResult validate(T data);
}