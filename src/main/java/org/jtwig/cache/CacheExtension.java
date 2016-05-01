package org.jtwig.cache;


import org.jtwig.cache.configuration.CacheConfiguration;
import org.jtwig.cache.node.CacheNode;
import org.jtwig.cache.parser.CacheAddonParser;
import org.jtwig.cache.parser.CacheKeyword;
import org.jtwig.cache.render.node.CacheNodeRender;
import org.jtwig.environment.EnvironmentConfigurationBuilder;
import org.jtwig.extension.Extension;
import org.jtwig.parser.addon.AddonParserProvider;
import org.jtwig.parser.parboiled.node.AddonParser;

import java.util.Collection;

public class CacheExtension implements Extension {
    private final CacheConfiguration configuration;

    public CacheExtension(CacheConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void configure(EnvironmentConfigurationBuilder environmentConfigurationBuilder) {
        configuration.configure(environmentConfigurationBuilder);
        environmentConfigurationBuilder.parser().addonParserProviders().add(new AddonParserProvider() {
            @Override
            public Class<? extends AddonParser> parser() {
                return CacheAddonParser.class;
            }

            @Override
            public Collection<String> keywords() {
                return CacheKeyword.all();
            }
        }).and().and().render().nodeRenders().add(CacheNode.class, new CacheNodeRender());
    }
}
