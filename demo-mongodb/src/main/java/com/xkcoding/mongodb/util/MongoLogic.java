package com.xkcoding.mongodb.util;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Documented
public @interface MongoLogic {
    String value() default "";

    String delval() default "";
}
