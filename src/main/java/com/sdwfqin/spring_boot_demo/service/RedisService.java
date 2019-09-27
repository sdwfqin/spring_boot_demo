package com.sdwfqin.spring_boot_demo.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Redis 缓存服务
 * <p>
 *
 * @author 张钦
 * @date 2019/9/9
 */
@Repository
public class RedisService {

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public void addObject(String key, Object obj, Long time) {
        Gson gson = new Gson();
        stringRedisTemplate.opsForValue().set(key, gson.toJson(obj), time, TimeUnit.MILLISECONDS);
    }

    public void addObject(String key, Object obj) {
        Gson gson = new Gson();
        stringRedisTemplate.opsForValue().set(key, gson.toJson(obj));
    }

    public void addObjectList(String key, List<Object> objs, Long time) {
        Gson gson = new Gson();
        String src = gson.toJson(objs);
        stringRedisTemplate.opsForValue().set(key, src, time, TimeUnit.MILLISECONDS);
    }

    public void addObjectList(String key, List<Object> objs) {
        Gson gson = new Gson();
        String src = gson.toJson(objs);
        stringRedisTemplate.opsForValue().set(key, src);
    }

    public void addString(String key, String str, Long time) {
        stringRedisTemplate.opsForValue().set(key, str, time, TimeUnit.MILLISECONDS);
    }

    public void addString(String key, String str) {
        stringRedisTemplate.opsForValue().set(key, str);
    }

    public <T> T getObject(String key, Class<T> cls) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            return new Gson().fromJson(source, cls);
        }
        return null;
    }

    public <T> List<T> getObjectList(String key, Class<T> cls) {
        String source = stringRedisTemplate.opsForValue().get(key);
        if (!StringUtils.isEmpty(source)) {
            Gson gson = new Gson();
            List<T> list = new ArrayList<T>();
            JsonArray array = new JsonParser().parse(source).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
            return list;
        }
        return null;
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public void delete(String key) {
        stringRedisTemplate.opsForValue().getOperations().delete(key);
    }
}
