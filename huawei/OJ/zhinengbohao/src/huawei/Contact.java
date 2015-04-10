/*
 * 文 件 名:  Contact.java
 * 版    权:  Huawei Technologies Co., Ltd. Copyright YYYY-YYYY,  All rights reserved
 * 描    述:  201103技能鉴定实战考试JAVA三级第二题
 */
package huawei;

/**
 * 联系人实体类
 */
public class Contact
{
    /** 名 */
    private String firstName;

    /** 姓 */
    private String lastName;

    /** 号码 */
    private String number;

    /**
     * 构造函数.
     * @param firstName 名,只包含英文26个字母的大写或小写且值不为空
     * @param lastName  姓,只包含英文26个字母的大写或小写且值不为空
     * @param number 号码,只包含数字且值不为空
     */
    public Contact(String firstName, String lastName, String number)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    /**
     * @return 返回 lastName
     */
    public String getLastName()
    {
        return lastName;
    }

    /**
     * @param 对lastName进行赋值
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    /**
     * @return 返回 firstName
     */
    public String getFirstName()
    {
        return firstName;
    }

    /**
     * @param 对firstName进行赋值
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    /**
     * @return 返回 number
     */
    public String getNumber()
    {
        return number;
    }

    /**
     * @param 对number进行赋值
     */
    public void setNumber(String number)
    {
        this.number = number;
    }

    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }

        if ((o == null) || (!(o instanceof Contact)))
        {
            return false;
        }

        Contact c = (Contact)o;
        if (this.firstName.equals(c.getFirstName())
                && this.lastName.equals(c.getLastName())
                && this.number.equals(c.getNumber()))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
