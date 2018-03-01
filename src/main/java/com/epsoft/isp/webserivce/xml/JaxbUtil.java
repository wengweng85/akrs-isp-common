package com.epsoft.isp.webserivce.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;


public class JaxbUtil {

    private static JaxbUtil jaxbutil;

    // 多线程安全的Context.
    private static  JAXBContext jaxbContext;

    public JaxbUtil(Class<?>... types){
        try {
            jaxbContext = JAXBContext.newInstance(types);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public static JaxbUtil getInstance(Class<?>... types){
        if(jaxbutil==null){
            jaxbutil=new JaxbUtil();
            try {
                jaxbContext = JAXBContext.newInstance(types);
            } catch (JAXBException e) {
                throw new RuntimeException(e);
            }
        }
        return jaxbutil;
    }


    /**
     * Java Object->Xml.
     */
    public String toXml(Object root, String encoding) {
        try {
            StringWriter writer = new StringWriter();
            //添加自己想要的xml报文头
            //writer.write("<?xml version=\'1.0\' encoding=\'" + encoding + "\'?>\n");
            writer.write("");
            Marshaller marshaller= createMarshaller(encoding);
            marshaller.marshal(root, writer);
            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Java Object->Xml, 特别支持对Root Element是Collection的情形.
     */
    @SuppressWarnings("unchecked")
    public String toXml(Collection root, String rootName, String encoding) {
        try {
            CollectionWrapper wrapper = new CollectionWrapper();
            wrapper.collection = root;

            JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(new QName(rootName), CollectionWrapper.class, wrapper);

            StringWriter writer = new StringWriter();
            createMarshaller(encoding).marshal(wrapperElement, writer);

            return writer.toString();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xml->Java Object.
     */
    @SuppressWarnings("unchecked")
    public <T> T fromXml(String xml) {
        try {
            if(!xml.equals("")){
                StringReader reader = new StringReader(xml);
                return (T) createUnmarshaller().unmarshal(reader);
            }else{
                return null;
            }
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Xml->Java Object, 支持大小写敏感或不敏感.
     */
    @SuppressWarnings("unchecked")
    public <T> T fromXml(String xml, boolean caseSensitive) {
        try {
            String fromXml = xml;
            if (!caseSensitive)
                fromXml = xml.toLowerCase();
            StringReader reader = new StringReader(fromXml);
            return (T) createUnmarshaller().unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建Marshaller, 设定encoding(可为Null).
     */
    public Marshaller createMarshaller(String encoding) {
        try {
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);
            //去掉生成xml的默认报文头。
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
            //转换所有的适配字符，包括xml实体字符&lt;和&gt;，xml实体字符在好多处理xml
            //的框架中是处理不了的，除非序列化。

            if (!encoding.equals("")) {
                marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
            }
            return marshaller;
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建UnMarshaller.
     */
    public Unmarshaller createUnmarshaller() {
        try {
            return jaxbContext.createUnmarshaller();
        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 封装Root Element 是 Collection的情况.
     */
    public static class CollectionWrapper {
        @SuppressWarnings("unchecked")
        @XmlAnyElement
        protected Collection collection;
    }

}
