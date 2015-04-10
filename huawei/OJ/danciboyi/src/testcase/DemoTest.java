package testcase;

import huawei.Demo;

import junit.framework.TestCase;

public class DemoTest extends TestCase
{
	public void testCase01  ()
    {        
    assertEquals((new Demo().who("aaa")), 0); 
    assertEquals((new Demo().who("gmiim")),  0);  
    assertEquals((new Demo().who("ndnmd")),  0); 
    assertEquals((new Demo().who("igjnf")),  0);   
    assertEquals((new Demo().who("bklli")),  0); 
    assertEquals((new Demo().who("efnej")),  1);
    assertEquals((new Demo().who("gfbni")),  1); 
    assertEquals((new Demo().who("hdffc")),  0);
    assertEquals((new Demo().who("fbcdc")),  0);
    assertEquals((new Demo().who("naghh")),  0);
}
    
                   
                        
}                       
                        
