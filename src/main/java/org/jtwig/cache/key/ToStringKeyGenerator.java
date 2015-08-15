package org.jtwig.cache.key;

public class ToStringKeyGenerator implements KeyGenerator {
    @Override
    public String generateKey(Object value) {
        if (value == null) {
            return "";
        } else {
            return value.toString();
        }
    }
}
