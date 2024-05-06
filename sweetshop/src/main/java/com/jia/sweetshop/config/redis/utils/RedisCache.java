package com.jia.sweetshop.config.redis.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class RedisCache {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    /**
     * 缓存基本对象， Integer String 实体等
     * @param key
     * @param value
     * @param <T>
     */
    public <T> void setCacheObject(final String key,  final T value) {
        redisTemplate.opsForValue().set(key, value);
    }


    /**
     * 缓存基本对象， Integer String 实体等
     * @param key  缓存 键 key
     * @param value  缓存值 value
     * @param timeout 时间长度
     * @param timeUnit 时间粒度
     * @param <T>
     */
    public <T> void setCacheObject(final String key,  final T value,  final Integer timeout, final TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value,timeout,timeUnit);
    }

    /**
     * 给 key 设置有效时间
     * @param key 缓存 键 key
     * @param timeout 时间长度
     * @param timeUnit 时间粒度
     * @return true=设置成功  false=设置失败
     */
    public boolean expire(final String key, final Integer timeout, final TimeUnit timeUnit) {

        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, timeUnit));
    }

    /**
     * 获取缓存基本对象
     * @param key  缓存 键 key
     * @return
     * @param <T>
     */
    public <T> T getCacheObject(final String key) {
        ValueOperations<Object, Object> operations = redisTemplate.opsForValue();
        return (T) operations.get(key);
    }
    /**
     * 获取缓存基本对象
     * @param key  缓存 键 key
     * @return
     * @param
     */
    public Object getObjectFromRedis(final String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据key 删除 value
     * @param key
     * @return
     */
    public boolean delCacheObject(final String key) {
     return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

    /**
     * 根据key 删除 value
     * @param collection
     * @return
     */
    public long delCacheObject(final Collection collection) {
        return redisTemplate.delete(collection);
    }

    /**
     * 缓存 list 集合数据
     * @param key
     * @param list
     * @return
     * @param <T>
     */
    public <T> long setCacheList(final String key, final List<T> list) {
        Long count = redisTemplate.opsForList().rightPushAll(key, list);
        return count==null ?0:count;
    }

    /**
     * 获取缓存中list 对象
     * @param key
     * @return
     * @param <T>
     */
    public <T> List<T> getCacheList(final String key) {
      return (List<T>) redisTemplate.opsForList().range(key, 0, -1);

    }

    /**
     * 缓存 set
     * @param key
     * @return
     * @param <T>
     */
    public <T> BoundSetOperations<Object, T> setCacheSet(final String key,final Set<T> dataSet) {
        BoundSetOperations<Object, T> boundSetOps = (BoundSetOperations<Object, T>) redisTemplate.boundSetOps(key);
        Iterator<T> iterator = dataSet.iterator();
        while (iterator.hasNext()) {
            boundSetOps.add(iterator.next());
        }
        return boundSetOps;
    }

    /**
     * 获取 缓存的set
     * @param key
     * @return
     * @param <T>
     */
    public <T> Set<T> getCacheSet(final String key) {
       return (Set<T>) redisTemplate.opsForSet().members(key);
    }

    /**
     *  缓存 map 数据
     * @param key
     * @param dataMap
     * @param <T>
     */
    public <T>  void setCacheMap(final String key, final Map<String,T> dataMap) {
        if (dataMap != null) {
            redisTemplate.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获取map数据
     */
    public <T> Map<Object, Object> getCacheMap(final String key) {
        Map<Object, Object> map = redisTemplate.opsForHash().entries(key);
        return map;
    }

    /**
     * 往 Hash 中存入数据
     * @param key
     * @param hKey
     * @param value
     * @param <T>
     */
    public <T> void setCacheMapValue(final String key, final String hKey, final T value) {
        redisTemplate.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash 中值
     * @param key
     * @param hKey
     * @return
     * @param <T>
     */
    public <T> T getCacheMapValue(final String key, final String hKey) {
        HashOperations<Object, String, Object> forHash = redisTemplate.opsForHash();
        return (T) forHash.get(key,hKey);
    }

    /**
     * 删除hash 中值
     * @param key
     * @param hKey
     */
    public void delCacheMapValue(final String key, final String hKey) {
        HashOperations<Object, String, Object> forHash = redisTemplate.opsForHash();
        forHash.delete(key,hKey);
    }

    /**
     * 获取多个hash中的数据
     * @param key
     * @param hKeys
     * @return
     * @param <T>
     */
    public <T> List<T> getCacheMapValue(final String key,  final Collection<Object> hKeys) {
        return (List<T>) redisTemplate.opsForHash().multiGet(key, hKeys);
    }

}
