package com.arqiao.closure_compiler;

import java.io.FileInputStream;
import java.io.IOException;

import com.google.javascript.jscomp.parsing.Config.LanguageMode;
import com.google.javascript.jscomp.parsing.ParserRunner;
import com.google.javascript.jscomp.parsing.parser.Parser.Config.Mode;
import com.google.javascript.rhino.JSDocInfo;
import com.google.javascript.rhino.Node;
import com.google.javascript.rhino.jstype.SimpleSourceFile;

public class JsDocParser {
	
	public static void main(String[] args) throws IOException {
		TestErrorReporter testErrorReporter = new TestErrorReporter(null,null);
		FileInputStream fiStream = new FileInputStream("foo.js");
		byte[] byts = new byte[fiStream.available()];
		fiStream.read(byts);
		fiStream.close();
		
		String source = new String(byts);
		
		LanguageMode mode = LanguageMode.ECMASCRIPT5;
	    Node script = ParserRunner.parse(
	        new SimpleSourceFile("input", false),
	        source,
	        ParserRunner.createConfig(true, mode, false),
	        testErrorReporter).ast;
	    JSDocInfo jsDocInfo = script.getFirstChild().getJSDocInfo();
	
	    System.out.println(jsDocInfo.hasParameter("@param"));
	}

}
