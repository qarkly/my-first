package com.arqiao.jsdoc.parser.util;

public class Assert {
	
	public static void checkNotNull(Object obj)
	{
		if(null == obj)
		{
			throw new NullPointerException("Object is null ");
		}
	}

	
	public static boolean isNUllOrEmpty(String param)
	{
		if(null == param || param.isEmpty())
		{
			return true;
		}else {
			return false;
		}
	}
}
