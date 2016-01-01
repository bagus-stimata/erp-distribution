package org.erp.distribution;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.erp.distribution.jpaservice.FAreaJpaService;
import org.erp.distribution.jpaservice.FAreaJpaServiceImpl;
import org.erp.distribution.jpaservice.FStockJpaService;
import org.erp.distribution.jpaservice.FStockJpaServiceImpl;
import org.erp.distribution.model.FArea;
import org.erp.distribution.model.FProduct;
import org.erp.distribution.model.FStock;
import org.erp.distribution.model.FWarehouse;
import org.erp.distribution.util.TransaksiHelper;
import org.erp.distribution.util.TransaksiHelperImpl;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Test {

	public static void main(String [] args) throws ParserConfigurationException, SAXException, IOException{
		
		String passwd = "hacker";
       	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
 
        // lokasi file XML
        File file = new File("/home/yhawin/persistence.xml");
 
        // konversi file menjadi Document
        Document document = builder.parse(file);
 
        // mengambil tag perusahaan
        Element persistence = (Element) document.getElementsByTagName("persistence").item(0);
        System.out.println("VERSION: " + persistence.getAttribute("version"));
        System.out.println("xmlns: " + persistence.getAttribute("xmlns"));
        
        NodeList list = persistence.getElementsByTagName("persistence-unit");        
        for (int i = 0; i < list.getLength(); i++) {
            Element elementPersistence_unit = (Element) list.item(i); 
 
            Node nodeProvider =  elementPersistence_unit.getElementsByTagName("provider").item(0);
            System.out.println("Provider : " + nodeProvider.getTextContent());
 
            NodeList listClass = elementPersistence_unit.getElementsByTagName("class");
            for (int pencacahClass=0; pencacahClass<listClass.getLength();pencacahClass++){
            	Element elementClass = (Element) listClass.item(pencacahClass);
//            	System.out.println(elementClass.getAttribute(""));
            	System.out.println(elementClass.getTextContent());
            }
            
            NodeList nodeListProperties = elementPersistence_unit.getElementsByTagName("properties");
            for (int pencacahProperties=0; pencacahProperties<nodeListProperties.getLength(); pencacahProperties++){
            	Element elementProperties = (Element) nodeListProperties.item(pencacahProperties);
            	
            	NodeList nodeListProperty = elementProperties.getElementsByTagName("property");
            	for (int pencacahProperty=0; pencacahProperty< nodeListProperty.getLength(); pencacahProperty++){
            		Element elementProperty = (Element) nodeListProperty.item(pencacahProperty);
            		System.out.println(elementProperty.getAttribute("name"));
            	}
            }
            
            System.out.println(i + " =====================================");
        }
	}
}
