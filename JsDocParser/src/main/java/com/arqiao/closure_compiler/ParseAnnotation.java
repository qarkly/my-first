package com.arqiao.closure_compiler;

import java.lang.reflect.Method;

import com.sun.org.glassfish.gmbal.Description;

public class ParseAnnotation {
		 
		 public static void main(String[] args) throws Exception{
		  final Class cls = Class.forName("");
		  final Method[] method = cls.getMethods();
		  
		  // 判断是否有指定注解类型的注解
		  if(cls.isAnnotationPresent(Description.class)) {
		   // 根据注解类型返回指定类型的注解
		   Description des = (Description)cls.getAnnotation(Description.class);
		   System.out.println("注解描述:" + des.value());
		  }
		 }
}
