package fileConverter.parser;

import java.io.File;
import java.util.List;

public interface Parsable<E> {
	
	public abstract List<E> parse(File file);
	
}
