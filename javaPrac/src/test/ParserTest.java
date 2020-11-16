package test;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import fileConverter.parser.XMLFileParser;

public class ParserTest {

	private String pathXml = "D:\\git\\jsp\\javaPrac\\bin\\resources\\xml\\exmployee.xml";
	
	public String getPathXml() {
		return pathXml;
	}
	
	public ParserTest() {
//		String pathThis = this.getClass().getResource("").getPath();
//		pathXml = pathThis + "../resources/xml/exmployee.xml";
	}
	
	public List<?> getParsedData(XMLFileParser parser, File file) throws ParserConfigurationException, SAXException, IOException {
		return parser.parse(file);
	}
	
	public static void main(String[] args) {
		
		ParserTest test = new ParserTest();
		XMLFileParser parser = new XMLFileParser();
		try {
			test.getParsedData(parser, new File(test.getPathXml()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
