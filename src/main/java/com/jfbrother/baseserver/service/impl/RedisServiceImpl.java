//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.jfbrother.baseserver.service.impl;

import com.jfbrother.baseserver.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisServiceImpl() {
    }

    public boolean set(final String key, Object value) {
        boolean result = false;

        try {
            ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
            operations.set(key, value);
            result = true;
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return result;
    }

    public boolean set(final String key, Object value, Long expireTime) {
        boolean result = false;

        try {
            ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
            operations.set(key, value);
            this.redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
            result = true;
        } catch (Exception var6) {
            var6.printStackTrace();
        }

        return result;
    }

    public void remove(final String... keys) {
        String[] var2 = keys;
        int var3 = keys.length;

        for (int var4 = 0; var4 < var3; ++var4) {
            String key = var2[var4];
            this.remove(key);
        }

    }

    public void removePattern(final String pattern) {
        Set<Serializable> keys = this.redisTemplate.keys(pattern);
        if (keys.size() > 0) {
            this.redisTemplate.delete(keys);
        }

    }

    public void remove(final String key) {
        if (this.exists(key)) {
            this.redisTemplate.delete(key);
        }

    }

    public boolean exists(final String key) {
        return this.redisTemplate.hasKey(key);
    }

    public Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = this.redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    public void hmSet(String key, Object hashKey, Object value) {
        HashOperations<String, Object, Object> hash = this.redisTemplate.opsForHash();
        hash.put(key, hashKey, value);
    }

    public Object hmGet(String key, Object hashKey) {
        HashOperations<String, Object, Object> hash = this.redisTemplate.opsForHash();
        return hash.get(key, hashKey);
    }

    public void lPush(String k, Object v) {
        ListOperations<String, Object> list = this.redisTemplate.opsForList();
        list.leftPush(k, v);
    }

    public Object rPop(String k) {
        ListOperations<String, Object> list = this.redisTemplate.opsForList();
        return list.rightPop(k);
    }

    public Object rPop(String k, long timeout, TimeUnit timeUnit) {
        ListOperations<String, Object> list = this.redisTemplate.opsForList();
        return list.rightPop(k, timeout, timeUnit);
    }

    public List<Object> lRange(String k, long l, long l1) {
        ListOperations<String, Object> list = this.redisTemplate.opsForList();
        return list.range(k, l, l1);
    }

    public void add(String key, Object value) {
        SetOperations<String, Object> set = this.redisTemplate.opsForSet();
        set.add(key, new Object[]{value});
    }

    public Set<Object> setMembers(String key) {
        SetOperations<String, Object> set = this.redisTemplate.opsForSet();
        return set.members(key);
    }

    public void zAdd(String key, Object value, double scoure) {
        ZSetOperations<String, Object> zset = this.redisTemplate.opsForZSet();
        zset.add(key, value, scoure);
    }

    public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
        ZSetOperations<String, Object> zset = this.redisTemplate.opsForZSet();
        return zset.rangeByScore(key, scoure, scoure1);
    }

    public Long incr(String key) {
        return this.redisTemplate.getConnectionFactory().getConnection().incr(this.redisTemplate.getKeySerializer().serialize(key));
    }

    public Long getExpire(String key) {
        return this.redisTemplate.getExpire(key);
    }

    public Long getExpire(String key, TimeUnit unit) {
        return this.redisTemplate.getExpire(key, unit);
    }

    public void publish(String channel, Object message) {
        this.stringRedisTemplate.convertAndSend(channel, message);
    }

    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return this.redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Long sRem(String key, Object... members) {
        SetOperations<String, Object> set = this.redisTemplate.opsForSet();
        return set.remove(key, members);
    }

    @Override
    public Boolean sIsMember(String key, Object member) {
        SetOperations<String, Object> set = this.redisTemplate.opsForSet();
        return set.isMember(key, member);
    }

}
