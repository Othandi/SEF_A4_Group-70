import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

// tests for Course.createNewAssessment;
// author: Minh Duc
// conditions: 8, 9, 10, 11


public class CourseTest {
    private Course course;

    @BeforeEach
    public void setUpCourse() {
        // fresh course before every test
        course = new Course();
    }


    @Test
    public void groupIsTrueSpaceOnlyNameReturnsFalse(){
        // condtion 8: if group is true, groupingName is not empty.
        // precondition: assessments list is empty (size == 0)
        // test data: group=true, groupingName="   "
        // postcondition: createNewAssessment returns false, assessments list stays empty (size == 0)

        // check precondtions
        assertEquals(0, course.getAssessments().size(), "Precondition failed: Assessments should be empty initially");

        // attempt to create assessments with group==true and groupName = "  " (all other parameters valid)
        boolean createAssessmentsOutcome = course.createNewAssessment(
            "assignment 1",
            "do the assignment",
            25,
            "pdf",
            true, // group == true
            "   ", // group name == "    "
            "08/10/2026 08:00",
            "09/10/2026 08:00",
            "10/10/2026 08:00");

        // check postconditions
        assertEquals(false, createAssessmentsOutcome, "Condition 8 failed: for group=true, empty group name must return false");
        assertEquals(0, course.getAssessments().size(), "Condition 8 failed: empty group names must not be added to list");
    }


    @Test
    public void groupNameNotUniqueWithWhitespaceReturnsFalse(){
        // condtion 9: groupingName must be unique among all assessments.
        // precondition: assessment list has 1 Assignment object, whose group name is "group1"
        // test data: group=true, groupingName="group1 "
        // postcondition: createNewAssessment returns false, length of assessments list stays 1.
        
        // check precondition
        boolean preconditionCreateNewAsmOutcome = course.createNewAssessment(            
            "assignment 2",
            "please do the assignment",
            20,
            "doc",
            true, // group == true
            "group1", // group name == "    "
            "02/10/2026 08:00",
            "03/10/2026 08:00",
            "04/10/2026 08:00"); // valid object

        assertEquals(true, preconditionCreateNewAsmOutcome, "Precondition Failed: initial assignment object should be valid");
        assertEquals(1, course.getAssessments().size(), "Precondition Failed: list should have 1 valid object");
        assertEquals("group1", course.getAssessments().getFirst().getGroupingName(), "Precondition Failed: valid object group name should be \"group1\"");

        // attempt to create assessments with non-unique characters, with whitespace(all other parameters valid)
        boolean createAssessmentsOutcome = course.createNewAssessment(            
            "assignment 3",
            "simple assignment",
            10,
            "doc",
            true, // group == true
            "group1 ", // non-unique group name with whitespace
            "01/10/2026 08:00",
            "02/10/2026 08:00",
            "03/10/2026 08:00"); // valid object
        
        // check postcondtion
        assertEquals(false, createAssessmentsOutcome, "Condition 9 Failed: non-unique group name should return false");
        assertEquals(1, course.getAssessments().size(), "Condition 9 Failed: assessment list size should stay as 1 after attempt to add invalid object");
    }
    

    @Test
    public void invalidDateTimesCasesReturnsFalse(){
        // condtion 10: String arguements representing date times should use the specified format and is legal.  
        // precondition: initial assessments list is empty (size == 0)
        // test data:
            // set 1: wrong format but legal date/time, publish is valid.
            // set 2: right format but illegal date/time (leap year/ 24:00), due date is valid.
        // postcondition: assessments list stays empty, both sets returned failed.

        // check precondition:
        assertEquals(0, course.getAssessments().size(), "Precondtion failed: Initial List should be empty!");
        
        // attempt set 1
        boolean set1CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 2b",
            "please!! do the assignment",
            40,
            "pdf",
            false,
            "",
            "01/10/2026 08:00", // publish date format is correct
            "03-10-2026 08:00", // date sep: "-"
            "04/10/2026 08:00PM"); // 12hr format

        // attempt set 2
        boolean set2CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 2c",
            "life shattering assignment",
            50,
            "pdf",
            false,
            "",
            "29/02/2026 20:00", // right format, leap year
            "03/10/2026 08:00", // due date is valid
            "07/10/2026 24:00"); // right format, impossible time.

        // check post conditions
        assertEquals(false, set1CreateAssessmentsOutcome, "Condition 10 failed: Wrongly formatted legal dates/times should return false");
        assertEquals(false, set2CreateAssessmentsOutcome, "Condition 10 failed: Illegal dates/times should return false");
        assertEquals(0, course.getAssessments().size(), "Precondition failed: list should be empty after 2 add attempt with invalid date time");
    }
}
