package org.jtwig.cache.configuration;

import com.google.common.cache.CacheBuilder;
import org.apache.commons.lang3.builder.Builder;
import org.jtwig.cache.key.ToStringKeyGenerator;
import org.jtwig.cache.provider.CompositeRenderableCacheProvider;
import org.jtwig.cache.provider.NamedRenderableCacheProvider;
import org.jtwig.cache.provider.RenderableCache;
import org.jtwig.cache.provider.RenderableCacheProvider;
import org.jtwig.render.Renderable;

import java.util.ArrayList;
import java.util.Collection;

public class CacheConfigurationBuilder implements Builder<CacheConfiguration> {
    public static CacheConfigurationBuilder defaultConfiguration () {
        return new CacheConfigurationBuilder()
                .withRenderableCacheProvider(
                        new NamedRenderableCacheProvider("persistent", new RenderableCache(
                                CacheBuilder.newBuilder().maximumSize(Long.MAX_VALUE).<String, Renderable>build(),
                                new ToStringKeyGenerator())
                        )
                )
                ;
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
