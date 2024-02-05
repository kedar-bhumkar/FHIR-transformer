
import org.hl7.fhir.r4.model.*;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.parser.IParser;
import ca.uhn.fhir.parser.StrictErrorHandler;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class Welcome {

	public static void main(String[] args) {
		
		//basicCheck();
		
		parseJSON();
	
	}

	
	
	
	private static void parseJSON() {
		try {

			FhirContext ctxR4 = FhirContext.forR4();
			IParser parser = ctxR4.newJsonParser();
			
			/*String jsonContent = readJSON("C:\Users\kedar\eclipse-workspace\client\FHIR-data\\patient.json");
			Patient patient = parser.parseResource(Patient.class, jsonContent);
			System.out.println(patient.getBirthDate());*/
			
			
			String jsonContent = readJSON("C:\\Users\\kedar\\eclipse-workspace\\client\\FHIR-data\\allergy.json");
			AllergyIntolerance allergy = parser.parseResource(AllergyIntolerance.class, jsonContent);
			System.out.println(allergy.getOnset());
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static String readJSON(String fileName) throws IOException {
		String jsonContent = new String(Files.readAllBytes(Paths.get(fileName)));
		System.out.println("jsonContent -  " + jsonContent);
		
		return jsonContent;
	}


	private static void basicCheck() {
		
		System.out.println("Haloa means holiday of ...");
		FhirContext ctxR4 = FhirContext.forR4();
		Patient patient = new Patient();
		patient.addIdentifier().setSystem("http://example.com/fictitious-mrns").setValue("MRN001");
		patient.addName()
		 		.setUse(HumanName.NameUse.OFFICIAL)
		      .setFamily("Tester")
		      .addGiven("John")
		      .addGiven("Q");

		String encoded = ctxR4.newJsonParser().setPrettyPrint(true).encodeResourceToString(patient);
		System.out.println("Encoded " +  encoded);
		
		// Instantiate a new JSON parser
		IParser parser = ctxR4.newJsonParser();

		// Serialize it
		String serialized = parser.encodeResourceToString(patient);
		System.out.println(serialized);
		
	}
	
}
