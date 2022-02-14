import java.util.Comparator;

public class TimeComparator implements Comparator<Task>{

    /**
     * Compares task objects by time. If tasks have the exact same deadline, this comparator
     * sorts tasks names alphabetically.
     */
    @Override
    public int compare(Task task1, Task task2) {
        if(task1.getYear() == task2.getYear()) {
            if(task1.getMonth() == task2.getMonth()) {
                if(task1.getDay() == task2.getDay()) {
                    if(task1.getHour() == task2.getHour()) {
                        if(task1.getMinute() == task2.getMinute()) {
                            return task1.getName().compareTo(task2.getName());
                        }
                        else if(task1.getMinute() > task2.getMinute()) {
                            return 1;
                        }
                        else {
                            return -1;
                        }
                    }
                    else if(task1.getHour() > task2.getHour()) {
                        return 1;
                    }
                    else {
                        return -1;
                    }
                }
                else if(task1.getDay() > task2.getDay()) {
                    return 1;
                }
                else {
                    return -1;
                }
            }
            else if(task1.getMonth() > task2.getMonth()) {
                return 1;
            }
            else {
                return -1;
            }
        }
        else if (task1.getYear() > task2.getYear()) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
}
