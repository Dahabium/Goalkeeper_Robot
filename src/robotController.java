import java.awt.event.*;

//user controls are in this class
//it joins the view with the model
public class robotController {

    private View view;
    private robotModel robotModel;


    public robotController(View view, robotModel model) {
        this.view = view;
        this.robotModel = model;


        keyControl buttonControl = new keyControl();
        view.panel.addKeyListener(buttonControl);

    }

    private class keyControl implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {}

        @Override
        public void keyPressed(KeyEvent e) {

            System.out.println(" a key is pressed !");
            if(e.getKeyChar() == KeyEvent.VK_SPACE){
                System.out.println("New X position " + robotModel.xPos);

                robotModel.moveRight();

                view.setSphereLoc(robotModel.xPos, robotModel.yPos);

            }
        }

        @Override
        public void keyReleased(KeyEvent e) {}
    }
}

