   /** TODO: initilise all the attributes*/
   /** Constructor initializes all the attributes of the Assessment class */

package course;
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
   private List<Assessment> assessments;
   
   public boolean createNewAssessment(String name, String description, int points, 
                                      String submissionType, boolean group, String groupingName, 
                                      String publishDateTime, String dueDateTime, String hideDateTime) {
      boolean valid = true;
      
      //TODO: Validate all the provided parameters.
      //if the assessment parameters meet the given conditions,
      //   the function should create a new Assessment object, 
      //   add it in the assessments list and then return true.
      //else
      //   the function should only return false.
      
      return valid;
   }
}