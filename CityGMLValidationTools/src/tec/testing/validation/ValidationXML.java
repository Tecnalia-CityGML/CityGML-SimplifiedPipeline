package tec.testing.validation;

import org.w3c.dom.Document;

import tec.general.General;

public class ValidationXML {

	public ValidationXML() {

	}

	public String PerformValidationXML(String inputCityGML, boolean esEnlace) {
		String resultado = "";
		try {
			General objGeneral = new General();

			Document doc;
			if (esEnlace)
				doc = objGeneral.OpenFileURL(inputCityGML);
			else
				doc = objGeneral.OpenFileString(inputCityGML);

			doc.getDocumentElement().normalize();

			resultado = "<RESULT>XML File is VALID</RESULT>";

		} catch (Exception e) {
			System.out.println("-1-Reason: " + e.getLocalizedMessage());
			System.out.println("-2-Reason: " + e.getMessage());
			resultado = "<ERROR>" + e.getLocalizedMessage() + " - " + e.getMessage() + "</ERROR>";
		}

		return resultado;
	}

	public static void main(String[] args) {

	}

}
