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
     * Test Case for Condition 1: Assessment name must be unique to the course.
     * Boundary Test Data: Adding an assessment with the exact same name
     * as an assessment already stored in the course's assessments list.
     */

    @Test
    public void testCondition1_DuplicateAssessmentName_ReturnsFalse() {
        // Precondition step: Add a valid first assessment to the course (25 points, "pdf")
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
        assertEquals(1, course.getAssessments().size(), "Precondition failed: Assessments list should contain 1 item.");
        assertEquals("assessment 1", course.getAssessments().get(0).getName());

        // Action: Attempt to create a second assessment with the duplicate name "assessment 1"
        // but DIFFERENT description, points (30), and submissionType ("zip")
        boolean testResult = course.createNewAssessment(
            "assessment 1", // Duplicate name
            "write unit tests for course class",
            30,
            "zip",
            false,
            "",
            "20/10/2026 09:00",
            "31/10/2026 23:59",
            "01/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false, list size remains 1, and original attributes (25 and "pdf") are untouched
        assertFalse(testResult, "Condition 1 failed: Duplicate assessment name should return false.");
        assertEquals(1, course.getAssessments().size(), "Postcondition failed: Duplicate assessment must not be added to list.");
        assertEquals(25, course.getAssessments().get(0).getPoints(), "Postcondition failed: Existing assessment points must remain 25.");
        assertEquals("pdf", course.getAssessments().get(0).getSubmissionType(), "Postcondition failed: Existing submission type must remain 'pdf'.");
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

    // ============================================================
    // Section completed by: Jasmine
    // Testing Conditions 4, 5, 6 and Case 12 (All Valid)
    // ============================================================

    /**
     * Test Case for Condition 4: Assessment points must be greater than 0.
     * Boundary Test Data: points = 0, the first invalid value at the lower boundary.
     */
    @Test
    public void testCondition4_ZeroPoints_ReturnsFalse() {
        // Precondition assertion: Verify the assessments list is empty before running the test
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments list should be empty initially.");

        // Action: Attempt to create an assessment worth 0 points
        boolean testResult = course.createNewAssessment(
            "assessment 4",
            "write a short reflection essay",
            0, // Boundary: 0 points
            "pdf",
            false,
            "",
            "23/10/2026 09:00",
            "03/11/2026 23:59",
            "04/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false and no object is added to the list
        assertFalse(testResult, "Condition 4 failed: Assessment worth 0 points should return false.");
        assertEquals(0, course.getAssessments().size(), "Postcondition failed: Assessment must not be stored when points are 0.");
    }

    /**
     * Test Case for Condition 5: A single assessment cannot be worth more than 50 points.
     * Boundary Test Data: points = 51, the first invalid value just above the upper boundary.
     */
    @Test
    public void testCondition5_PointsOver50_ReturnsFalse() {
        // Precondition assertion: Verify the assessments list is empty before running the test
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments list should be empty initially.");

        // Action: Attempt to create an assessment worth 51 points
        boolean testResult = course.createNewAssessment(
            "assessment 5",
            "design the database schema diagram",
            51, // Boundary: 51 points
            "zip",
            false,
            "",
            "24/10/2026 09:00",
            "04/11/2026 23:59",
            "05/11/2026 23:59"
        );

        // Postcondition assertions: Verify function returns false and no object is added to the list
        assertFalse(testResult, "Condition 5 failed: Assessment worth 51 points should return false.");
        assertEquals(0, course.getAssessments().size(), "Postcondition failed: Assessment must not be stored when points exceed 50.");
    }

    /**
     * Test Case for Condition 6: Total points across all assessments cannot exceed 100.
     * Boundary Test Data: Existing assessments total 60 points, new assessment worth 41 points
     * would make the total 101, the first invalid value just above the boundary.
     */
    @Test
    public void testCondition6_TotalPointsOver100_ReturnsFalse() {
        // Precondition step: Add two valid assessments worth 30 points each (total = 60)
        boolean setupResult1 = course.createNewAssessment(
            "assessment 6a",
            "complete the requirements analysis document",
            30,
            "pdf",
            false,
            "",
            "25/10/2026 09:00",
            "05/11/2026 23:59",
            "06/11/2026 23:59"
        );
        boolean setupResult2 = course.createNewAssessment(
            "assessment 6b",
            "present the system design slides",
            30,
            "doc",
            false,
            "",
            "26/10/2026 09:00",
            "06/11/2026 23:59",
            "07/11/2026 23:59"
        );

        // Calculate the current total points in the course
        int totalBefore = 0;
        for (Assessment existingAssessment : course.getAssessments()) {
            totalBefore += existingAssessment.getPoints();
        }

        // Precondition assertions: Verify both setup assessments were added and the total is 60
        assertTrue(setupResult1, "Precondition failed: First setup assessment should return true.");
        assertTrue(setupResult2, "Precondition failed: Second setup assessment should return true.");
        assertEquals(2, course.getAssessments().size(), "Precondition failed: Assessments list should contain 2 items.");
        assertEquals(60, totalBefore, "Precondition failed: Existing assessments should total 60 points.");

        // Action: Attempt to add an assessment worth 41 points (60 + 41 = 101)
        boolean testResult = course.createNewAssessment(
            "assessment 6c",
            "write the final testing report",
            41, // Boundary: total would become 101
            "pdf",
            false,
            "",
            "27/10/2026 09:00",
            "07/11/2026 23:59",
            "08/11/2026 23:59"
        );

        // Recalculate the total after the attempt
        int totalAfter = 0;
        for (Assessment existingAssessment : course.getAssessments()) {
            totalAfter += existingAssessment.getPoints();
        }

        // Postcondition assertions: Verify function returns false, list size stays 2, and total stays 60
        assertFalse(testResult, "Condition 6 failed: Total points of 101 should return false.");
        assertEquals(2, course.getAssessments().size(), "Postcondition failed: Assessment must not be added when total exceeds 100.");
        assertEquals(60, totalAfter, "Postcondition failed: Total points must remain 60.");
    }

    /**
     * Test Case 12: All inputs are valid.
     * Valid Test Data (3 data sets):
     *   Data set 1: points = 1 (lowest valid value for Condition 4)
     *   Data set 2: points = 50 (highest valid value for Condition 5)
     *   Data set 3: points = 49, bringing the total to exactly 100 (highest valid total for Condition 6)
     */
    @Test
    public void testCase12_AllValidInputs_ReturnsTrue() {
        // Precondition assertion: Verify the assessments list is empty before running the test
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments list should be empty initially.");

        // ----- Data set 1: points = 1 -----
        boolean result1 = course.createNewAssessment(
            "quiz 1",
            "online multiple choice quiz",
            1,
            "pdf",
            false,
            "",
            "20/10/2026 09:00",
            "27/10/2026 23:59",
            "28/10/2026 23:59"
        );

        // Postcondition assertions: Verify true is returned, object is added, and all attributes match
        assertTrue(result1, "Case 12 failed: Valid assessment worth 1 point should return true.");
        assertEquals(1, course.getAssessments().size(), "Postcondition failed: Assessments list should contain 1 item.");
        Assessment a1 = course.getAssessments().get(0);
        assertEquals("quiz 1", a1.getName());
        assertEquals("online multiple choice quiz", a1.getDescription());
        assertEquals(1, a1.getPoints());
        assertEquals("pdf", a1.getSubmissionType());
        assertFalse(a1.isGroup());
        assertEquals("", a1.getGroupingName());
        assertEquals("20/10/2026 09:00", a1.getPublishDateTime());
        assertEquals("27/10/2026 23:59", a1.getDueDateTime());
        assertEquals("28/10/2026 23:59", a1.getHideDateTime());

        // ----- Data set 2: points = 50 -----
        boolean result2 = course.createNewAssessment(
            "assignment 1",
            "build the java validation function",
            50,
            "zip",
            false,
            "",
            "21/10/2026 09:00",
            "04/11/2026 23:59",
            "05/11/2026 23:59"
        );

        // Postcondition assertions: Verify true is returned, object is added, and all attributes match
        assertTrue(result2, "Case 12 failed: Valid assessment worth 50 points should return true.");
        assertEquals(2, course.getAssessments().size(), "Postcondition failed: Assessments list should contain 2 items.");
        Assessment a2 = course.getAssessments().get(1);
        assertEquals("assignment 1", a2.getName());
        assertEquals("build the java validation function", a2.getDescription());
        assertEquals(50, a2.getPoints());
        assertEquals("zip", a2.getSubmissionType());
        assertFalse(a2.isGroup());
        assertEquals("", a2.getGroupingName());
        assertEquals("21/10/2026 09:00", a2.getPublishDateTime());
        assertEquals("04/11/2026 23:59", a2.getDueDateTime());
        assertEquals("05/11/2026 23:59", a2.getHideDateTime());

        // ----- Data set 3: points = 49, total becomes exactly 100 -----
        boolean result3 = course.createNewAssessment(
            "final report",
            "write the final project report",
            49,
            "doc",
            false,
            "",
            "22/10/2026 09:00",
            "11/11/2026 23:59",
            "12/11/2026 23:59"
        );

        // Calculate the total points after all three assessments
        int total = 0;
        for (Assessment existingAssessment : course.getAssessments()) {
            total += existingAssessment.getPoints();
        }

        // Postcondition assertions: Verify true is returned, object is added, total is 100, and all attributes match
        assertTrue(result3, "Case 12 failed: Valid assessment bringing total to exactly 100 should return true.");
        assertEquals(3, course.getAssessments().size(), "Postcondition failed: Assessments list should contain 3 items.");
        assertEquals(100, total, "Postcondition failed: Total points should be exactly 100.");
        Assessment a3 = course.getAssessments().get(2);
        assertEquals("final report", a3.getName());
        assertEquals("write the final project report", a3.getDescription());
        assertEquals(49, a3.getPoints());
        assertEquals("doc", a3.getSubmissionType());
        assertFalse(a3.isGroup());
        assertEquals("", a3.getGroupingName());
        assertEquals("22/10/2026 09:00", a3.getPublishDateTime());
        assertEquals("11/11/2026 23:59", a3.getDueDateTime());
        assertEquals("12/11/2026 23:59", a3.getHideDateTime());
    }
}