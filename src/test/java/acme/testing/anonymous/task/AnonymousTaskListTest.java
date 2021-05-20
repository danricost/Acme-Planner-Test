package acme.testing.anonymous.task;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import acme.testing.AcmePlannerTest;

public class AnonymousTaskListTest extends AcmePlannerTest{
	
	// Lifecycle management ---------------------------------------------------
	
	// Test cases -------------------------------------------------------------
	
	@ParameterizedTest
	@CsvFileSource(resources = "/anonymous/task/list.csv", encoding = "utf-8", numLinesToSkip = 1)
	@Order(10)	
	public void list(final int recordIndex, final String title, final String initialMoment, final String finalMoment, final String workload, final String description, final String isPublic) {		
//		super.signIn("employer1", "employer1");
		
		super.clickOnMenu("Anonymous", "Tasks");		
		
		super.checkColumnHasValue(recordIndex, 0, title);
		super.checkColumnHasValue(recordIndex, 1, initialMoment);
		super.checkColumnHasValue(recordIndex, 2, finalMoment);
		super.checkColumnHasValue(recordIndex, 3, workload);
		
		super.clickOnListingRecord(recordIndex);
		
		super.checkInputBoxHasValue("title", title);
		super.checkInputBoxHasValue("initialMoment", initialMoment);		
		super.checkInputBoxHasValue("finalMoment", finalMoment);
		super.checkInputBoxHasValue("workload", workload);
		super.checkInputBoxHasValue("description", description);
		super.checkInputBoxHasValue("isPublic", isPublic);
		
//		super.signOut();
	}
	
	// Ancillary methods ------------------------------------------------------

}
