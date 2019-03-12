package hello;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Ball {
    private float[] position = {0,0,-5};
    private float[] movementDir = {0,0,0};
    private float speed = .1f;
    private float maxX = 5;
    private float minX = -5;
    private float maxY = 5;
    private float minY = -5;

    Ball(){}
    Ball(float[] floats){
        this.movementDir = floats;
    }
    public float[] getPosition() {
        return position;
    }

    public void setPosition(float[] position) {
        this.position = position;
    }

    public float[] getMovementDir() {
        return movementDir;
    }

    public void setMovementDir(float[] movementDir) {
        this.movementDir = movementDir;
    }

    public void move(){
        List<Float> newMoveDir = new ArrayList<>();
        for (int i = 0; i <movementDir.length ; i++) {
//
            // reverses at max X
            if(this.position[0]+(this.movementDir[0]*this.speed) > this.maxX){
                this.position[0] = maxX;
                reflectX();
            }
            // reverse at min X
            if(this.position[0]+(this.movementDir[0]*this.speed) < this.minX){
                this.position[0] = minX;
                reflectX();
            }
            // reverses at max X
            if(this.position[1]+(this.movementDir[1]*this.speed) > this.maxY){
                this.position[1] = maxY;
                reflectY();
            }
            // reverse at min X
            if(this.position[1]+(this.movementDir[1]*this.speed) < this.minY){
                this.position[1] = minY;
                reflectY();
            }


            this.position[i] = this.position[i]+(this.movementDir[i]*this.speed);
        }
    }
    public void reflectX(){
        this.movementDir[0] = this.movementDir[0] * -1;
    }
    public void reflectY(){
        this.movementDir[1] = this.movementDir[1] * -1;
    }
}
