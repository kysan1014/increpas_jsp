package fileConverter.parser;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXParserHandler<E> extends DefaultHandler implements Parsable<E> {

	private Class<E> cls;
	private String className;
	private ArrayList<Method> setters;
	private ArrayList<E> voList;
	private E vo = null;
	private String text = null;;

	public SAXParserHandler(Class<E> cls) {
		this.cls = cls;
		className = cls.getName();
		setters = getSetters(cls.getMethods());
		voList = new ArrayList<E>();
	}

	private ArrayList<Method> getSetters(Method[] methods) {
		ArrayList<Method> list = new ArrayList<Method>();
		for (Method m : methods) {
			if (m.getName().substring(0, 3).equals("set")) {
				setters.add(m);
			}
		}
		return list;
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

		if (qName.equals(className)) {
			try {
				vo = cls.newInstance();
				for (Method m : setters) {
					if (m.getName().equals("setId")) {
						try {
							m.invoke(vo, attributes.getValue("ID"));
						} catch (IllegalArgumentException | InvocationTargetException e) {
							e.printStackTrace();
						}
						break;
					}
				}
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		// TODO: text 데이터를 VO 멤버에 맞는 타입으로 캐스팅 해야 함 
		
		if (qName.equals(cls.getName())) {
			voList.add(vo);
			return;
		}
		
		for (Method setter : setters) {
			if (qName.toLowerCase().equals(setter.getName().substring(3).toLowerCase())) {
				try {
					setter.invoke(vo, text);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
	}
	

	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
         text = String.copyValueOf(ch, start, length).trim();
    }
	
	@Override
	public List<E> parse(File file) {
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return null;
	}

}
