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

   public Assessment(String name, String description, int points, 
                     String submissionType, boolean group, String groupingName, 
                     String publishDateTime, String dueDateTime, String hideDateTime) {
      // initialize the asm object with provided arguments. 
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

      // getters and setters for asm attributes
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
   // initialize an empty array list to hold Assessment objects.
   private List<Assessment> assessments = new ArrayList<>();

   // Getter for assessments list to verify conditions for JUnit tests.
   public List<Assessment> getAssessments() {
      return assessments;
   }
   
   public boolean createNewAssessment(String name, String description, int points, 
                                      String submissionType, boolean group, String groupingName, 
                                      String publishDateTime, String dueDateTime, String hideDateTime) {
      
      // Minh Duc's validation check for condition 8, 9, 10, 11
      if (!validateGroupNameDateTimes(group, groupingName, publishDateTime, dueDateTime, hideDateTime))
         return false; 
      
      // all validation passed, instantiate Assessment object, add to Asssessments list, and return true
      Assessment newAssessment = new Assessment(name, description, points, submissionType, group,
      groupingName, publishDateTime, dueDateTime, hideDateTime);

      assessments.add(newAssessment);
      return true;
   }


   // Minh Duc's validation methods for condtion 8, 9, 10, 11.
   private boolean validateGroupNameDateTimes(boolean group, String groupingName,
                     String publishDateTime, String dueDateTime, String hideDateTime) {
      
      // condtion 8: if group is true, groupingName is not empty.
      if (group && (groupingName == null || groupingName.isEmpty() || groupingName == "" || groupingName.isBlank())) {
      return false;
      }

      // condtion 9: groupingName must be unique among all assessments.
      for (Assessment existingAssessment : assessments) {
         if (existingAssessment.getGroupingName().equals(groupingName.strip())) {
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
}

// References
// [1] https://www.baeldung.com/java-string-valid-date