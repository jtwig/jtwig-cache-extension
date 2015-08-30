package org.jtwig.cache.configuration;

import org.apache.commons.lang3.builder.Builder;
import org.jtwig.cache.provider.CompositeRenderableCacheProvider;
import org.jtwig.cache.provider.PersistentCacheProvider;
import org.jtwig.cache.provider.RenderableCacheProvider;

import java.util.ArrayList;
import java.util.Collection;

public class CacheConfigurationBuilder implements Builder<CacheConfiguration> {
    public static CacheConfigurationBuilder defaultConfiguration () {
        return new CacheConfigurationBuilder().withRenderableCacheProvider(new PersistentCacheProvider(1024));
    }
    private final Collection<RenderableCacheProvider> renderableCacheProviders = new ArrayList<RenderableCacheProvider>();

    public CacheConfigurationBuilder() {}

    public CacheConfigurationBuilder withRenderableCacheProvider (RenderableCacheProvider provider) {
        renderableCacheProviders.add(provider);
        return this;
    }
    public CacheConfigurationBuilder withRenderableCacheProviders (Collection<RenderableCacheProvider> provider) {
        renderableCacheProviders.addAll(provider);
        return this;
    }

    @Override
    public CacheConfiguration build() {
        return new CacheConfiguration(new CompositeRenderableCacheProvider(renderableCacheProviders));
    }
}
