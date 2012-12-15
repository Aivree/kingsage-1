package pl.piomin.kingsage.machine.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;

public class Parser {

	private static final String URL = "http://s17.kingsage.pl/";
	
	public static String inputToString(InputStream stream) throws IOException {
		StringBuilder buffer = new StringBuilder("<?xml version=\"1.0\"?>");
		BufferedReader rd = new BufferedReader(new InputStreamReader(stream));
		String line = "";
		while ((line = rd.readLine()) != null) 
			buffer.append(line);
		return buffer.toString().replaceAll("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">", "");
	}
	
	public static String getLink(String content, String className, int index) {
		Source source = new Source(content);
		List<Element> tags = source.getAllElementsByClass(className);
		Element parent = tags.get(index);
		Element link = parent.getFirstElement(HTMLElementName.A);
		return URL + link.getAttributeValue("href");
	}
	
	public static void printErrors(String content, String className) {
		Source source = new Source(content);
		List<Element> tags = source.getAllElementsByClass(className);
		for (Element element : tags) {
			System.out.println(element.getContent().toString());
		}
	}
	
}
