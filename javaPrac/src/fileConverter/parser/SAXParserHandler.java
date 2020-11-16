package fileConverter.parser;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
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
	private Field[] fields;
	private ArrayList<E> voList;
	private E vo = null;
	private String text = null;;

	public SAXParserHandler(Class<E> cls) {
		this.cls = cls;
		className = cls.getName();
		setters = getSetters(cls.getDeclaredMethods());
		fields = cls.getDeclaredFields();
//		fieldTypes = getFieldTypes(cls.getDeclaredFields());
		voList = new ArrayList<E>();
	}

	private ArrayList<String> getFieldTypes(Field[] fields) {
		ArrayList<String> list = new ArrayList<String>();
		for(Field field : fields) {
			list.add(field.getType().getName());
		}
		return list;
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
			String tagNameLower = qName.toLowerCase();
			String fieldNameLower = setter.getName().substring(3).toLowerCase();
			if (tagNameLower.equals(fieldNameLower)) {
				
				Class<?> type = getFieldType(tagNameLower);
				
				try {
					setter.invoke(vo, parseStringData(text, type.newInstance()));
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		
	}
	
	private Boolean parseStringData(String data, Boolean bool) {
		return Boolean.parseBoolean(data);
	}
	
	private Integer parseStringData(String data, Integer integer) {
		return Integer.parseInt(data);
	}

	private Double parseStringData(String data, Double doub) {
		return Double.parseDouble(data);
	}
	
	private Object parseStringData(String data, Object obj) {
		return obj;
	}

	private Class<?> getFieldType(String tagNameLower) {
		Class<?> type = null;
		for (Field field : fields) {
			if(tagNameLower.equals(field.getName().toLowerCase())) {
				type = field.getType().getClass();
				break;
			}
		}
		
		return type;
	}
	
	@Override
    public void characters(char[] ch, int start, int length) throws SAXException {
         text = String.copyValueOf(ch, start, length).trim();
    }
	
	@Override
	public List<E> parse(File file) {
		ArrayList<E> voList = new ArrayList<E>();
		SAXParserFactory parserFactory = SAXParserFactory.newInstance();
		SAXParser parser = null;
		try {
			parser = parserFactory.newSAXParser();
		} catch (ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		try {
			parser.parse(file, this);
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		}
		return voList;
	}

	
	
}
