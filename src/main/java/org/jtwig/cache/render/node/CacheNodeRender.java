package org.jtwig.cache.render.node;

import com.google.common.base.Optional;
import org.jtwig.cache.configuration.CacheConfiguration;
import org.jtwig.cache.node.CacheNode;
import org.jtwig.cache.provider.RenderableCache;
import org.jtwig.render.RenderRequest;
import org.jtwig.render.node.renderer.NodeRender;
import org.jtwig.renderable.RenderException;
import org.jtwig.renderable.Renderable;
import org.jtwig.util.ErrorMessageFormatter;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class CacheNodeRender implements NodeRender<CacheNode> {
    @Override
    public Renderable render(final RenderRequest renderRequest, final CacheNode node) {
        Optional<RenderableCache> renderableCache = CacheConfiguration.cacheProvider(renderRequest.getEnvironment()).cacheFor(node.getCacheType());
        if (renderableCache.isPresent()) {
            Object calculate = renderRequest.getEnvironment().getRenderEnvironment().getCalculateExpressionService().calculate(renderRequest, node.getCacheParameter());
            String key = renderableCache.get().getKeyGenerator().generateKey(calculate);
            try {
                return renderableCache.get().getCache().get(key, new Callable<Renderable>() {
                    @Override
                    public Renderable call() throws Exception {
                        return renderRequest.getEnvironment().getRenderEnvironment().getRenderNodeService().render(renderRequest, node.getContent());
                    }
                });
            } catch (ExecutionException e) {
                if (e.getCause() instanceof RuntimeException) {
                    throw (RuntimeException) e.getCause();
                } else {
                    throw new RenderException(e.getCause());
                }
            }
        } else {
            throw new RenderException(ErrorMessageFormatter.errorMessage(node.getPosition(), String.format("Unable to get cache provider of type '%s'", node.getCacheType())));
        }
    }
}
