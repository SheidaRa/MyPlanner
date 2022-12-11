
import edu.macalester.graphics.*;


/**
 * Creates a checkbox that can be marked as checked to indicate completion of a task
 * or marked as unchecked to indicate that a tasks needs to be completed. 
 */
public class Checkbox {
    
    private boolean checked;
    private Image check;

    private static final String CHECKED_IMG = "checkedRound.png";
    private static final String UNCHECKED_IMG = "uncheckedRound.png";


    /**
     * Creates a new instance of a checkbox
     * @param checked   true if checkbox is checked, false if unchecked
     * @param group     graphics group containing checkbox
     * @param xPosition center x-position of the checkbox
     * @param yPosition cetner y-position of the checkbox
     */
    public Checkbox(boolean checked, GraphicsGroup group, int xPosition, int yPosition) {
        this.checked = checked;
        check = new Image(0,0);
        if(checked) {
            check.setImagePath(CHECKED_IMG);
        }
        else {
            check.setImagePath(UNCHECKED_IMG);
        }
        check.setMaxWidth(25);
        check.setCenter(xPosition, yPosition);
        group.add(check);
    }

    public boolean isChecked() {
        return checked;
    }

    public void markChecked() {
        check.setImagePath(CHECKED_IMG);
        checked = true;
    }

    public void markUnchecked() {
        check.setImagePath(UNCHECKED_IMG);
        checked = false;
    }

    public Point getPosition() {
        return check.getPosition();
    }





}
