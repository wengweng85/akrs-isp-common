package com.epsoft.isp.webserivce.model;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by wengsh on 2018/2/28.
 *
 *
 *   @XmlType
     @XmlElement
     @XmlRootElement
     @XmlAttribute
     @XmlAccessorType
     @XmlAccessorOrder
     @XmlTransient
     @XmlJavaTypeAdapter
     @XmlElementWrapper
     @XmlList
 */
@XmlRootElement(name="studentBean")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private String address;

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

}