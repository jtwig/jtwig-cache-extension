package org.jtwig.cache.configuration;

import org.jtwig.cache.provider.RenderableCacheProvider;
import org.jtwig.environment.Environment;
import org.jtwig.environment.EnvironmentConfigurationBuilder;

public class CacheConfiguration {
    private static final String CACHE_PROVIDER = "cacheProvider";
    public static RenderableCacheProvider cacheProvider (Environment environment) {
        return environment.parameter(CACHE_PROVIDER);
    }

    private final RenderableCacheProvider cacheProvider;

    public CacheConfiguration(RenderableCacheProvider cacheProvider) {
        this.cacheProvider = cacheProvider;
    }

    public void configure (EnvironmentConfigurationBuilder builder) {
        builder.withParameter(CACHE_PROVIDER, cacheProvider);
    }
}
