package com.springcxf.test;

import org.junit.runner.RunWith;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * 
 * @author 张靠勤
 * @e-mail 627658539@qq.com
 * @version v1.0
 * @copyright 2010-2015
 * @create-time 2013-1-14 下午5:14:16
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/applicationContext.xml")
public class HelloserviceTest {

	/*@Autowired
	private RpcHelloService rpcHelloService;
	

	@Test
	public void testSayHi() {
		long i=0l;
		while (true) {
			System.out.println(rpcHelloService.sayHi("我的天啊"));
			i++;
			System.out.println(i);
		}
	}*/
	
	
	@org.junit.Test
	@Scheduled(cron = "0/5 * * * * *")
	public void Test() throws Exception{
		System.out.println("mmm");
	}
	

}
