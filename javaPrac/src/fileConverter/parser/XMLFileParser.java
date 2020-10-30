package fileConverter.parser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fileConverter.dto.Employee;

public class XMLFileParser {

	public List<Employee> parse(File xml) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(xml);

		List<Employee> list = new ArrayList<Employee>();
		System.out.println(list);
		NodeList nodeList = document.getDocumentElement().getChildNodes();

		for (int i = 0; i < nodeList.getLength(); i++) {
			Node node = nodeList.item(i);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element elem = (Element) node;

				String id = node.getAttributes().getNamedItem("ID").getNodeValue();
				
				String firstname = elem.getElementsByTagName("Firstname").item(0).getChildNodes().item(0)
						.getNodeValue();
				String lastname = elem.getElementsByTagName("Lastname").item(0).getChildNodes().item(0).getNodeValue();
				Integer age = Integer
						.parseInt(elem.getElementsByTagName("Age").item(0).getChildNodes().item(0).getNodeValue());
				Double salary = Double.parseDouble(
						elem.getElementsByTagName("Salary").item(0).getChildNodes().item(0).getNodeValue());
				list.add(new Employee(id, firstname, lastname, age, salary));
			}
			
		}
		
		return list;
	}

}
