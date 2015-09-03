package com.torana.training.xmlParser;

import java.io.File;
import java.io.PrintWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLParser {
    static Logger logger = LoggerFactory.getLogger(XMLParser.class);

    public static void main(String args[]) {
        try {

            File instances = new File("S:\\Work\\Training\\java\\xmlParser\\src\\main\\resources\\QUISKData3.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(instances);
            doc.getDocumentElement().normalize();


            System.out.println("root of xml file" + doc.getDocumentElement().getNodeName());
            NodeList nodes = doc.getElementsByTagName("Instance");
            PrintWriter writer = new PrintWriter("S:\\Work\\Training\\java\\xmlParser\\src\\main\\resources\\QUISKData3.snmprec");
            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;

                    writer.println(element.getAttribute("oid") + "|" + getTagValue(element) + "|" + getValue("Value", element));

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static String getTagValue(Element element) {
        String valueType = element.getAttribute("valueType");
        if (valueType.equals("Integer")) return "2";
        if (valueType.equals("OctetString")) return "4";
        if (valueType.equals("NULL")) return "5";
        if (valueType.equals("OID")) return "6";
        if (valueType.equals("IpAddress")) return "64";
        if (valueType.equals("Counter32")) return "65";
        if (valueType.equals("Gauge")) return "66";
        if (valueType.equals("TimeTicks")) return "67";
        if (valueType.equals("Opaque")) return "68";
        if (valueType.equals("Counter64")) return "70";
        return "";
    }

    private static String getValue(String tag, Element element) {
        NodeList nodes = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = (Node) nodes.item(0);
        return node.getNodeValue();
    }
}