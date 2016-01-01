package org.erp.distribution.util;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReportJdbcConfigHelper {

	public Connection getConnection(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String url = "jdbc:mysql://localhost/erp_db";
		String user = "root";																																																																																																																																																																																																																																																																																																																				
		String pass = "hacker";
		//TIMPA DAN AMBIL DARI PERSISTENCE
//		try{
//			pass = getPersistenceXmlPassword();
//		} catch(Exception ex){}	
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return con;
	}
	
	
	public String getPersistenceXmlPassword()  throws ParserConfigurationException, IOException, SAXException{
        // lokasi file XML
		String passwd = "";
        File file = new File("persistence.xml");
 
       	DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        
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
            		if (elementProperty.getAttribute("name").equals("javax.persistence.jdbc.password")){
            			passwd = elementProperty.getAttribute("value");
            		}
            	}
            }
            
        }
        
        return passwd;
		
	}
}
