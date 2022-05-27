package cn.dcube.ahead.base.xml;

import java.io.File;
import java.io.StringBufferInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import cn.dcube.ahead.base.exception.AheadRuntimeException;

/**
 * 通过jaxb将xml转换为对象
 *
 * @author：yangfei<br>
 * @date：2021年3月24日上午9:13:16
 * @since 1.0
 */
@SuppressWarnings("deprecation")
public class JaxbUtil {

    /**
     * JavaBean转换成xml 默认编码UTF-8
     *
     * @param obj the object
     * @return xml字符串
     * @throws Exception the exception
     */
    public static <T> String convertToXml(T obj) throws Exception {
        return convertToXml(obj, "UTF-8");
    }

    /**
     * JavaBean转换成xml
     *
     * @param obj      the object
     * @param encoding the encoding
     * @return xml字符串
     * @throws Exception the exception
     */
    public static <T> String convertToXml(T obj, String encoding) throws Exception {
        String result = null;
        try {
            JAXBContext context = JAXBContext.newInstance(obj.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

            StringWriter writer = new StringWriter();
            marshaller.marshal(obj, writer);
            result = writer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            throw new AheadRuntimeException(e);
        }

        return result;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xml xml字符串
     * @param c   对象类型
     * @return 对象
     * @throws Exception the exception
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(String xml, Class<T> c) throws Exception {
        T t = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(new StringBufferInputStream(xml));
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AheadRuntimeException(e);
        }
        return t;
    }

    /**
     * xml转换成JavaBean
     *
     * @param xmlFile xml文件
     * @param c       对象类型
     * @return 对象实例
     * @throws Exception the exception.
     */
    @SuppressWarnings("unchecked")
    public static <T> T converyToJavaBean(File xmlFile, Class<T> c) throws Exception {
        T t = null;
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setExpandEntityReferences(false);
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(xmlFile);
            JAXBContext context = JAXBContext.newInstance(c);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            t = (T) unmarshaller.unmarshal(document);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AheadRuntimeException(e);
        }
        return t;
    }

}
