package org.jtwig.cache.configuration;

import org.jtwig.cache.provider.PersistentCacheProvider;
import org.jtwig.cache.provider.RenderableCacheProvider;

import java.util.Collections;

public class DefaultCacheConfiguration extends CacheConfiguration {
    public DefaultCacheConfiguration() {
        super(Collections.<RenderableCacheProvider>singleton(new PersistentCacheProvider(1024)));
    }
}
