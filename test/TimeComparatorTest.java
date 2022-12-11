import org.junit.jupiter.api.Test;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class TimeComparatorTest {
    private List<Task> tasks;
    private PriorityQueue<Task> pq;

    @BeforeEach
    public void setup() {
        tasks = new ArrayList<>();
        Task task1 = new Task("Homework 3", "Math", 12, 7, 2021, 20, 02, 1);
        Task task2 = new Task("Study for Quiz 2", "Computer Science", 12, 9, 2021, 19, 00, 2);
        Task task3 = new Task("Read Research", "Biology" , 11, 12, 2021, 20, 18, 4);
        Task task4 = new Task("Homework 6", "Math", 8, 28, 2021, 8, 30, 1);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);

        TimeComparator timecomp = new TimeComparator();
        pq = new PriorityQueue<>(timecomp);
        for(Task task: tasks) {
            System.out.println(task.getName());
            pq.offer(task);
        }

    }    

    @Test
    public void testTimeComparator() {
        assertEquals(4, pq.size());
        //assertEquals(expected, actual);

    }
}
