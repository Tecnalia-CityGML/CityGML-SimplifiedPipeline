package tec.testing.validation;

import java.io.File;
import java.io.PrintWriter;
import java.net.URL;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class ValidationXSD {

	public ValidationXSD() {

	}

	public String PerformValidationXSD(String stringCityGML, boolean esEnlace) {
		System.out.println(stringCityGML);
		String resultado = "";
		try {

			if (esEnlace == false) {
				PrintWriter writer = new PrintWriter("building.xml", "UTF-8");
				writer.println(stringCityGML);
				writer.close();
			}

			URL schemaFile = new URL("http://3dcity-test.tecnalia.com/Resources/CityGML_120314/CityGML.xsd");

			Source xmlFile = null;
			if (esEnlace == false)
				xmlFile = new StreamSource(new File("building.xml"));
			else
				xmlFile = new StreamSource(stringCityGML);

			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();

			validator.validate(xmlFile);
			System.out.println(xmlFile.getSystemId() + " is valid");
			resultado = "<RESULT>File is XSD scheme VALID</RESULT>";
		} catch (Exception e) {
			System.out.println("-1-Reason: " + e.getLocalizedMessage());
			System.out.println("-2-Reason: " + e.getMessage());
			e.printStackTrace();
			resultado = "<ERROR>" + e.getLocalizedMessage() + " - " + e.getMessage() + "</ERROR>";
		}

		return resultado;
	}

	public static void main(String[] args) {

	}

}
