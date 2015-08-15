package org.jtwig.cache.node;

import org.jtwig.cache.configuration.CacheConfiguration;
import org.jtwig.cache.provider.RenderableCache;
import org.jtwig.context.RenderContext;
import org.jtwig.model.expression.Expression;
import org.jtwig.model.position.Position;
import org.jtwig.model.tree.ContentNode;
import org.jtwig.model.tree.Node;
import org.jtwig.render.RenderException;
import org.jtwig.render.Renderable;
import org.jtwig.util.OptionalUtils;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CacheNode extends ContentNode {
    private final String cacheType;
    private final Expression cacheParameter;

    public CacheNode(Position position, Node content, String cacheType, Expression cacheParameter) {
        super(position, content);
        this.cacheType = cacheType;
        this.cacheParameter = cacheParameter;
    }

    @Override
    public Renderable render(final RenderContext context) {
        RenderableCache renderableCache = CacheConfiguration.cacheProvider(context.environment()).cacheFor(cacheType)
                .or(OptionalUtils.<RenderableCache>throwException(String.format("Unable to get cache provider of type '%s'", cacheType)));
        String key = renderableCache.getKeyGenerator().generateKey(cacheParameter.calculate(context).asObject());
        try {
            return renderableCache.getCache().get(key, new Callable<Renderable>() {
                @Override
                public Renderable call() throws Exception {
                    return CacheNode.super.render(context);
                }
            });
        } catch (ExecutionException e) {
            if (e.getCause() instanceof RuntimeException) {
                throw (RuntimeException) e.getCause();
            } else {
                throw new RenderException(e.getCause());
            }
        }
    }
}
