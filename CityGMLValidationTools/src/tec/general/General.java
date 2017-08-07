package tec.general;

import java.io.StringReader;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class General {

	private String errores = "";

	public General() {
	}

	public Document OpenFileString(String stringCityGML) {
		Document resultado = null;
		try {

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			// System.out.println(stringCityGML);
			InputSource is = new InputSource(new StringReader(stringCityGML));

			Document doc = builder.parse(is);
			doc.getDocumentElement().normalize();

			resultado = doc;
		} catch (Exception e) {
			System.out.println("-1-Reason: " + e.getLocalizedMessage());
			System.out.println("-2-Reason: " + e.getMessage());
			errores = e.getLocalizedMessage() + " - " + e.getMessage();
		}
		return resultado;
	}

	public Document OpenFileURL(String urlString) {
		Document resultado = null;
		try {
			URL url = new URL(urlString);
			URLConnection conn = url.openConnection();

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(conn.getInputStream());
			resultado = doc;
		} catch (Exception e) {
			System.out.println("-1-Reason: " + e.getLocalizedMessage());
			System.out.println("-2-Reason: " + e.getMessage());
			errores = e.getLocalizedMessage() + " - " + e.getMessage();
		}
		return resultado;
	}

	public String getErrores() {
		return errores;
	}

	public void setErrores(String errores) {
		this.errores = errores;
	}

}
