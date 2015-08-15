package org.jtwig.cache.provider;

import com.google.common.base.Optional;

public interface RenderableCacheProvider {
    Optional<RenderableCache> cacheFor (String name);
}
