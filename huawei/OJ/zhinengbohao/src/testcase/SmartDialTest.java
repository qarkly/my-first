/*
 * 文 件 名:  SmartDialTest.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  201103技能鉴定实战考试JAVA三级第二题单元测试框架
 */
package testcase;

import huawei.Contact;
import huawei.SmartDial;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import junit.framework.TestCase;

/**
 * 测试SmartDial类
 * 测试用例不做为阅卷使用，阅卷时可能会替换并增加新的用例。
 */
public class SmartDialTest extends TestCase
{
    private SmartDial smartDial = new SmartDial();
    
    private Contact contact1 = new Contact("Brian", "Yang", "13951905919");
    private Contact contact2 = new Contact("Jiali", "Chen", "13851958680");
    
    public void setUp() throws Exception
    {
        smartDial.addContact(contact1);
        smartDial.addContact(contact2);
    }
    
    public void test001()
    {
        ArrayList matchContacts = smartDial.findContacts("519");
        Collections.sort(matchContacts, new ContactComparator());
        
        ArrayList expectContacts = new ArrayList();
        expectContacts.add(contact1);
        expectContacts.add(contact2);
        Collections.sort(expectContacts, new ContactComparator());
        
        if (expectContacts.size() != matchContacts.size())
        {
            fail();
        }
        
        for (int i=0; i<expectContacts.size(); i++)
        {
            if (!expectContacts.get(i).equals(matchContacts.get(i)))
            { 
                fail();  
            }
        }        
    }
    
    public void test002()
    {
        ArrayList matchContacts = smartDial.findContacts("926");
        Collections.sort(matchContacts, new ContactComparator());
        
        ArrayList expectContacts = new ArrayList();
        expectContacts.add(contact1);
        Collections.sort(expectContacts, new ContactComparator());
        
        if (expectContacts.size() != matchContacts.size())
        {
            fail();
        }
        
        for (int i=0; i<expectContacts.size(); i++)
        {
            if (!expectContacts.get(i).equals(matchContacts.get(i)))
            { 
                fail();  
            }
        }        
    }
    
    public void test003()
    {
        ArrayList matchContacts = smartDial.findContacts("52");
        Collections.sort(matchContacts, new ContactComparator());
        
        ArrayList expectContacts = new ArrayList();
        expectContacts.add(contact2);
        Collections.sort(expectContacts, new ContactComparator());
        
        if (expectContacts.size() != matchContacts.size())
        {
            fail();
        }
        
        for (int i=0; i<expectContacts.size(); i++)
        {
            if (!expectContacts.get(i).equals(matchContacts.get(i)))
            { 
                fail();  
            }
        }        
    }
    
    private class ContactComparator implements Comparator
    {
        public int compare(Object o1, Object o2)
        {
            String c1 = ((Contact)o1).getFirstName()+"+"+((Contact)o1).getLastName()+"+"+((Contact)o1).getNumber();
            String c2 = ((Contact)o2).getFirstName()+"+"+((Contact)o2).getLastName()+"+"+((Contact)o2).getNumber();
            return c1.compareTo(c2);
        }
    }
    
}
