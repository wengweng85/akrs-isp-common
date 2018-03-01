package com.epsoft.isp.webserivce.model;

import com.epsoft.isp.webserivce.xml.CDataAdapter;
import com.epsoft.isp.webserivce.xml.JaxbDateAdapter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by wengsh on 2018/2/28.
 *
 *
     1、@XmlType，将Java类或枚举类型映射到XML模式类型
     2、@XmlAccessorType(XmlAccessType.FIELD) ，控制字段或属性的序列化。FIELD表示JAXB将自动绑定Java类中的每个非静态的（static）、非瞬态的（由@XmlTransient标注）字段到XML。其他值还有XmlAccessType.PROPERTY和XmlAccessType.NONE。
     3、@XmlAccessorOrder，控制JAXB 绑定类中属性和字段的排序。
     4、@XmlJavaTypeAdapter，使用定制的适配器（即扩展抽象类XmlAdapter并覆盖marshal()和unmarshal()方法），以序列化Java类为XML。
     5、@XmlElementWrapper ，对于数组或集合（即包含多个元素的成员变量），生成一个包装该数组或集合的XML元素（称为包装器）。
     6、@XmlRootElement，将Java类或枚举类型映射到XML元素。
     7、@XmlElement，将Java类的一个属性映射到与属性同名的一个XML元素。
     8、@XmlAttribute，将Java类的一个属性映射到与属性同名的一个XML属性。
     9、@XmlElementWrapper注解表示生成一个包装 XML 表示形式的包装器元素。 此元素主要用于生成一个包装集合的包装器 XML 元素。因此，该注释支持以下两种形式的序列化。
     10、 @XmlTransient 用于标示在由java对象映射xml时，忽略此属性。即，在生成的xml文件中不出现此元素。

 */
@XmlRootElement(name="student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @XmlJavaTypeAdapter(CDataAdapter.class)
    private String name;
    private int age;
    private String address;
    @XmlJavaTypeAdapter(JaxbDateAdapter.class)
    private Date birthday;

    @XmlElementWrapper(name="hobbylist")
    private List<Hobby> hobby;

    public List<Hobby> getHobby() {
        return hobby;
    }

    public void setHobby(List<Hobby> hobby) {
        this.hobby = hobby;
    }


    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}