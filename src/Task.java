

/**
 * Creates a Task object that represents a specific tasks and contains user-specified information 
 * about the task such as its name, category, due date (month, day, year), deadline time 
 * (hour, minute), and priority level.
 */
public class Task {

    private String name;
    private String category;
    private  int month;
    private  int day;
    private  int year;
    private  int hour;
    private  int minute;
    private  int priority;

    
    public Task(String name, String category, int month, int day, int year, int hour, int minute, int priority) {
        this.name = name;
        this.category = category;
        this.month = month;
        this.day = day;
        this.year = year;
        this.hour = hour;
        this.minute = minute;
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Task [category=" + category + ", day=" + day + ", hour=" + hour + ", minute=" + minute + ", month="
            + month + ", name=" + name + ", priority=" + priority + ", year=" + year + "]";
    }


    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getMonth() {
        return month;
    }

    public String getStrMonth() {
        if(month == 1) {
            return "Jan";
        }
        else if(month == 2) {
            return "Feb";
        }
        else if(month == 3) {
            return "Mar";
        }
        else if(month == 4) {
            return "Apr";
        }
        else if(month == 5) {
            return "May";
        }
        else if(month == 6) {
            return "Jun";
        }
        else if(month == 7) {
            return "Jul";
        }
        else if(month == 8) {
            return "Aug";
        }
        else if(month == 9) {
            return "Sept";
        }
        else if(month == 10) {
            return "Oct";
        }
        else if(month == 11) {
            return "Nov";
        }
        else {
            return "Dec";
        }

    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getHour() {
        return hour;
    }


    public int getMinute() {
        return minute;
    }

    public String get12HourMin() {
        String hourStr = "";
        String amPm = "";
        if(hour > 12) {
            int new12Hour = hour - 12;
            hourStr = hourStr + new12Hour;
            amPm = "PM";
        }
        else {
            if(hour == 0) {
                hourStr = hourStr + 12;
            }
            else {
                hourStr = hourStr + hour;
            }
            amPm = "AM";
        }
        String minuteStr = "";
        if(minute < 10) {
            minuteStr =  "0" + minute;
        }
        else {
            minuteStr = minuteStr + minute;
        }
        return hourStr + ":" + minuteStr + " " + amPm;
    }

    public int getPriority() {
        return priority;
    }

    public boolean equals(Task task1, Task task2) {
        if(task1.getName().equals(task2.getName()) && 
        task1.getCategory().equals(task2.getCategory()) && 
        task1.getMonth() == task2.getMinute() &&
        task1.getDay() == task2.getDay() &&
        task1.getYear() == task2.getYear() &&
        task1.getHour() == task2.getHour() &&
        task1.getMinute() == task2.getMinute() &&
        task1.getPriority() == task2.getPriority()) {
            return true;
        }
        else {
            return false;
        }
    }

    

}
