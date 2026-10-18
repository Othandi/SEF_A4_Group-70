/** TODO: initilise all the attributes*/

package course;

import java.util.ArrayList;
import java.util.List;

// validation specific imports
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

class Assessment {
   private String name;
   private String description;
   private int points;
   private String submissionType;
   private boolean group;
   private String groupingName;
   private String publishDateTime;
   private String dueDateTime; 
   private String hideDateTime;

   /** Constructor initializes all the attributes of the Assessment class */
   public Assessment(String name, String description, int points, 
                     String submissionType, boolean group, String groupingName, 
                     String publishDateTime, String dueDateTime, String hideDateTime) {
      this.name = name;
      this.description = description;
      this.points = points;
      this.submissionType = submissionType;
      this.group = group;
      this.groupingName = groupingName;
      this.publishDateTime = publishDateTime;
      this.dueDateTime = dueDateTime;
      this.hideDateTime = hideDateTime;
   }

   public String getName() {
      return name;
   }

   public String getDescription() {
      return description;
   }

   public int getPoints() {
      return points;
   }

   public String getSubmissionType() {
      return submissionType;
   }

   public boolean isGroup() {
      return group;
   }

   public String getGroupingName() {
      return groupingName;
   }

   public String getPublishDateTime() {
      return publishDateTime;
   }

   public String getDueDateTime() {
      return dueDateTime;
   }

   public String getHideDateTime() {
      return hideDateTime;
   }
}

public class Course {
   // Initialise assessments list so we can store valid Assessment objects in it.
   private List<Assessment> assessments = new ArrayList<>();
   
   // Getter for assessments list to verify conditions for JUnit tests.
   public List<Assessment> getAssessments() {
      return assessments;
   }

   public boolean createNewAssessment(String name, String description, int points, 
                                      String submissionType, boolean group, String groupingName, 
                                      String publishDateTime, String dueDateTime, String hideDateTime) {
      if (!validateNameDescAndType(name, description, submissionType)){ // Jose's validation check for Conditions 1, 2, 3, and 7
         return false;
      }

      // Jasmine's validation check for Conditions 4, 5, and 6
      if (!isValidPoints(points)) {
         return false;
      }

      // Minh Duc's validation check for condition 8, 9, 10, 11
      if (!validateGroupNameDateTimes(group, groupingName, publishDateTime, dueDateTime, hideDateTime))
         return false; 

      // If all conditions pass, create the Assessment object, store it in the list, and return true
      Assessment newAssessment = new Assessment(name, description, points, submissionType, group,
      groupingName, publishDateTime, dueDateTime, hideDateTime);

      assessments.add(newAssessment);
      return true;
   }                            

   /**
    * Jose's Validation for Conditions 1, 2, 3, and 7.
    * Validates assessment name uniqueness, non-blank name, minimum 3-word description,
    * and allowed submission file types ("pdf", "zip", "doc"(.))
    */
   private boolean validateNameDescAndType(String name, String description, String submissionType) {
      // Condition 2: The assessment name cannot be blank
      if (name == null || name.trim().isEmpty()){
         return false;
      }

      // Condition 1: The assessment name must be unique to the course
      for (Assessment existingAssessment : assessments) {
         if (existingAssessment.getName().equals(name)) {
            return false;
         }
      }

      // Condition 3: The assessment description must have at least 3 words
      if (description == null || description.trim().isEmpty()) {
         return false;
      }

      // Split description by whitespace to count the exact number of words
      String[] words = description.trim().split("\\s+");
      if (words.length < 3) {
         return false;
      }

      // Condition 7: Submission type must be one of ("pdf, zip ,doc)
      if (submissionType == null || (!submissionType.equals("pdf") && !submissionType.equals("zip") && !submissionType.equals("doc"))) {
         return false;
      }
     
      return true;
   }

   /**
    * Jasmine's Validation for Conditions 4, 5, and 6.
    * Checks that points are above 0, no more than 50,
    * and that the course total stays at or under 100.
    */
   private boolean isValidPoints(int points) {
      // Condition 4: points must be greater than 0
      if (points <= 0) {
         return false;
      }

      // Condition 5: a single assessment cannot be worth more than 50
      if (points > 50) {
         return false;
      }

      // Condition 6: add up the points of the assessments already in the course
      int currentTotal = 0;
      for (Assessment existingAssessment : assessments) {
         currentTotal += existingAssessment.getPoints();
      }

      // The new total cannot go over 100
      if (currentTotal + points > 100) {
         return false;
      }

      return true;
   }

   /**
    * Minh Duc's Validation for Conditions 8, 9, 10, 11.
    */
     private boolean validateGroupNameDateTimes(boolean group, String groupingName,
                     String publishDateTime, String dueDateTime, String hideDateTime) {
      
      // condtion 8: if group is true, groupingName is not empty.
      if (group && (groupingName == null || groupingName.isEmpty() || groupingName == "" || groupingName.isBlank())) {
      return false;
      }

      // condtion 9: groupingName must be unique among all group assessments.
      for (Assessment existingAssessment : assessments) {
         if (existingAssessment.isGroup() && 
        		 existingAssessment.getGroupingName().equals(groupingName.strip())) {
            return false;
         }
      }

      // condition 10: 
      // Date times is formatted in the format DD/MM/YYYY hh:mm and is strictly validated
      // code was created referencing [1]
      // initialize correct date times format
      final DateTimeFormatter FORMAT = DateTimeFormatter
      .ofPattern("dd/MM/uuuu HH:mm").withResolverStyle(ResolverStyle.STRICT);
      try{
         // if string does not match FORMAT or isn't an actual date, throws exception
         LocalDateTime.parse(publishDateTime, FORMAT);
         LocalDateTime.parse(dueDateTime, FORMAT);
         LocalDateTime.parse(hideDateTime, FORMAT);
      }
      catch (Exception e){
         return false;
      }

      // condition 11: publish time is before due time, due time is before hide time
      LocalDateTime publishTime = LocalDateTime.parse(publishDateTime, FORMAT);
      LocalDateTime dueTime = LocalDateTime.parse(dueDateTime, FORMAT);
      LocalDateTime hideTime = LocalDateTime.parse(hideDateTime, FORMAT);

      if (!(publishTime.isBefore(dueTime) && dueTime.isBefore(hideTime))) {
      return false;
      }
   
      return true;  
   } 
   // All points checks passed
}

// References
// [1] https://www.baeldung.com/java-string-valid-date