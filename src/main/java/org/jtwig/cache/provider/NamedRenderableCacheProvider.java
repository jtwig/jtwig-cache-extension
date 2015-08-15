package org.jtwig.cache.provider;

import com.google.common.base.Optional;

public class NamedRenderableCacheProvider implements RenderableCacheProvider {
    private final String name;
    private final RenderableCache renderableCache;

    public NamedRenderableCacheProvider(String name, RenderableCache renderableCache) {
        this.name = name;
        this.renderableCache = renderableCache;
    }

    @Override
    public Optional<RenderableCache> cacheFor(String name) {
        if (this.name.equals(name)) {
            return Optional.of(renderableCache);
        }
        return Optional.absent();
    }
}
