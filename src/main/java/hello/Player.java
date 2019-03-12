package hello;

public class Player {
    public int playerId = 0;
    public float position = 0;
    public float speed = .1f;
    public float maxPosition = 5;
    public float minPosition = -5;

    Player(){
    }

    public float getPosition() {
        return position;
    }
    public float getSpeed(){
        return speed;
    }
    public void  setSpeed(float speed){
        this.speed = speed;
    }
    public void setMaxPosition(float maxPosition) {
        this.maxPosition = maxPosition;
    }

    public void setMinPositon(float minPositon) {
        this.minPosition = minPositon;
    }

    public float getMaxPosition() {
        return maxPosition;
    }

    public float getMinPositon() {
        return minPosition;
    }
    public void movePlayerUp(){
        if((this.position += this.speed)> maxPosition){
            this.position = maxPosition;
        }else {
            this.position += this.speed;
        }
    }
    public void movePlayerDown(){
        if((this.position -= this.speed)< minPosition){
            this.position = minPosition;
        }else
        this.position -= this.speed;
    }

}
