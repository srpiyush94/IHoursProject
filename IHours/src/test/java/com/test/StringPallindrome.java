package com.test;

public class StringPallindrome {

	public static void main(String[] args) {
	
		String str="msam";
		char c[]=str.toCharArray();
		int sz=c.length,count=0,i;
		
		for(i=0;i<sz;i++)
		{
			if(c[i]==c[sz-1-i])
			{
				count++;
				
			}
		}
			if(count==sz)
			{
				System.out.println(str+":String pallindrome");
				
			}
			else
			{
			System.out.println(str+":String is not pallindrom");	
			
			}
			
		}
		

}


