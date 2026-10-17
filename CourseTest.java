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
        // postcondition: createNewAssessment returned false, assessments list stayed empty (size == 0)

        // check precondtions
        assertEquals(0, course.getAssessments().size(), "Precondition 8 failed: Assessments should be empty initially");

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
        // postcondition: createNewAssessment returned false, length of assessments list stayed 1.
        
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

        assertEquals(true, preconditionCreateNewAsmOutcome, "Precondition 9 Failed: initial assignment object should be valid");
        assertEquals(1, course.getAssessments().size(), "Precondition 9 Failed: list should have 1 valid object");
        assertEquals("group1", course.getAssessments().getFirst().getGroupingName(), "Precondition 9 Failed: valid object group name should be \"group1\"");

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
            // set 1: wrong format but legal date time (due date).
            // set 2: right format but illegal date (leap year), (publish date)
            // set 3: right format but impossible time (24:00) (hide date)
        // postcondition: assessments list stayed empty, both sets returned false.

        // check precondition:
        assertEquals(0, course.getAssessments().size(), "Precondtion 10 failed: Initial List should be empty!");
        
        // attempt set 1
        boolean set1CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 2b",
            "please!! do the assignment",
            40,
            "pdf",
            false,
            "",
            "01/10/2026 08:00", // publish date format is valid
            "03-10-2026 08:00", // date sep: "-"
            "04/10/2026 08:00"); // hide date is valid

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
            "07/10/2026 23:00"); // hideDateTime is valid

        // attempt set 3
        boolean set3CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 9k",
            "bloodline shattering assignment",
            40,
            "zip",
            false,
            "",
            "17/12/2025 20:00", // publish date is valid
            "18/12/2025 21:00", // due date is valid
            "19/12/2025 24:00"); // impossible time

        // check post conditions
        assertEquals(false, set1CreateAssessmentsOutcome, "Condition 10 failed: Wrongly formatted legal dates/times should return false");
        assertEquals(false, set2CreateAssessmentsOutcome, "Condition 10 failed: Illegal dates/times should return false");
        assertEquals(false, set3CreateAssessmentsOutcome, "Condition 10 failed: Impossible times should return false");
        assertEquals(0, course.getAssessments().size(), "Postcondtion 10 failed: list should be empty after 2 add attempts with invalid dates/times");
    }


    @Test 
    public void publishDateDueDateHideDateInvalidSequenceReturnFalse(){
        // condition 11: publish time is before due time, due time is before hide time
        // precondtion: initial assessments list is empty (size == 0)
        // test data:
            // set 1: due is before publish
            // set 2: hide is before due
        // postcondition: assessments list stayed empty, both set returned false

        // check precondition
        assertEquals(0, course.getAssessments().size(), "Precondtion 11 failed: initial assessments list should be empty");

        // attempt set 1
        boolean set1CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 20b",
            "build a dyson sphere",
            15,
            "zip",
            false,
            "",
            "01/10/2026 08:00", 
            "01/10/2026 07:59", // due 1 minute before publish date
            "01/10/2026 08:00");
        
        // attempt set 2
            boolean set2CreateAssessmentsOutcome = course.createNewAssessment(
            "assignment 21b",
            "build TWO dyson spheres",
            20,
            "zip",
            false,
            "",
            "05/10/2026 08:00", 
            "06/10/2026 08:00", 
            "06/10/2026 07:59"); // hide is 1 minite before due

        // check postconditions
        assertEquals(false, set1CreateAssessmentsOutcome, "Condition 11 failed: due time before publish time should return false");
        assertEquals(false, set2CreateAssessmentsOutcome, "Condition 11 failed: hide time before due time should return false");
        assertEquals(0, course.getAssessments().size(), "Condition 11 failed: assessments list should be empty after 2 add attempts" +
                                                        " with impossible date time sequence");
    }
}
