package org.jtwig.cache.provider;

import com.google.common.base.Optional;

import java.util.Collection;

public class CompositeRenderableCacheProvider implements RenderableCacheProvider {
    private final Collection<RenderableCacheProvider> cacheProviders;

    public CompositeRenderableCacheProvider(Collection<RenderableCacheProvider> cacheProviders) {
        this.cacheProviders = cacheProviders;
    }

    @Override
    public Optional<RenderableCache> cacheFor(String name) {
        for (RenderableCacheProvider cacheProvider : cacheProviders) {
            Optional<RenderableCache> optional = cacheProvider.cacheFor(name);
            if (optional.isPresent()) {
                return optional;
            }
        }
        return Optional.absent();
    }
}
