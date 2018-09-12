import javax.swing.*;
import java.awt.*;

public class StartRobot {

    public static void main(String[] args) {

        //create MVC components

        robotModel model = new robotModel();
        View view = new View();
        robotController controller = new robotController(view, model);

    }

}
