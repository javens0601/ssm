package com.javen.mybatisplus;

import com.javen.beans.User;
import com.javen.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Test
	void contextLoads() {
	}

	@Test
	public void testSelect() {
		System.out.println(("----- selectAll method test ------"));
		List<User> userList = userMapper.selectList(null);
		//Assert.assertEquals(5, userList.size());
		userList.forEach(System.out::println);

	}

	@Test
	public void insertTest() {
		//insert的时候自动生成id
		User user = new User();
		//user.setId(10L);
		user.setName("jinwei");
		user.setAge(25);
		user.setEmail("javens0601@163.com");
		int count = userMapper.insert(user);
		System.out.println(user);
	}

	/**
	 * 测试乐观锁
	 */
	@Test
	public void testOptimisticLocker() {
		//查询用户
		User user = userMapper.selectById(8L);
		//修改用户信息
		user.setName("liuhe");
		int i = userMapper.updateById(user);
		System.out.println(userMapper.selectById(8L));
	}

	/**
	 * 查询多条数据
	 */
	@Test
	public void testSelectBatch() {
		List<User> users = userMapper.selectBatchIds(Arrays.asList("1", "2", "3"));
		users.forEach(System.out::println);
	}

	/**
	 * 按条件查询
	 */
	@Test
	public void testSelectbyMap() {
		Map<String, Object> userMap = new HashMap<>();
		userMap.put("id", 8);
		System.out.println(userMapper.selectByMap(userMap));
	}

}
