import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.PriorityQueue;  

/**
 * Creates a Planner that allows a user to add new tasks, remove tasks, mark tasks as checked or
 * unchecked. The Planner allows the user to view tasks alphabetical according to category names, 
 * by priority, or by time remaining until deadline.
 */
public class PlannerApp {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 800;
    private static final int TASK_X = 100;
    private static final int TASK_Y = 160;

    private static SimpleDateFormat formatter;
    private static GraphicsText currentTime;
    private static CanvasWindow canvas;
    private static Image addButton;
    private static Image quitButton;
    
    private static List<Task> tasks;
    private static TaskGUI gui;
    private static AddMenu addMenu;

    private static PriorityQueue<Task> timeQueue;
    private static PriorityQueue<Task> priorityQueue;
    private static PriorityQueue<Task> categoryQueue;
    private static PriorityQueue<Task> currentSort;

    private static CategoryComparator categoryComparator;
    private static PriorityComparator priorityComparator;
    private static TimeComparator timeComparator;
    
    public PlannerApp() {
        canvas = new CanvasWindow("Planner", WINDOW_WIDTH, WINDOW_HEIGHT);
        tasks = new ArrayList<>();

        initializeExampleTasks();
        
        timeComparator = new TimeComparator();
        timeQueue = new PriorityQueue<>(timeComparator);

        priorityComparator = new PriorityComparator();
        priorityQueue = new PriorityQueue<>(priorityComparator);

        categoryComparator = new CategoryComparator();
        categoryQueue = new PriorityQueue<>(categoryComparator);

        initializeApp();
        
        canvas.animate((event) -> {
            updateTime();
        });
        
        handleClicks(canvas);
    }


    /**
     * Handles on click events on the canvas, triggers task removal and UI updates if a remove button
     * is clicked. Initializes the AddMenu if the add button is clicked.
     * @param canvas Planner App's canvas window
     */
    private static void handleClicks(CanvasWindow canvas) {
        canvas.onClick(event -> {
            gui.clickBox(event.getPosition());
            String removedTaskName = gui.getRemovedTaskName(event.getPosition());
            int index = checkTaskName(removedTaskName);
            if(index >= 0) {
                gui.updateChecksAfterRemove(tasks.get(index));
                tasks.remove(index);
                updateQueues();
                gui.createTaskGUI(canvas, currentSort, TASK_X, TASK_Y);
            }
            checkAddClick(event.getPosition());
            checkQuitClick(event.getPosition());
        });
    }

    /**
     * Initializes all GUI elements in the main Planner App.
     */
    private static void initializeApp() {
        Image backGround = new Image(0,0);
        backGround.setImagePath("nature4.png");
        backGround.setMaxWidth(WINDOW_WIDTH);
        backGround.setMaxHeight(WINDOW_HEIGHT);
        canvas.add(backGround);

        GraphicsText title = new GraphicsText();
        title.setText("Task Planner");
        title.setFont(FontStyle.BOLD, 20);
        title.setCenter(WINDOW_WIDTH / 2, 80);
        canvas.add(title);

        formatter = new SimpleDateFormat("EEE  MMM. d, YYYY \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t h:mm:ss a");  
        currentTime = new GraphicsText();
        currentTime.setCenter(25, 25);
        canvas.add(currentTime);

        addButton = new Image(WINDOW_WIDTH*0.85,40);
        addButton.setImagePath("plusSolid.png");
        addButton.setMaxWidth(40);
        canvas.add(addButton);

        quitButton = new Image(WINDOW_WIDTH* 0.47, 40);

        updateQueues();

        gui = new TaskGUI(Collections.unmodifiableList(tasks));
        gui.createTaskGUI(canvas, timeQueue, TASK_X, TASK_Y);
        currentSort = timeQueue;

        createButtons();
    }

    /**
     * Creates a date object that has the current time. Sets the Planner App's clock text
     * to the current time. 
     */
    private static void updateTime() {
        Date date = new Date();  
        currentTime.setText(formatter.format(date));
    }

    /**
     * Updates the contents of each priority queue to reflect changes made to the PlannerApp's
     * task list when a task is added or removed. 
     */
    private static void updateQueues() {
        timeQueue.clear();
        priorityQueue.clear();
        categoryQueue.clear();
        for(Task task: tasks) {
            timeQueue.offer(task);
            priorityQueue.offer(task);
            categoryQueue.offer(task);
        }

    }

