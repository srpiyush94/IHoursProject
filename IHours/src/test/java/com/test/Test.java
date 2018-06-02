package com.test;

import com.demo.Animal;

class Dog implements Animal
{

	public void eat() {
	System.out.println("dog is eating");
		
	}
	

}

class Tiger implements Animal
{

	public void eat() {
		System.out.println("Tiger is eating");
		
	}
	

}
class Cat implements Animal
{

	public void eat() {
		System.out.println("cat is eating");
		
	}
	

}
class FactoryDAO
{
		public  static Animal getInstanceAnimal(String type)
		{
			if(type==null)
			{
				return null;
				
			}
			else if(type.equals("dog"))
			{
			return new Dog();
			}
			
			else if(type.equals("tiger"))
			{
			return new Tiger();
			}
			
			else if(type.equals("cat"))
			{
			return new Cat();
			}
			else
				return null;
		}


}

public class Test {

	public static void main(String[] args) {

		
		Animal a=FactoryDAO.getInstanceAnimal("cat");//new Cat();//new Tiger();new Dog();
		System.out.println(a.hashCode());
		Animal a1=FactoryDAO.getInstanceAnimal("cat");
		System.out.println(a1.hashCode());
			
		a.eat();

	}

}
