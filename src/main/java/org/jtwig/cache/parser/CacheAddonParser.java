package org.jtwig.cache.parser;

import org.jtwig.cache.node.CacheNode;
import org.jtwig.model.tree.CompositeNode;
import org.jtwig.parser.parboiled.ParserContext;
import org.jtwig.parser.parboiled.base.LexicParser;
import org.jtwig.parser.parboiled.base.LimitsParser;
import org.jtwig.parser.parboiled.base.PositionTrackerParser;
import org.jtwig.parser.parboiled.base.SpacingParser;
import org.jtwig.parser.parboiled.expression.AnyExpressionParser;
import org.jtwig.parser.parboiled.expression.VariableExpressionParser;
import org.jtwig.parser.parboiled.node.AddonParser;
import org.jtwig.parser.parboiled.node.CompositeNodeParser;
import org.parboiled.Rule;
import org.parboiled.annotations.Label;

public class CacheAddonParser extends AddonParser {
    public CacheAddonParser(ParserContext context) {
        super(CacheAddonParser.class, context);
    }

    @Override
    @Label("Cache")
    public Rule NodeRule() {
        LimitsParser limitsParser = parserContext().parser(LimitsParser.class);
        PositionTrackerParser positionTrackerParser = parserContext().parser(PositionTrackerParser.class);
        SpacingParser spacingParser = parserContext().parser(SpacingParser.class);
        LexicParser lexicParser = parserContext().parser(LexicParser.class);
        VariableExpressionParser variableExpressionParser = parserContext().parser(VariableExpressionParser.class);
        AnyExpressionParser anyExpressionParser = parserContext().parser(AnyExpressionParser.class);
        CompositeNodeParser nodeParser = parserContext().parser(CompositeNodeParser.class);
        return Sequence(
                positionTrackerParser.PushPosition(),

                Sequence( // Start
                        limitsParser.startCode(),
                        spacingParser.Spacing(),
                        lexicParser.Keyword(CacheKeyword.CACHE.toString()),
                        spacingParser.Spacing(),
                        variableExpressionParser.ExpressionRule(),
                        spacingParser.Spacing(),
                        anyExpressionParser.ExpressionRule(),
                        spacingParser.Spacing(),
                        limitsParser.endCode()
                ),

                nodeParser.NodeRule(),

                Sequence( // Stop
                        limitsParser.startCode(),
                        spacingParser.Spacing(),
                        lexicParser.Keyword(CacheKeyword.END_CACHE.toString()),
                        spacingParser.Spacing(),
                        limitsParser.endCode()
                ),

                push(new CacheNode(positionTrackerParser.pop(3), nodeParser.pop(), variableExpressionParser.pop(1).getIdentifier(), anyExpressionParser.pop()))
        );
    }
}
