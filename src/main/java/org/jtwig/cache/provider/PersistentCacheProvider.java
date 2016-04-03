package org.jtwig.cache.provider;

import com.google.common.cache.CacheBuilder;
import org.jtwig.cache.key.ToStringKeyGenerator;
import org.jtwig.renderable.Renderable;

public class PersistentCacheProvider extends NamedRenderableCacheProvider {
    public PersistentCacheProvider(int cacheSize) {
        super("persistent", new RenderableCache(CacheBuilder.newBuilder()
                .maximumSize(cacheSize)
                .<String, Renderable>build(), new ToStringKeyGenerator()));
    }
}
