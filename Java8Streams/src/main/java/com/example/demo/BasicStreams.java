package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.sql.rowset.Joinable;

public class BasicStreams {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1,2,3,5,4,7,6,9,8);
		
		Stream<Integer> streams= list.stream();
		
		//print all the numbers in the list
		System.out.println("Printing all the numbers: ");
		list.stream().forEach(i->System.out.print(i+" "));
		System.out.println();
		
		
		//filter odd numbers in the list
		System.out.println("Printing all the Odd numbers: ");
		list.stream().filter(i-> (i%2)!=0).forEach(System.out::print);
		System.out.println();
		
		
		//sort numbers of the list
		System.out.println("Printing the list in sorted order: ");
		System.out.println(list.stream().sorted().collect(Collectors.toList()));
		
		//get square of all the numbers in the list
		System.out.println("Printing the square of the numbers in the list: ");
		System.out.println(list.stream().map(i->i*i).collect(Collectors.toList()));
		
		//create and iterate a map
		Map<Integer,String> m= new HashMap<>();
		m.put(1, "debashree");
		m.put(2, "sagar");
		m.put(3, "krish");
		System.out.println("Printing the key values in the map: ");
		m.forEach((key,value)->System.out.println(key+"  "+value));
		
		
		/*Diffrent ways of getting stream object */		
		
		//create empty Stream
		Stream<Object> stream1= Stream.empty();
		//create stream object with data
		Stream<String> stream2= Stream.of("abc","xyz","mnp");
		//create Streams using builder patter
		Stream stream3= Stream.builder().build();
		
		IntStream intStream = Arrays.stream(new int[]{1,7,3,5});
		
		Stream stream4= list.stream();
		
		
	//	int arr[] =new int[]{23,34,56,42,89,67,91};
		Stream<Integer> stream5= Stream.of(23,34,56,42,89,67,91);
		
		Optional<Integer> i=stream5.min((a,b)->a.compareTo(b));
		System.out.println("Min Number in array: "+i);
		
		//Optional<Integer> j=stream5.min((a,b)->a.compareTo(b));
		//System.out.println("Max Number in array: "+j);
		
		
		Stream<String> stream6= Stream.of("sagar","mamuni","debashree");
		stream6.filter(s->s.startsWith("m") && s.length()==6).forEach(System.out::print);
		System.out.println();
		
		List<Integer> list1 = new ArrayList<>();
		list1.add(1);
		list1.add(2);
		list1.add(3);
		list1.add(1);
		StringBuffer s =new StringBuffer();
		System.out.println(list1.stream().map(x -> x%2==0 ? "e"+x : "o"+x).collect(Collectors.joining(",")));
		
		int x=1;
		for(int  l=0;l<list1.size();l++) {
			if(x==list1.get(l))
		System.out.println("indexof:::"+l);
		}
		
		System.out.println(reverse("mamuni"));
		
		int sum=0;
	System.out.println(list1.stream().map(d->d+d).toList());
	
	System.out.println(stream2.map(String::toUpperCase)
	  .collect(Collectors.toList()));
		
	}
    public static String reverse(String s) {
		if(s.length()==0){
			return "";
			}
			//else return s.charAt(s.length()-1)+""+reverse(s.substring(1,s.length()));
		  else {
			  System.out.println(s.length()-1+"  "+s.substring(0,s.length()-1));
			  return  s.charAt(s.length()-1)+reverse(s.substring(0,s.length()-1));
			  }
    }
	
}
