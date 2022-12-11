import edu.macalester.graphics.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;


/**
 * Creates a GUI for Task objects to be displayed on the main Planner App. Task objects
 * are displayed in an order determined by the selected sort method (PriorityQueue). 
 * Each task object is displayed with a box, checkbox object, x remove button, along with
 * the task name and information. 
 */
public class TaskGUI {

    private static List<Checkbox> checkboxes;
    private static List<Image> xImages;
    private static List<GraphicsText> taskNames;
    private static GraphicsGroup guiGroup; 
    private static Map<String,Boolean> checkedTaskMap;
    private static final Color TASKCOLOR = new Color(173,209,228);
    private static final Color STROKE_COLOR = new Color(16,46,66);

    
    public TaskGUI(List<Task> tasks) {
        guiGroup = new GraphicsGroup();  
        checkedTaskMap = new HashMap<>();
        for(Task task: tasks) {
            checkedTaskMap.put(task.getName(), false);
        }   
    }

    /**
     * Creates all UI elements to represent each task in the Planner. Tasks are displayed in order 
     * depending on the selected priority queue. Each task has a remove button, checkbox object, 
     * and is displayed on the canvas window with the task name, category, due date,
     * deadline time, and priority.
     * @param canvas CanvasWindow where the task UI objects are placed.
     * @param queue  Displays task objects according to the order of the priority queue
     * @param xDistance x-coordinate 
     * @param yDistance y-coordinate 
     */
    public void createTaskGUI(CanvasWindow canvas, PriorityQueue<Task> queue, int xDistance, int yDistance) {
        PriorityQueue<Task> clonedQueue = new PriorityQueue<>(queue);
        guiGroup.removeAll();
        checkboxes = new ArrayList<>();
        xImages = new ArrayList<>();
        taskNames = new ArrayList<>();

        while(!clonedQueue.isEmpty()) {
            Task task = clonedQueue.poll();

            Rectangle box = new Rectangle(xDistance, yDistance, 400, 50);
            box.setFillColor(TASKCOLOR);
            box.setStrokeColor(STROKE_COLOR);
            guiGroup.add(box);

            Checkbox check = new Checkbox(checkedTaskMap.get(task.getName()), guiGroup, xDistance + 30, yDistance + 25);
            checkboxes.add(check);
    
            GraphicsText taskName = new GraphicsText();
            taskName.setFontStyle(FontStyle.BOLD);
            taskName.setText(task.getName());
            taskName.setCenter(xDistance + 200, yDistance + 17);
            taskNames.add(taskName);
    
            GraphicsText taskTime = new GraphicsText();
            taskTime.setFontSize(12);
            taskTime.setText(task.getStrMonth() + ". " + task.getDay() + ", " + task.getYear() + "   " + task.get12HourMin() + "    " + task.getCategory() + "   #" + task.getPriority());
            taskTime.setCenter(xDistance + 200, yDistance + 36);

            Image xRemove = new Image(xDistance + 420, yDistance + 17);
            xRemove.setImagePath("xBlue.png");
            xRemove.setMaxWidth(20);
            xImages.add(xRemove);
    
            guiGroup.add(taskTime);
            guiGroup.add(taskName);
            guiGroup.add(xRemove);

            yDistance = yDistance + 70;
        
        }
        canvas.add(guiGroup);
        
    }

    /**
     * Checks whether a checkbox object has been checked or unchecked when the user clicks on the
     * CanvasWindow. If an unchecked checkbox has been clicked, a check appears while if a checked 
     * checkbox has been clicked, the check is removed to reflect its state. Clicking on checkboxes
     * updates the checkedTaskMap (Hashmap) that keeps track of which boxes are checked/unchecked. 
     * @param position  Point (x,y) of the user's click on the CanvasWindow.
     */
    public void clickBox(Point position) {
        for(int i = 0; i < checkboxes.size(); i++) {
            Checkbox check = checkboxes.get(i);
            Point checkPosition = check.getPosition();
            if(!check.isChecked() && position.getX() > checkPosition.getX() && position.getX() < checkPosition.getX() + 25 && position.getY() > checkPosition.getY() && position.getY() < checkPosition.getY() + 25) {
                check.markChecked();
                checkedTaskMap.put(taskNames.get(i).getText(), true);
            }
            else if(check.isChecked() && position.getX() > checkPosition.getX() && position.getX() < checkPosition.getX() + 25 && position.getY() > checkPosition.getY() && position.getY() < checkPosition.getY() + 25) {
                check.markUnchecked();
                checkedTaskMap.put(taskNames.get(i).getText(), false);

            }
        }
    }

    /**
     * Checks if any x remove button has been clicked and returns the name of a task 
     * that should be removed.
     * @param position  Point position (x,y) of the user's click on the CanvasWindow
     * @return  name of the Task object to be removed or "" if no task has been clicked.
     */
    public String getRemovedTaskName(Point position) {
        String taskName = "";
        for(int i = 0; i < xImages.size(); i++) {
            Point xPosition = xImages.get(i).getPosition();
            if(position.getX() > xPosition.getX() && position.getX() < xPosition.getX() + 25 && position.getY() > xPosition.getY() && position.getY() < xPosition.getY() + 25) {
                taskName = taskNames.get(i).getText();
            }
        }
        return taskName;
    }

    /**
     * Removes a task from the checkedTaskMap which keeps track of the checkboxes corresponding to 
     * tasks that are checked/unchecked.
     * @param task  removed task
     */
    public void updateChecksAfterRemove(Task task) {
        checkedTaskMap.remove(task.getName());

    }



}
