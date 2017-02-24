import java.util.HashMap;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParseLibro extends DefaultHandler {

	private Libro libro;
	private HashMap<String, Libro> mapaL;
	private String valorActual;
	private String isbn;

	@Override
	public void startDocument() throws SAXException {
		mapaL = new HashMap<String, Libro>();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (localName.equals("libro")) {
			isbn = attributes.getValue("isbn");
			libro = new Libro();
			this.libro.setIsbn(isbn);
		}
	}

	
	@Override
	public void characters(char[] ch, int start, int length)throws SAXException
	{
		
		String str = new String(ch,start,length);

		valorActual = str;
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {

		switch (localName) {
		case "titulo":
			libro.setTitulo(valorActual);
			break;
		case "autor":
			libro.setAutor(valorActual);
			break;
		case "anyo":
			libro.setAnyo(valorActual);
			break;
		case "editorial":
			libro.setEditorial(valorActual);
			break;
		case "libro":
			mapaL.put(isbn, libro);
			break;

		default:
			break;
		}
	}
	
	@Override
	public void endDocument() throws SAXException {
		MainLibro.setMapaLibros(mapaL);
	}
}
