import java.awt.Color;
import java.util.List;

import edu.macalester.graphics.*;
import edu.macalester.graphics.ui.*;



/**
 * Add Menu creates a faux window where users can input task information into various fields
 * to create a new task. Add Menu checks whether the user's inputted information is valid 
 * (e.g. months should not be less than 0 or surpass 12) and displays error messages if the user's
 * inputted information is invalid. If all inputted task information is valid, Add Menu can create
 * a new task using the user's inputs. 
 */
public class AddMenu {

    private Image quitButton;
    private Button enterTask;
    private static Image backGround;
    private static List<Task> tasks;

    private static String newTaskName;
    private static String newCategory;
    private static String newMonth;
    private static Integer newMonthInt;
    private static String newDay;
    private static Integer newDayInt;
    private static String newYear;
    private static Integer newYearInt;
    private static String newHour;
    private static Integer newHourInt;
    private static String newMinute;
    private static Integer newMinuteInt;
    private static String newPriority;
    private static Integer newPriorityInt;

    private static GraphicsText title;
    private static GraphicsText instructions;
    private static GraphicsText errorMessage;

    private static TextField taskNameField;
    private static GraphicsText nameDesc;

    private static TextField categoryField;
    private static GraphicsText categoryDesc;

    private static TextField monthField;
    private static GraphicsText monthDesc;

    private static TextField dayField;
    private static GraphicsText dayDesc;

    private static TextField yearField;
    private static GraphicsText yearDesc;

    private static TextField hourField;
    private static GraphicsText hourDesc;

    private static TextField minuteField;
    private static GraphicsText minuteDesc;

    private static TextField priorityField;
    private static GraphicsText priorityDesc;


    /**
     * Creates a new instance of an Add Menu.
     * @param canvas        CanvasWindow where the Add Menu is initialized
     * @param tasks         list of tasks from PlannerApp
     * @param quitButton    quit button to terminate Add Menu
     * @param enterTask     enter button to check user input/add new task
     * @param width         width of the Add Menu
     * @param height        height of the Add Menu
     */
    public AddMenu(CanvasWindow canvas, List<Task> tasks, Image quitButton, Button enterTask, int width, int height) {
        canvas.removeAll();
        this.tasks = tasks;
        this.quitButton = quitButton;
        this.enterTask = enterTask;
        createAddMenu(width, height);
        addAlltoCanvas(canvas);
    }


    /**
     * Creates and formats all UI objects in the Add Menu
     * @param width     width of the CanvasWindow containing the Add Menu
     * @param height    height of the CanvasWindow containing the Add Menu
     */
    private void createAddMenu(int width, int height) {
        backGround = new Image(0,0);
        backGround.setImagePath("nature3.png");
        backGround.setMaxWidth(width);
        backGround.setMaxHeight(height);

        title = new GraphicsText();
        title.setText("Create a Task");
        title.setFontSize(20);
        title.setCenter(width / 2, 120);

        instructions = new GraphicsText();
        instructions.setText("Fill in the fields below to add a new task.");
        instructions.setCenter(width/2, 155);

        errorMessage = new GraphicsText();
        errorMessage.setFillColor(Color.white);
        errorMessage.setCenter(width *0.4, 580);

        quitButton.setImagePath("xGreen.png");
        quitButton.setMaxWidth(40);

        taskNameField = new TextField();
        nameDesc = new GraphicsText();
        nameDesc.setText("Task Name");
        taskNameField.setCenter(230, 210);
        nameDesc.setCenter(230,240);

        categoryField = new TextField();
        categoryDesc = new GraphicsText();
        categoryDesc.setText("Task Category");
        categoryField.setCenter(370, 210);
        categoryDesc.setCenter(370,240);

        monthField = new TextField();
        monthDesc = new GraphicsText();
        monthDesc.setText("Month Due (1-12)");
        monthField.setCenter(150, 290);
        monthDesc.setCenter(150,320);

        dayField = new TextField();
        dayDesc = new GraphicsText();
        dayDesc.setText("Day Due (1-31)");
        dayField.setCenter(300, 290);
        dayDesc.setCenter(300,320);

        yearField = new TextField();
        yearDesc = new GraphicsText();
        yearDesc.setText("Year Due (4 digits)");
        yearField.setCenter(450, 290);
        yearDesc.setCenter(450,320);

        hourField = new TextField();
        hourDesc = new GraphicsText();
        hourDesc.setText("Hour Due (0-23)");
        hourField.setCenter(230, 370);
        hourDesc.setCenter(230,400);

        minuteField = new TextField();
        minuteDesc = new GraphicsText();
        minuteDesc.setText("Minute Due (0-59)");
        minuteField.setCenter(370, 370);
        minuteDesc.setCenter(370,400);

        priorityField = new TextField();
        priorityDesc = new GraphicsText();
        priorityDesc.setText("\t \t \t \t \t \t \t \t \t Task Priority (1-4)\n1 = most important, 4 = least important");
        priorityField.setCenter(width*0.5, 450);
        priorityDesc.setCenter(width*0.5,490);

        enterTask.setCenter(width*0.5, 540);
    }


