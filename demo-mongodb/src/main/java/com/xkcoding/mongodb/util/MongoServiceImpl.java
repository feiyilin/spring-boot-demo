//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.xkcoding.mongodb.util;

import com.alibaba.fastjson.JSONObject ;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class MongoServiceImpl<T> implements IMongoService<T> {
    @Autowired
    private MongoTemplate mongoTemplate;
    private Class<T> classes = null;

    public MongoServiceImpl() {
        this.classes = this.currentModelClass();
    }

    protected Class<T> currentModelClass() {
        return ReflectionKit.getSuperClassGenericType(this.getClass(), 0);
    }

    public Class<T> getEntityClass() {
        return this.classes;
    }

    @Override
    public T save(T entity) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            String name = mongoLogicField.getName();
            Integer value = this.getMongoLogicValue();
            StringBuilder sb = new StringBuilder();
            sb.append("set");
            sb.append(name.substring(0, 1).toUpperCase(Locale.ROOT));
            sb.append(name.substring(1));

            try {
                this.classes.getMethod(sb.toString(), mongoLogicField.getType()).invoke(entity, value);
            } catch (IllegalAccessException var7) {
                var7.printStackTrace();
            } catch (InvocationTargetException var8) {
                var8.printStackTrace();
            } catch (NoSuchMethodException var9) {
                var9.printStackTrace();
            }
        }

        return this.mongoTemplate.save(entity);
    }

    @Override
    public T insert(T entity) {
        return this.mongoTemplate.insert(entity);
    }

    @Override
    public void insertAll(List<T> entityList) {
        this.mongoTemplate.insertAll(entityList);
    }

    @Override
    public T getById(Serializable id) {
        return this.mongoTemplate.findById(id, this.classes);
    }

    @Override
    public void updateById(Serializable id, T t) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update notNullUpdate = this.getNotNullUpdate(t);
        this.mongoTemplate.updateFirst(query, notNullUpdate, this.classes);
    }

    @Override
    public UpdateResult updateMulti(Query query, T t) {
        Update notNullUpdate = this.getNotNullUpdate(t);
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.updateMulti(query, notNullUpdate, this.classes);
    }

    @Override
    public UpdateResult updateMulti(Query query, Update update) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.updateMulti(query, update, this.classes);
    }

    @Override
    public List<T> getList(Query query) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.find(query, this.classes);
    }

    @Override
    public boolean deleteById(Serializable id) throws Exception {
        Field mongoLogicField = this.getMongoLogicField();
        Integer mongoLogicDelValue = this.getMongoLogicDelValue();
        Query query = new Query(Criteria.where("_id").is(id));
        if (mongoLogicField != null && mongoLogicDelValue != null) {
            Update update = new Update();
            update.set(mongoLogicField.getName(), mongoLogicDelValue);
            UpdateResult updateResult = this.mongoTemplate.updateFirst(query, update, this.classes);
            long modifiedCount = updateResult.getModifiedCount();
            return modifiedCount > 0L;
        } else {
            DeleteResult remove = this.mongoTemplate.remove(query);
            long deletedCount = remove.getDeletedCount();
            return deletedCount > 0L;
        }
    }

    @Override
    public boolean delete(Query query) throws Exception {
        Field mongoLogicField = this.getMongoLogicField();
        Integer mongoLogicDelValue = this.getMongoLogicDelValue();
        if (mongoLogicField != null && mongoLogicDelValue != null) {
            Update update = new Update();
            update.set(mongoLogicField.getName(), mongoLogicDelValue);
            UpdateResult updateResult = this.mongoTemplate.updateFirst(query, update, this.classes);
            long modifiedCount = updateResult.getModifiedCount();
            return modifiedCount > 0L;
        } else {
            DeleteResult remove = this.mongoTemplate.remove(query);
            long deletedCount = remove.getDeletedCount();
            return deletedCount > 0L;
        }
    }

    private boolean isMongoLogicPresent() {
        Field[] declaredFields = this.classes.getDeclaredFields();
        Field[] var2 = declaredFields;
        int var3 = declaredFields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.isAnnotationPresent(TableLogic.class)) {
                return true;
            }
        }

        return false;
    }

    private Field getMongoLogicField() {
        Field[] declaredFields = this.classes.getDeclaredFields();
        Field[] var2 = declaredFields;
        int var3 = declaredFields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.isAnnotationPresent(TableLogic.class)) {
                return field;
            }
        }

        return null;
    }

    private Update getNotNullUpdate(T t) {
        Class<?> aClass = t.getClass();
        Field[] fields = t.getClass().getDeclaredFields();
        Update update = new Update();
        int count = 0;
        Field[] var6 = fields;
        int var7 = fields.length;

        for(int var8 = 0; var8 < var7; ++var8) {
            Field field = var6[var8];
            Object field1 = null;
            ReflectionUtils.makeAccessible(field);
            field1 = ReflectionUtils.getField(field, t);
            if (field1 != null) {
                update.set(field.getName(), field1);
                ++count;
            }
        }

        return count == 0 ? null : update;
    }

    private Integer getMongoLogicValue() {
        Field[] declaredFields = this.classes.getDeclaredFields();
        Field[] var2 = declaredFields;
        int var3 = declaredFields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.isAnnotationPresent(TableLogic.class)) {
                TableLogic declaredAnnotation = (TableLogic)field.getDeclaredAnnotation(TableLogic.class);
                return declaredAnnotation.value() == null ? Integer.parseInt(YamlUtil.getValue("mybatis-plus.global-config.db-config.logic-not-delete-value")) : Integer.parseInt(declaredAnnotation.value());
            }
        }

        return null;
    }

    private Integer getMongoLogicDelValue() {
        Field[] declaredFields = this.classes.getDeclaredFields();
        Field[] var2 = declaredFields;
        int var3 = declaredFields.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Field field = var2[var4];
            if (field.isAnnotationPresent(TableLogic.class)) {
                TableLogic declaredAnnotation = (TableLogic)field.getDeclaredAnnotation(TableLogic.class);
                return declaredAnnotation.delval() == null ? Integer.parseInt(YamlUtil.getValue("mybatis-plus.global-config.db-config.logic-delete-value")) : Integer.parseInt(declaredAnnotation.delval());
            }
        }

        return null;
    }

    @Override
    public PageRequest buildPageRequest(int pageNumber, int pageSize, Sort.Direction sortType, String... param) {
        if (pageNumber <= 0) {
            pageNumber = 1;
        }

        --pageNumber;
        if (pageSize <= 0) {
            pageSize = 50;
        }

        Sort order = Sort.by(sortType, param);
        return PageRequest.of(pageNumber, pageSize, order);
    }

    @Override
    public Page<T> pageList(Query query, Pageable pageable) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        long total = this.mongoTemplate.count(query, this.classes);
        query.with(pageable);
        List<T> ts = this.mongoTemplate.find(query, this.classes);
        org.springframework.data.domain.Page<T> all = new PageImpl(ts, pageable, total);
        List<T> content = all.getContent();
        Page<T> page = new Page((long)(pageable.getPageNumber() + 1), (long)pageable.getPageSize(), all.getTotalElements());
        page.setRecords(content);
        return page;
    }

    @Override
    public <R extends BaseMongoEntity> List<R> getGroupList(Criteria criteria, ProjectionOperation projectionOperation, GroupOperation groupOperation, Class<R> voClass, Sort.Direction sortType, String... sortParam) throws Exception {
        Page<R> page = this.getGroupPage(criteria, projectionOperation, groupOperation, voClass, false, 0, 0, sortType, sortParam);
        return page.getRecords();
    }

    @Override
    public <R extends BaseMongoEntity> Page<R> getGroupPage(Criteria criteria, ProjectionOperation projectionOperation, GroupOperation groupOperation, Class<R> voClass, Boolean isPaging, Integer pageNumber, Integer pageSize, Sort.Direction sortType, String... sortParam) throws Exception {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            criteria.and(mongoLogicField.getName()).is(value);
        }

        long total = 0L;
        if (pageNumber <= 0) {
            pageNumber = 1;
        }

        pageNumber = pageNumber - 1;
        if (pageSize <= 0) {
            pageSize = 50;
        }

        MatchOperation match = Aggregation.match(criteria);
        CountOperation countOperation = new CountOperation("count");
        ArrayList list;
        Aggregation agg;
        if (isPaging) {
            list = new ArrayList();
            list.add(match);
            if (projectionOperation != null) {
                list.add(projectionOperation);
            }

            if (groupOperation != null) {
                list.add(groupOperation);
            }

            list.add(countOperation);
            agg = Aggregation.newAggregation(list);
            R uniqueMappedResult = (BaseMongoEntity)this.mongoTemplate.aggregate(agg, this.classes, voClass).getUniqueMappedResult();
            if (uniqueMappedResult == null) {
                total = 0L;
            } else {
                total = uniqueMappedResult.getCount();
            }
        }

        list = new ArrayList();
        list.add(match);
        if (projectionOperation != null) {
            list.add(projectionOperation);
        }

        if (groupOperation != null) {
            list.add(groupOperation);
        }

        if (sortType != null && sortParam != null && sortParam.length > 0) {
            Sort sort = Sort.by(sortType, sortParam);
            list.add(Aggregation.sort(sort));
        }

        if (isPaging) {
            SkipOperation skip = Aggregation.skip((long)pageNumber * (long)pageSize);
            list.add(skip);
            LimitOperation limit = Aggregation.limit((long)pageSize);
            list.add(limit);
        }

        agg = Aggregation.newAggregation(list);
        Page<R> page = new Page((long)(pageNumber + 1), (long)pageSize, total);
        if (groupOperation != null) {
            Document rawResults = this.mongoTemplate.aggregate(agg, this.classes, Object.class).getRawResults();
            List<Document> result = rawResults.getList("results", Document.class);
            List<R> rList = (List)result.stream().map((v) -> {
                v.remove("_id");
                JSONObject jsonObject = new JSONObject();
                jsonObject.putAll(v);
                return (BaseMongoEntity)jsonObject.toJavaObject(voClass);
            }).collect(Collectors.toList());
            page.setRecords(rList);
        } else {
            List<R> mappedResultList = this.mongoTemplate.aggregate(agg, this.classes, voClass).getMappedResults();
            page.setRecords(mappedResultList);
        }

        return page;
    }

    @Override
    public T getOne(Query query) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.findOne(query, this.classes);
    }

    @Override
    public UpdateResult updateOne(Query query, T t) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        Update notNullUpdate = this.getNotNullUpdate(t);
        return this.mongoTemplate.updateFirst(query, notNullUpdate, this.classes);
    }

    @Override
    public UpdateResult updateOne(Query query, Update update) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.updateFirst(query, update, this.classes);
    }

    @Override
    public Long count(Query query) {
        if (this.isMongoLogicPresent()) {
            Field mongoLogicField = this.getMongoLogicField();
            Integer value = this.getMongoLogicValue();
            query.addCriteria(Criteria.where(mongoLogicField.getName()).is(value));
        }

        return this.mongoTemplate.count(query, this.classes);
    }
}
