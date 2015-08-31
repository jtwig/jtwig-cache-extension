package org.jtwig.cache.configuration;

import org.apache.commons.lang3.builder.Builder;
import org.jtwig.cache.provider.RenderableCacheProvider;

import java.util.ArrayList;
import java.util.Collection;

public class CacheConfigurationBuilder implements Builder<CacheConfiguration> {
    private final Collection<RenderableCacheProvider> renderableCacheProviders = new ArrayList<RenderableCacheProvider>();

    public CacheConfigurationBuilder() {}

    public CacheConfigurationBuilder (CacheConfiguration prototype) {
        renderableCacheProviders.addAll(prototype.getCacheProviders());
    }

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
        return new CacheConfiguration(renderableCacheProviders);
    }
}
