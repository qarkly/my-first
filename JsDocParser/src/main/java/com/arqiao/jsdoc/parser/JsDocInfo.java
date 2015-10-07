package com.arqiao.jsdoc.parser;

import java.util.ArrayList;
import java.util.List;


public class JsDocInfo {
	
	private int line;
	
	private String description;
	
	private String source;
	
	private List<Marker> tags = null;
	
	private JsDocInfo(){}
	
	public static JsDocInfo createJsDocInfo()
	{
		return new JsDocInfo();
	}
	
	public void addMarker(Marker marker)
	{
		if(tags == null)
		{
			tags  = new ArrayList<JsDocInfo.Marker>();
		}
		
		if(marker != null)
		{
			tags.add(marker);
		}
	}
	
	
	public int getLine() {
		return line;
	}




	public void setLine(int line) {
		this.line = line;
	}




	public String getDescription() {
		return description;
	}




	public void setDescription(String description) {
		this.description = description;
	}




	public String getSource() {
		return source;
	}




	public void setSource(String source) {
		this.source = source;
	}




	static class Marker{
		private String tag;
		
		private String type;
		
		private String name;
		
		private String description;
		
		private int line;
		
		private String source;
		
		public Marker(String tag)
		{
			this.tag = tag;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getLine() {
			return line;
		}

		public void setLine(int line) {
			this.line = line;
		}

		public String getSource() {
			return source;
		}

		public void setSource(String source) {
			this.source = source;
		}
		
		
	}

}
