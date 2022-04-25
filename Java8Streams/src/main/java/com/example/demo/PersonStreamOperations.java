package com.example.demo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class PersonStreamOperations {

	public static void main(String[] args) {
		List<Person> personList= Arrays.asList(
				new Person("debashree","pratap",29),
				new Person("sagar","nayak",30),
				new Person("balaji","panda",32),
				new Person("krish","jena",32));
		//Print person details where last name starts with 'n'
		personList.stream().filter(p->p.getLastName().startsWith("n")).collect(Collectors.toList()).forEach(System.out::print);
		System.out.println();
		
		//Print the first name where last name starts with 'n'
		personList.stream().filter(p->p.getLastName().startsWith("n")).collect(Collectors.toList()).forEach(p->System.out.print(p.getFirstName()));
		System.out.println();
		
		//sort the Person objects in ascending order of firstname
		personList.stream().sorted((p1,p2) -> p1.getFirstName().compareTo(p2.getFirstName())).collect(Collectors.toList()).forEach(System.out::print);
		System.out.println();
		
		//change the first name to 'testName' if first name starts with 'b'
		System.out.println(personList.stream().filter(p->p.getFirstName().startsWith("b"))
				                              .map(p->{p.setFirstName("testName");
		                                               return p; })
				                              .collect(Collectors.toList()));
	
	}
}
