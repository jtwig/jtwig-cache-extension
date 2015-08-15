package org.jtwig.cache.provider;

import com.google.common.cache.Cache;
import org.jtwig.cache.key.KeyGenerator;
import org.jtwig.render.Renderable;

public class RenderableCache {
    private final Cache<String, Renderable> cache;
    private final KeyGenerator keyGenerator;

    public RenderableCache(Cache<String, Renderable> cache, KeyGenerator keyGenerator) {
        this.cache = cache;
        this.keyGenerator = keyGenerator;
    }

    public Cache<String, Renderable> getCache() {
        return cache;
    }

    public KeyGenerator getKeyGenerator() {
        return keyGenerator;
    }
}
