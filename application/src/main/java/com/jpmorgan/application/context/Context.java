package com.jpmorgan.application.context;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Context is responsible for dependencies (DI)
 */
public final class Context {
    private static final Injector INJECTOR = Guice.createInjector(new Binding());

    public static Injector getInjector() {
        return INJECTOR;
    }
}
