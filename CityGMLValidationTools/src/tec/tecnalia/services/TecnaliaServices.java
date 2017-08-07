package tec.tecnalia.services;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import tec.testing.validation.ValidationXML;
import tec.testing.validation.ValidationXSD;

@Path("Servicios")
public class TecnaliaServices {

	@Context
	ServletContext context;

	public TecnaliaServices() {
		System.out.println("Constructor " + context); // null here
	}

	@POST
	@Path("/CityGMLValidateXML")
	@Produces(MediaType.TEXT_PLAIN)
	public String CityGMLValidateXML(String input, @Context HttpServletRequest request) {
		System.out.println("-----------CityGMLValidateXML---------");
		// 0 - CityGML

		String trozoAEliminar = input.split("&")[0].split("=")[0];
		String todoCityGML = input.split("&")[0];
		String stringCityGML = todoCityGML.replaceAll(trozoAEliminar + "=", "");

		// String stringCityGML = input.split("&")[0].split("=")[1];

		stringCityGML = ReemplazarCaracteresRaros(stringCityGML);

		ValidationXML objValidationXML = new ValidationXML();
		String resultado = objValidationXML.PerformValidationXML(stringCityGML, false);
		return resultado;
	}

	@POST
	@Path("/CityGMLValidateXSD")
	@Produces(MediaType.TEXT_PLAIN)
	public String CityGMLValidateXSD(String input, @Context HttpServletRequest request) {
		System.out.println("-----------CityGMLValidateXSD---------");
		// 0 - CityGML

		String trozoAEliminar = input.split("&")[0].split("=")[0];
		String todoCityGML = input.split("&")[0];
		String stringCityGML = todoCityGML.replaceAll(trozoAEliminar + "=", "");

		// String stringCityGML = input.split("&")[0].split("=")[1];

		stringCityGML = ReemplazarCaracteresRaros(stringCityGML);

		ValidationXSD objValidationXSD = new ValidationXSD();
		String resultado = objValidationXSD.PerformValidationXSD(stringCityGML, false);
		return resultado;
	}

	@POST
	@Path("/CityGMLValidateXSDFromURI")
	@Produces(MediaType.TEXT_PLAIN)
	public String CityGMLValidateXSDFromURI(String input, @Context HttpServletRequest request) {
		System.out.println("-----------CityGMLValidateXSDFromURI---------");

		// 0 - URI
		String fileURI = input.split("&")[0].split("=")[1];
		fileURI = ReemplazarCaracteresRaros(fileURI);

		ValidationXSD objValidationXSD = new ValidationXSD();
		String resultado = objValidationXSD.PerformValidationXSD(fileURI, true);
		return resultado;
	}

	private String ReemplazarCaracteresRaros(String input) {
		String output = input;
		output = output.replaceAll("%3A", ":");
		output = output.replaceAll("%2F", "/");
		output = output.replaceAll("%20", " ");

		// Problema slash
		output = output.replaceAll("%7c", "|");

		output = output.replaceAll("%3C", "<");
		output = output.replaceAll("%3F", "?");
		output = output.replaceAll("%3E", ">");
		output = output.replaceAll("%3D", "=");
		output = output.replaceAll("%27", "'");
		output = output.replaceAll("%23", "#");

		output = output.replaceAll("\\+", " ");

		// Salto de linea?
		output = output.replaceAll("%0D%0A", "");

		return output;
	}
}
