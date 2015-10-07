package com.arqiao.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.Charset;

import com.arqiao.jsdoc.parser.JsDocInfoParser;
import com.arqiao.jsdoc.parser.JsDocToken;
import com.arqiao.jsdoc.parser.JsDocTokenStream;

/**
 * Hello world!
 *
 */
public class App 
{
	
	static String readFile(String filePath) throws IOException
	{
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filePath));
		
		int size = bis.available();
		
		byte[] byts = new byte[size];
		
		bis.read(byts);
		
		String source = new String(byts, Charset.forName("utf-8"));
		
		return source;
	}
	
    public static void main( String[] args ) throws IOException
    {
        String jsDocStr = readFile("foo.js");
        System.out.println("jsDoc:"+jsDocStr);
        
        JsDocTokenStream tokenStream = new JsDocTokenStream(jsDocStr);
        
       JsDocInfoParser parser = new JsDocInfoParser(tokenStream);
       
       parser.parse();
    }
}
