package com.qf.service.impl;

import com.qf.constant.RedisConstant;
import com.qf.constant.StringConstant;
import com.qf.entity.TProductStore;
import com.qf.entity.TProductType;
import com.qf.mapper.product.ProductStoreMapper;
import com.qf.mapper.product.ProductTypeMapper;
import com.qf.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class ProductService implements IProductService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductTypeMapper productTypeMapper;

    @Autowired
    private ProductStoreMapper productStoreMapper;

    @Override
    public List<TProductType> queryProductType() {
        //获取redis中存储的商品类别
        List<TProductType> typeList = (List<TProductType>) redisTemplate.opsForValue().get(RedisConstant.PRODUCT_TYPE);
        if (typeList ==null ){
            String uuid = UUID.randomUUID().toString();
            //开启分布式锁
            Boolean flag = redisTemplate.opsForValue().setIfAbsent(StringConstant.redis_types_lock, uuid,5, TimeUnit.MINUTES);
            if (flag){
                try {
                    //设置超时时间
                    redisTemplate.expire(StringConstant.redis_types_lock,5,TimeUnit.MINUTES);
                    //查询数据
                    typeList = productTypeMapper.queryProductType();
                    //将数据存放到redis中
                    redisTemplate.opsForValue().set(RedisConstant.PRODUCT_TYPE,typeList);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //释放锁
                    String currentUUID = (String) redisTemplate.opsForValue().get(StringConstant.redis_types_lock);
                    if (currentUUID.equals(uuid)){
                        redisTemplate.delete(StringConstant.redis_types_lock);
                    }
                }
            }else{
                try {
                    Thread.sleep(10);
                    return queryProductType();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return typeList;
    }

    @Override
    public void insertStoreToRedis() {
        List<TProductStore> tProductStores = productStoreMapper.insertStoreToRedis();
        redisTemplate.opsForValue().set(RedisConstant.PRODUCT_STORE,tProductStores);
    }
}