    /**
     * Adds all add menu GUI objects to a CanvasWindow
     * @param canvas    CanvasWindow containing Add Menu
     */
    private void addAlltoCanvas(CanvasWindow canvas) {
        canvas.add(backGround);
        canvas.add(title);
        canvas.add(instructions);
        canvas.add(quitButton);

        canvas.add(taskNameField);
        canvas.add(categoryField);
        canvas.add(monthField);
        canvas.add(dayField);
        canvas.add(yearField);
        canvas.add(hourField);
        canvas.add(minuteField);
        canvas.add(priorityField);

        canvas.add(nameDesc);
        canvas.add(categoryDesc);
        canvas.add(monthDesc);
        canvas.add(dayDesc);
        canvas.add(yearDesc);
        canvas.add(hourDesc);
        canvas.add(minuteDesc);
        canvas.add(priorityDesc);
        canvas.add(errorMessage);
        canvas.add(enterTask);
    }

    /**
     * Retrieves user's inputted task information (Strings) in the AddMenu text fields and 
     * updates corresponding task information instance variables.     
    */
    public void updateEntries() {
        newTaskName = taskNameField.getText();
        newCategory = categoryField.getText();
        newMonth = monthField.getText();
        newDay = dayField.getText();
        newYear = yearField.getText();
        newHour = hourField.getText();
        newMinute = minuteField.getText();
        newPriority = priorityField.getText();
    }

    /**
     * Checks whether the user's inputted information to create a new task is valid. 
     * Returns false and displays case-specific error messages on the add menu if any of the user's inputs are invalid.
     * Returns true if and only if all of the user's inputted texts are valid.
     * @return true if all of the user's inputted text are valid
     */
    private static boolean checkInputPasses() {
        String errorText = "Please enter a valid: ";
        if(!taskNameExists(newTaskName) && (!newTaskName.isEmpty() || !newTaskName.equals(""))) {
            nameDesc.setFillColor(Color.BLACK);
        }
        else {
            nameDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - unique task name ";
        }

        if(!newCategory.isEmpty()|| !newCategory.equals("")) {
            categoryDesc.setFillColor(Color.BLACK);
        }
        else {
            categoryDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - category name ";

        }

        if(isInt(newMonth)) {
            int month = Integer.parseInt(newMonth);
            if(month >= 1 && month <= 12) {
                monthDesc.setFillColor(Color.BLACK);
                newMonthInt = month;
            }
            else {
                monthDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - month within 1-12 ";
            }
        }
        else {
            monthDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical month ";
        }

        if(isInt(newDay)) {
            int day = Integer.parseInt(newDay);
            if(day >= 1 && day <= 31) {
                dayDesc.setFillColor(Color.BLACK);
                newDayInt = day;
            }
            else {
                dayDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - day within 1-31 ";
            }
        }
        else {
            dayDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical day ";
        }

        if(isInt(newYear)) {
            int year = Integer.parseInt(newYear);
            if(year >= 1900 && year <= 2100) {
                yearDesc.setFillColor(Color.BLACK);
                newYearInt = year;
            }
            else {
                yearDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - year within 1900-2100 ";
            }
        }
        else {
            yearDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical year ";
        }

        if(isInt(newHour)) {
            int hour = Integer.parseInt(newHour);
            if(hour >= 0 && hour <= 23) {
                newHourInt = hour;
                hourDesc.setFillColor(Color.BLACK);
            }
            else {
                hourDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - hour within 0-23 ";
            }
        }
        else {
            hourDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical hour ";
        }

        if(isInt(newMinute)) {
            int min = Integer.parseInt(newMinute);
            if(min >= 0 && min <= 59) {
                newMinuteInt = min;
                minuteDesc.setFillColor(Color.BLACK);
            }
            else {
                minuteDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - minute within 0-59 ";
            }
        }
        else {
            minuteDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical minute ";
        }

        if(isInt(newPriority)) {
            int priority = Integer.parseInt(newPriority);
            if(priority >= 1 && priority <= 4) {
                priorityDesc.setFillColor(Color.BLACK);
                newPriorityInt = priority;
            }
            else {
                priorityDesc.setFillColor(Color.RED);
                errorText = errorText + "\n - priority within 1-4 ";
            }
        }
        else {
            priorityDesc.setFillColor(Color.RED);
            errorText = errorText + "\n - numerical priority ";
        }

        if(errorText.equals("Please enter a valid: ")) {
            return true;
        }
        else {
            errorMessage.setText(errorText);
            return false;
        }
    }

    /**
     * Getter method to determine whether user inputs are valid and be used to create a new task.
     * @return true if all user's inputs are valid
     */
    public boolean getInputPasses() {
        return checkInputPasses();
    }

    /**
     * Helper method to check whether the user's inputted task name is already taken by an existing task.  
     * @param taskName  String representation of a task name
     * @return          true if the task name is already taken, false if task name is unique
     */
    private static boolean taskNameExists(String taskName) {
        for(Task task: tasks) {
            if(task.getName().equals(taskName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Creates and returns a new task using user inputted task information.
     * @return new task
     */
    public Task getNewTask() {
        Task newTask = new Task(newTaskName, newCategory, newMonthInt, newDayInt, newYearInt, newHourInt, newMinuteInt, newPriorityInt);
        return newTask;
    }
    
    /**
     * Helper method to check whether a user's input (string) is an integer.
     * @param str   String representation of user's input
     * @return      true if user's input is an integer, false if user's input is null or not an integer.
     */
    private static boolean isInt(String str){
        return str != null && str.matches("[0-9]+");
    }


}




