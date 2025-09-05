import java.time.LocalDateTime;
import java.time.YearMonth;

/**
 *
 */
public class Task implements  Comparable<Task> {
  /**
   * Year Task happens in, ex. 2025
   */
  private int year;

  /**
   * Month task happens in ex. 1 (January)
   */
  private int month;

  /**
   * Number of Day in month the task happens in ex. 27 (27th of a month)
   */
  private int dayNum;

  /**
   * Name of task to do
   */
  private String taskName;

  /**
   * Description of the task
   */
  private String taskDescription;

  /**
   * Time the task starts at in 24-Hour format HH:MM
   */
  private String startTime;

  /**
   * Time the task ends at in 24-Hour format HH:MM
   */
  private String endTime;

  /**
   *
   */
  private boolean complete;

  /**
   *
   */
  private LocalDateTime date;

  /**
   *
   */
  String[] monthsWord = {"January", "February", "March", "April", "May", "June", "July", "August",
      "September", "October", "November", "December"};


  /**
   *
   * @param taskName
   * @param year
   * @param month
   * @param dayNum
   * @param startTime
   * @param endTime
   */
  public Task(String taskName, int year, int month, int dayNum, String startTime, String endTime) {
    this.taskName = taskName;
    this.year = year;
    if (month < 1 || month > 12) {
      throw new IllegalArgumentException("Month provided is invalid");
    }
    this.month = month;
    if (validateDayNum(dayNum)) {
      this.dayNum = dayNum;
    } else {
      throw new IllegalArgumentException("Day provided in month " + getMonthWord(month) + " is " +
          "invalid");
    }
    String[] parts = startTime.split(":");
    if (parts.length != 2) {
      throw new IllegalArgumentException("Start time must be in HH:MM format");
    }

    this.startTime = startTime;
    this.endTime = endTime;

    int hour;
    int minute;
    try {
      hour = Integer.parseInt(parts[0]);
      minute = Integer.parseInt(parts[1]);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Start time contains non-numeric values");
    }

    this.date = LocalDateTime.of(year, month, dayNum, hour, minute);
  }

  /**
   *
   * @param month
   * @return
   */
  public String getMonthWord(int month) {
    return this.monthsWord[month - 1];
  }

  /**
   *
   * @param dayNum
   * @return
   */
  public boolean validateDayNum(int dayNum) {
    return dayNum >= 1 && dayNum <= YearMonth.of(this.year, this.month).lengthOfMonth();
  }

  /**
   *
   * @param taskDescription
   */
  public void setTaskDescription(String taskDescription) {
    if (taskDescription == null || taskDescription.isBlank()) {
      throw new IllegalArgumentException("Invalid Description: " + taskDescription);
    }
    this.taskDescription = taskDescription;
  }


  /**
   *
   */
  public void setComplete() {this.complete = true;}

  /**
   *
   * @return
   */
  public boolean getCompletionStatus() {return this.complete;}

  public String getStartTime() {return this.startTime;}

  public String getEndTime() {return this.endTime;}

  public String getTaskDescription() {return this.taskDescription;}

  public LocalDateTime getDate() {return this.date;}

  public String getTaskName() {return this.taskName;}


  @Override
  public int compareTo(Task o) {
    return this.date.compareTo(o.date);
  }
}
