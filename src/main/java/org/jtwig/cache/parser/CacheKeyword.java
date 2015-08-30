package org.jtwig.cache.parser;

import com.google.common.base.Function;
import com.google.common.collect.Collections2;

import java.util.Collection;

import static java.util.Arrays.asList;

public enum CacheKeyword {
    CACHE("cache"),
    END_CACHE("endcache")
    ;
    public static Collection<String> all () {
        return Collections2.transform(asList(values()), new Function<CacheKeyword, String>() {
            @Override
            public String apply(CacheKeyword input) {
                return input.token;
            }
        });
    }

    private final String token;

    CacheKeyword(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return token;
    }
}
