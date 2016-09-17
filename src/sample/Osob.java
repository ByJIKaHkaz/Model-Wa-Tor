package sample;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Владимир on 18.06.2016.
 */
public class Osob {
    Random random = new Random();
    private int age=0;
    private int baby=0;;
    private int eat=0;
    private int x=-1;
    private int y=-1;
    private int x1=0;
    private int y1=0;

    public Osob(){}
    public void setAge(){
        this.age+=1;
    }
    public void setFirstAge(int age){
        this.age=age;
    }
    public int getAge(){
        return this.age;
    }
    public void setBaby(){
        this.baby+=1;
    }
    public void resetBaby(){
        this.baby=0;
    }
    public int getBaby(){
        return this.baby;
    }
    public void setEat(){
        this.eat+=1;
    }
    public void resetEat(){
        this.eat=0;
    }
    public void unsetEat(){
        this.eat-=1;
    }
    public int getEat(){
        return this.eat;
    }
    public void setXY(int x,int y){
        this.x=x;this.y=y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void setX1Y1(int x,int y){
        this.x1=x;this.y1=y;
    }
    public int getX1(){
        return this.x1;
    }
    public int getY1(){
        return this.y1;
    }
}
