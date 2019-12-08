package com.gangxin.test;

import java.util.ArrayList;
import java.util.HashMap;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.gangxin.common.utils.RandomUtil;
import com.gangxin.common.utils.StringUtil;
import com.gangxin.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-redis.xml")
public class RedisTest {
	
	@Resource
	private RedisTemplate redisTemplate;
	
	@Test
	public void JDKTest() {
		
		ArrayList<User> user_list = new ArrayList<User>();
		
		for (int i = 1; i <= 50000; i++) {
			
			String name=StringUtil.randomChineseString(3);
			String phone=RandomUtil.randomString(9);
			User user = new User(i,name,"13"+phone);
			user_list.add(user);
			
			
		}
		
		ListOperations opsForList = redisTemplate.opsForList();
		
		Long start=System.currentTimeMillis();
		opsForList.leftPushAll("user_jdk", user_list);
		Long end=System.currentTimeMillis();
		
		System.out.println("存储完毕"+(end-start)+"毫秒");
		
	}
	
	@Test
	public void JSONTest() {
		
		ArrayList<User> user_list = new ArrayList<User>();
		
		for (int i = 1; i <= 50000; i++) {
			
			String name=StringUtil.randomChineseString(3);
			String phone=RandomUtil.randomString(9);
			User user = new User(i,name,"13"+phone);
			user_list.add(user);
				
		}
		
		ListOperations opsForList = redisTemplate.opsForList();
		
		Long start=System.currentTimeMillis();
		opsForList.leftPushAll("user_json", user_list);
		Long end=System.currentTimeMillis();
		
		System.out.println("存储完毕"+(end-start)+"毫秒");
		
	}
	
	@Test
	public void HashTest() {
		
		HashMap<String, User> hash_map = new HashMap<String,User>();
		for (int i = 1; i <= 50000; i++) {
			
			String name=StringUtil.randomChineseString(3);
			String phone=RandomUtil.randomString(9);
			User user = new User(i,name,"13"+phone);
			hash_map.put(i+"",user);
				
		} 
		HashOperations opsForHash = redisTemplate.opsForHash();
		
		Long start=System.currentTimeMillis();
		opsForHash.putAll("hash_jdk", hash_map);
		Long end=System.currentTimeMillis();
		
		System.out.println("存储完毕"+(end-start)+"毫秒");
		
	}
	
	

}
