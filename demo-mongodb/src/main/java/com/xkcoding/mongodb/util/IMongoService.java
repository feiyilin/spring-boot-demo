//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xkcoding.mongodb.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.ProjectionOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.io.Serializable;
import java.util.List;

public interface IMongoService<T> {
    T getById(Serializable id);

    List<T> getList(Query query);

    T save(T entity);

    T insert(T entity);

    void updateById(Serializable id, T t);

    void insertAll(List<T> entityList);

    UpdateResult updateMulti(Query query, T t);

    UpdateResult updateMulti(Query query, Update update);

    boolean deleteById(Serializable id) throws Exception;

    boolean delete(Query query) throws Exception;

    PageRequest buildPageRequest(int pageNumber, int pageSize, Sort.Direction sortType, String... param);

    Page<T> pageList(Query query, Pageable pageable);

    <R extends BaseMongoEntity> List<R> getGroupList(Criteria criteria, ProjectionOperation projectionOperation, GroupOperation groupOperation, Class<R> voClass, Sort.Direction sortType, String... sortParam) throws Exception;

    <R extends BaseMongoEntity> Page<R> getGroupPage(Criteria criteria, ProjectionOperation projectionOperation, GroupOperation groupOperation, Class<R> voClass, Boolean isPaging, Integer pageNumber, Integer pageSize, Sort.Direction sortType, String... sortParam) throws Exception;

    T getOne(Query query);

    UpdateResult updateOne(Query query, T t);

    UpdateResult updateOne(Query query, Update t);

    Long count(Query query);
}