    /**
     * For Demonstration/Presentation: Creates example tasks to demonstrate the functionality of the Planner App.
     */
    private static void initializeExampleTasks() {
        Task task1 = new Task("Homework 3", "MATH279", 12, 7, 2021, 20, 02, 1);
        Task task2 = new Task("Study for Quiz 2", "COMP128", 12, 9, 2021, 19, 00, 2);
        Task task3 = new Task("Read Research", "BIO100" , 11, 12, 2021, 20, 18, 4);
        Task task4 = new Task("Homework 6", "BIO100", 8, 28, 2021, 8, 30, 3);
        tasks.add(task1);
        tasks.add(task2);
        tasks.add(task3);
        tasks.add(task4);
        System.out.println("Tasks: " + tasks.size());
    }


    /**
     * Creates sort buttons which when clicked, resets the GUI to display tasks ordered in a specific way.
     */
    private static void createButtons() {
        Button sortByPriority = new Button("Sort by Priority");
        sortByPriority.setCenter(160, 120);
        sortByPriority.onClick(() -> {
            gui.createTaskGUI(canvas, priorityQueue, TASK_X, TASK_Y);
            currentSort = priorityQueue;
        });
        canvas.add(sortByPriority);

        Button sortByCategory = new Button("Sort by Category");
        sortByCategory.setCenter(305, 120);
        sortByCategory.onClick(() -> {
            gui.createTaskGUI(canvas, categoryQueue, TASK_X, TASK_Y);
            currentSort = categoryQueue;
        });
        canvas.add(sortByCategory);

        Button sortByTime = new Button("Sort by Time");
        sortByTime.setCenter(445, 120);
        sortByTime.onClick(() -> {
            gui.createTaskGUI(canvas, timeQueue, TASK_X, TASK_Y);
            currentSort = timeQueue;
        });
        canvas.add(sortByTime);
    }

    /**
     * Checks whether a given task name exists and belongs to a task in the PlannerApp task list. 
     * If the task name is not unique, the index of the task with the matching task name is returned. 
     * If the task name is unique, this method returns -1.
     * @param taskname name of another task
     * @return  index of the task with the given taskname
     */
    private static int checkTaskName(String taskname) {
        int index = -1;
        for(int i=0; i < tasks.size(); i++) {
            if(tasks.get(i).getName() == taskname) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Checks whether the user clicked on the add button on the Planner App and initializes the Add Menu
     * if clicked. Adds a new task to the task list if all of the user's inputted task information is valid 
     * and updates the UI to reflect the addition of a new task.
     * @param position  Point position (x,y) of a user's click
     */
    private static void checkAddClick(Point position) {
        Point addPosition = addButton.getPosition();
        if(position.getX() > addPosition.getX() && position.getX() < addPosition.getX() + 45 && position.getY() > addPosition.getY() && position.getY() < addPosition.getY() + 45) {
            Button enterTask = new Button("Enter");
            addMenu = new AddMenu(canvas, Collections.unmodifiableList(tasks), quitButton, enterTask, WINDOW_WIDTH, WINDOW_HEIGHT);
            enterTask.onClick(() -> {
                addMenu.updateEntries();
                if(addMenu.getInputPasses()) {
                    Task userTask = addMenu.getNewTask();
                    tasks.add(userTask);
                    canvas.removeAll();
                    initializeApp();
                }
            });
        }
    }


    /**
     * Checks whether the user clicked on the quit button on the AddMenu. If the quit button is clicked,
     * this removes the AddMenu from the Canvas Window and restores the Planner App UI with tasks displayed.
     * @param position Point position (x,y) of a user's click
     */
    private static void checkQuitClick(Point position) {
        Point quitPosition = quitButton.getPosition();
        if(position.getX() > quitPosition.getX() && position.getX() < quitPosition.getX() + 45 && position.getY() > quitPosition.getY() && position.getY() < quitPosition.getY() + 45) {
            canvas.removeAll();
            initializeApp();
        }
    }


    public static void main(String[] args) {
        PlannerApp plan = new PlannerApp();
    }
}


