package org.jtwig.cache.integration;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.jtwig.cache.CacheExtension;
import org.jtwig.cache.configuration.CacheConfigurationBuilder;
import org.jtwig.cache.configuration.DefaultCacheConfiguration;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.jtwig.environment.EnvironmentConfigurationBuilder.configuration;

public class CacheTest {
    @Test
    public void persistentCache() throws Exception {
        String result = JtwigTemplate.inlineTemplate("{% cache persistent 'key' %}Hello{% endcache %}{% cache persistent 'key' %}{% endcache %}", configuration()
                .withExtension(new CacheExtension(new CacheConfigurationBuilder(new DefaultCacheConfiguration()).build()))
                .build()).render(JtwigModel.newModel());

        assertThat(result, is("HelloHello"));
    }
}
