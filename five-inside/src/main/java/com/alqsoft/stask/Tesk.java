package com.alqsoft.stask;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 
 * @Description: TODO
 * @author 时永青
 * @version v1.0
 * @copyright 2010-2017
 * @create-time 2017年2月9日 下午4:46:26
 * Copyright © 2017 北京易商通公司 All rights reserved.
 * 
 */
@Component
@Lazy(value=false)
public class Tesk {
	
	/*@Scheduled(cron="0/1 * * * * ?")
	public void ss(){
		System.out.println("mmddm");
	}*/

}
