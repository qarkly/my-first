/*
 * 文 件 名:  SmartDial.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  201103技能鉴定实战考试JAVA三级第二题
 */
package huawei;

import java.util.ArrayList;

/**
 * 本类实现手机数字键盘智能查找联系人的能力.
 * 标准的手机数字键盘中2-9键分别对应26个字母.
 * 通过输入联系人姓或者名的前几个字母,联系人电话号码的部分连续数字,
 * 联系人姓和名的首字母来匹配联系人记录.
 * 用户输入数字串(长度必须大于等于2)按如下规则匹配联系人记录:
 * 1)联系人号码匹配:输入联系人号码所包含的任意一段数字可匹配该联系人.
 * 比如某个联系人的号码是13951905919,用户可以输入号码的任意一段匹配,
 * 如输入"139","951","5919"等.
 * 2)联系人姓或者名匹配:输入联系人姓或者名的英文单词的首几个字母(对应的数字键)
 * 可匹配该联系人.比如某个联系人姓名为"Brian Yang",用户可以输入9(wxyz)2(abc)
 * 或者2(abc)7(pqrs)4(ghi)来匹配该联系人.
 * 3)联系人姓和名的首字母匹配:输入联系人姓和名的英文单词首字母可匹配该联系人,
 * 且姓和名的顺序可以颠倒.比如某个联系人姓名为"Brian Yang",
 * 用户可以输入2(abc)9(wxyz)或者9(wxyz)2(abc)来匹配该联系人.
 */
public class SmartDial
{
    /** 用户保存的所有联系人集合 */
    ArrayList contacts = new ArrayList();
    
    /**
     * 添加联系人.
     * @param firstName 名,只包含英文26个字母的大写或小写且值不为空
     * @param lastName  姓,只包含英文26个字母的大写或小写且值不为空
     * @param number 号码,只包含数字且值不为空
     */
    public void addContact(String firstName, String lastName, String number)
    {
        addContact(new Contact(firstName, lastName, number));
    }
    
    /**
     * 添加联系人.
     * @param contact 联系人
     */
    public void addContact(Contact contact)
    {
        contacts.add(contact);
    }
    
    /**
     * 返回符合条件的联系人集合.
     * @param digitalSequence 数字串,长度大于等于2,可以代表部分号码序列，
     *                        姓或者名的首几个字母，长度等于2时还可以代表姓名的首字母
     * @return ArrayList     符合条件的联系人集合（集合中的元素类型为Contact）
     *                       digitalSequence的长度小于2返回null
     *                       没有符合条件的记录返回空ArrayList
     * 
     */
    public ArrayList findContacts(String digitalSequence)
    {
        if (digitalSequence.length() < 2)
        {
            return null;
        }
        ArrayList matchContacts = new ArrayList();
        
        //TODO 请考生完成......
        
           
        return matchContacts;
    }
}
