import java.util.Comparator;


/**
 * Sorts tasks by alphabetical order of their category. If tasks belong to the same category,
 * this comparator sorts tasks according to time remaining.
 */
public class CategoryComparator implements Comparator<Task> {

    @Override
    public int compare(Task task1, Task task2) {
        if(task1.getCategory().compareTo(task2.getCategory()) == 0) {
            if(task1.getYear() == task2.getYear()) {
                if(task1.getMonth() == task2.getMonth()) {
                    if(task1.getDay() == task2.getDay()) {
                        if(task1.getHour() == task2.getHour()) {
                            if(task1.getMinute() == task2.getMinute()) {
                                return 0;
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
        else if(task1.getCategory().compareTo(task2.getCategory()) > 0) {
            return 1;
        }
        else {
            return -1;
        }
    }
    
}
