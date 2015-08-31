package org.jtwig.cache.configuration;

import org.jtwig.cache.provider.CompositeRenderableCacheProvider;
import org.jtwig.cache.provider.RenderableCacheProvider;
import org.jtwig.environment.Environment;
import org.jtwig.environment.EnvironmentConfigurationBuilder;

import java.util.Collection;

public class CacheConfiguration {
    private static final String CACHE_PROVIDER = "cacheProvider";
    public static RenderableCacheProvider cacheProvider (Environment environment) {
        return environment.parameter(CACHE_PROVIDER);
    }

    private final Collection<RenderableCacheProvider> cacheProviders;

    public CacheConfiguration(Collection<RenderableCacheProvider> cacheProviders) {
        this.cacheProviders = cacheProviders;
    }

    public void configure (EnvironmentConfigurationBuilder builder) {
        builder.withParameter(CACHE_PROVIDER, new CompositeRenderableCacheProvider(cacheProviders));
    }

    public Collection<RenderableCacheProvider> getCacheProviders() {
        return cacheProviders;
    }
}
