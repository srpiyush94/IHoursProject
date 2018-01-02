package com.test;
import java.util.*;

class Employee
{
	private int contact;
	private String name;
	private double salary;
	public Employee(int contact,String name,double salary)
	{
		this.contact=contact;
		this.name=name;
		this.salary=salary;
	}
	public int getContact(){return contact;}
	public String getName(){return name;}
	public double getSalary(){return salary;}
	
	public String toString()
	{
		return "Contact:"+contact+"\tName:"+name+"\t Salary:"+salary;		
		
	}
	
}
	class SalarySort implements Comparator<Employee>
	{

		public int compare(Employee o1, Employee o2) {
		
		double result=o1.getSalary()-o2.getSalary();
		
		if(result>0)
		{
			return 0;
			
		}else if(result<0)
		{
			return -1;
		}
		else
		{
			return 0;
			
		}
		
		}
	}
	

public class ComparatorTest {
	public static void main(String[] args) {
		
		ArrayList<Employee> list=new ArrayList<Employee>();
		list.add(new Employee(6565,"piyush",1500.55));
		list.add(new Employee(5655,"arun",100.55));
		list.add(new Employee(7558,"kiran",1800.55));
		
		for(Employee e:list)
		{
			System.out.println(e);
			
		}
		
		SalarySort s=new SalarySort();
		Collections.sort(list,s);
		
		for(Employee e:list)
		{
			System.out.println(e);
			
		}
		
		
	}

}
