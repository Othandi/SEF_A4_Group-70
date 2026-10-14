   /** TODO: initilise all the attributes*/

package course;

import java.util.ArrayList;
import java.util.List;

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

      //TODO : Jasmine's validation check (Conditions 4, 5, 6) goes here
      //TODO: Minh's validation check (Conditions 8, 9, 10, 11) goes here

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
}