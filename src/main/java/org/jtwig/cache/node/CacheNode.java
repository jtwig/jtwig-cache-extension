package org.jtwig.cache.node;

import org.jtwig.model.expression.Expression;
import org.jtwig.model.position.Position;
import org.jtwig.model.tree.ContentNode;
import org.jtwig.model.tree.Node;

public class CacheNode extends ContentNode {
    private final String cacheType;
    private final Expression cacheParameter;

    public CacheNode(Position position, Node content, String cacheType, Expression cacheParameter) {
        super(position, content);
        this.cacheType = cacheType;
        this.cacheParameter = cacheParameter;
    }

    public String getCacheType() {
        return cacheType;
    }

    public Expression getCacheParameter() {
        return cacheParameter;
    }
}
