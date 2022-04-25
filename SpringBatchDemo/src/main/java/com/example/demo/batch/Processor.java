package com.example.demo.batch;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.example.demo.model.User;

@Component
public class Processor implements ItemProcessor<User,User>{

	public static final Map<String,String> dept_names= new HashMap<>();
	
	

	public Processor() {
		dept_names.put("2", "ACCOUNTS");
		dept_names.put("3", "OPERATION");
		dept_names.put("4", "HR");
	}



	@Override
	public User process(User user) throws Exception {
		String deptCode= user.getDept();
		String dept =dept_names.get(deptCode);
		user.setDept(dept);		
		return user;
	}
	
	
	
	

}
