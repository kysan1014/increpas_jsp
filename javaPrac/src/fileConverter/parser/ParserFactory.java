package fileConverter.parser;

public class ParserFactory<T> {

	public final int SAX_PARSER = 1000;

	private int typeCode;
	private Class<T> cls;
	
	public ParserFactory(int typeCode, Class<T> cls) {
		this.typeCode = typeCode;
		this.cls = cls;
	}
	
	public Parsable<T> newInstance() {
		Parsable<T> parser = null;
		if (typeCode == SAX_PARSER) {
			parser = new SAXParserHandler<T>(cls);
		}
		return parser;
	}
	
}
