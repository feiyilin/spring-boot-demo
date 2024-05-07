package com.xkcoding.helloworld.beikong.demo.open;

interface DataRepository<T> {
    boolean saveOrUpdate(T data);
}