//set up the logic and all limitations for movement in this class
//it doesent know anhything about simulation view
public class robotModel {

    //a position of the ball?
    public int xPos, yPos ;

    public robotModel(){

        this.xPos = 0;
        this.yPos = 0;

    }


    public void moveRight(){
        this.xPos += 1;
    }


}
