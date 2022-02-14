import java.util.Comparator;


/**
 * Sorts tasks based on priority. If tasks are of the same priority level, this comparator
 * sorts tasks by deadline (closest first), and then alphabetically by name.
 */
public class PriorityComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        if(task1.getPriority() == task2.getPriority()) {
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
        else if(task1.getPriority() > task2.getPriority()) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
}
