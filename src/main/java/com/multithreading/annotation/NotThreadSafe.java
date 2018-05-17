package com.multithreading.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.CLASS;

@Documented
@Target(value = TYPE)
@Retention(value = CLASS)
public @interface NotThreadSafe {
}
