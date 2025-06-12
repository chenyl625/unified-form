//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jfbrother.baseserver.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface RedisService {
    boolean set(final String key, Object value);

    boolean set(final String key, Object value, Long expireTime);

    void remove(final String... keys);

    void removePattern(final String pattern);

    void remove(final String key);

    boolean exists(final String key);

    Object get(final String key);

    void hmSet(String key, Object hashKey, Object value);

    Object hmGet(String key, Object hashKey);

    void lPush(String k, Object v);

    Object rPop(String k);

    Object rPop(String k, long timeout, TimeUnit timeUnit);

    List<Object> lRange(String k, long l, long l1);

    void add(String key, Object value);

    Set<Object> setMembers(String key);

    void zAdd(String key, Object value, double scoure);

    Set<Object> rangeByScore(String key, double scoure, double scoure1);

    Long incr(String key);

    Long getExpire(String key);

    Long getExpire(String key, TimeUnit unit);

    void publish(String channel, Object message);

    Boolean expire(String key, long timeout, TimeUnit unit);

    Long sRem(String key, Object... members);

    Boolean sIsMember(String key, Object member);
}
