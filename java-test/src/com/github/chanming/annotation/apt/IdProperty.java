package com.github.chanming.annotation.apt;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: 
 * Create Date:2016年4月10日
 * @author XuMaoSen
 * Version:1.0.0
 */
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.SOURCE)
public @interface IdProperty
{
    String column();

    String type();

    String generator();
}
