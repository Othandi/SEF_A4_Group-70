package course;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for Course.createNewAssessment().
 * Section completed by: Jose
 * Testing Conditions 1, 2, 3, and 7
 */

public class CourseTest{

    private Course course;

    @BeforeEach
    public void setUp() {
        // Initialise a fresh Course object before each unit test runs
        course = new Course();
    }

    /**
     * Test Case for Condition : Assessment name must be unique to the course.
     * Boundary Test Data: Adding an assessment with the exact same name
     * as an assessment already stored in the course's assessments list.
     */

    @Test
    public void testCondition1_DuplicateAssessmentName_ReturnsFalse() {
        // Precondition step: Add a valid first assessment to the course
        boolean setupResult = course.createNewAssessment(
            "assessment 1",
            "complete the software architecture report",
            25,
            "pdf",
            false,
            "",
            "20/10/2026 09:00",
            "31/10/2026 23:59",
            "01/11/2026 23:59"
        );

        // Precondition assertions: Verify that the first assessment was added successfully
        assertTrue(setupResult, "Precondition failed: Initial valid assessment should return true.");
        assertEquals(1, course.getAssessments().size(), "Precondition failed: Assessments list should containn 1 item.");
        assertEquals("assessment 1", course.getAssessments().get(0).getName());

        // Action: Attempt to create a second assessment with the duplicate name "Assessment 1"
        boolean testResult = course.createNewAssessment(
            "assessment 1", // Duplicate name
            "Complete the software architecture report",
            25,
            "pdf",
            false,
            "",
            "20/10/2026 09:00",
            "31/10/2026 23:59",
            "01/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false, list size remains 1, and original attributes are untouched
        assertFalse(testResult, "Condition 1 failed: Duplicate assessment name should return false.");
        assertEquals(1, course.getAssessments().size(), "Postcondition failed: Duplicate assessment must not be added to list.");
        assertEquals(25, course.getAssessments().get(0).getPoints(), "Postcondition failed: Existing assessment attributes must not be modified.");
    }

    /**
     * Test Case for Condition 2: Assessment name cannot be blank.
     * Boundary Test Data: Empty string "" for assessment name.
     */
    @Test
    public void testCondition2_BlankAssessmentName_ReturnsFalse() {
        // Precondition assertion: Verify the assessment list is empty prior to test execution
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments list should be empty initially.");

        // Action: Attempt to create an assessment with a blank name ""
        boolean testResult = course.createNewAssessment(
            "", //Blank name
            "implement the java validation function",
            20,
            "pdf",
            false,
            "",
            "20/10/2026 09:00",
            "31/10/2026 23:59",
            "01/11/2026 23:59"
        );

        assertFalse(testResult, "Condition 2 failed: Blank assessment name should return false.");
        assertTrue(course.getAssessments().isEmpty(), "Postcondition failed: No assessment object should be added when name is blank.");
    }

    /**
     * Test Case for Condition 3: Assessment description must have at least 3 words.
     * Boundary Test Data: A description with 2 words ("two words"), which is right on the invalid boundary (< 3).
     */
    @Test
    public void testCondition3_DescriptionUnderThreeWords_ReturnsFalse() {
        // Precondition assertion: Verify the assessments list is empty before running the test
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments list should be empty.");

        //Action: Attempt to create an assessment with a 2-word description ("two words")
        boolean testResult = course.createNewAssessment(
            "assessment 2",
            "two words",
            20,
            "doc",
            false,
            "",
            "21/10/2026 09:00",
            "01/11/2026 23:59",
            "02/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false and assessments list remains empty
        assertFalse(testResult, "Condition 3 failed: Description with fewer than 3 words should return false.");
        assertEquals(0, course.getAssessments().size(), "Postcondition failed: Assessment must not be stored when description has < 3 words.");
    }

    /**
     * Test Case for Condition 7: Submission type must be one of pdf, zip, doc
     * Boundary Test Data: An invalid file extension string ("txt") while all other parameters are valid
     */
    @Test
    public void testCondition7_InvalidSubmissionType_ReturnsFalse() {
        // Precondition assertion: Verify the assessments list is empty before running the test
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessment list should be empty initially.");

        // Action: Attempt to create an assessment with an invalid submissionType ("txt")
        boolean testResult = course.createNewAssessment(
            "assessment 3",
            "submit your final project code",
            40,
            "txt",
            false,
            "",
            "22/10/2026 09:00",
            "02/11/2026 23:59",
            "03/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false and no object is added to the assessments list
        assertFalse(testResult, "Condition 7 failed: Submission type 'txt' is not in(pdf, zip, doc) and should return false.");
        assertEquals(0, course.getAssessments().size(), "Postcondition failed: Assessment must not be stored when submission type is invalid.");
    }
}