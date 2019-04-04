package com.coder.dagger_java.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * @Qualifier annotation is provided by javax inject package and is used to qualify the dependency.
 * For example, a class can ask both, an Application Context and an Activity Context. But both these Objects will
 * be of type Context. So, for Dagger to figure out which variable is to be provided with what,
 * we have to explicitly specify the identifier for it.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME) //why is there a need to mention this
public @interface ActivityContext {
}
