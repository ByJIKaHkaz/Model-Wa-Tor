package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main extends Exception{
    public static int poleX= 200;
    public static int poleY=200;
    public static int step=400;
    public static int sharkCount=500;
    public static int fishCount=8000;
    public static int eatCount=30000;

    public static int maxAgeShark=50;
    public static int maxBabyShark=24;
    public static int maxEatShark=23;

    public static int maxAgeFish=15;
    public static int maxBabyFish=7;
    public static int maxEatFish=6;
    public static void main(String[] args)  {


        Random rand = new Random();
        int [][] pole = new int [201][201];
        ArrayList<Shark> shark = new ArrayList<>();ArrayList<Fish> fish = new ArrayList<>(); ArrayList<Eat> eat = new ArrayList<>();
        for(int i =0;i<sharkCount;i++){
            shark.add(new Shark());
            shark.get(i).setFirstAge(rand.nextInt(maxAgeShark));
            while(true) {
                int x = rand.nextInt(poleX);
                int y = rand.nextInt(poleY);
                boolean caseEmpty = (pole[x][y] == 0)&&x!=0&&y!=0;
                if(caseEmpty){
                    shark.get(i).setXY(x,y);
                    pole[shark.get(i).getX()][shark.get(i).getY()]=3;
                    break;}
            }
        }
        for(int i =0;i<fishCount;i++){
            fish.add(new Fish());
            fish.get(i).setFirstAge(rand.nextInt(maxAgeFish));
            while(true) {
                int x = rand.nextInt(poleX);
                int y = rand.nextInt(poleY);
                boolean caseEmpty = (pole[x][y] == 0)&&x!=0&&y!=0;
                if(caseEmpty){fish.get(i).setXY(x,y);
                    pole[fish.get(i).getX()][fish.get(i).getY()]=2;
                    break;}
            }
        }
        for(int i =0;i<eatCount;i++){
            eat.add(new Eat());
            while(true) {
                int x = rand.nextInt(poleX);
                int y = rand.nextInt(poleY);
                boolean caseEmpty = (pole[x][y] == 0)&&x!=0&&y!=0;
                if(caseEmpty){eat.get(i).setXY(x,y);
                    pole[eat.get(i).getX()][eat.get(i).getY()]=1;
                    break;}
            }
        }
        for(int i=0;i<step;i++) {

                /*Смерть*/
            for (int j = 0; j < shark.size(); j++) {
                if (shark.get(j).getEat() > maxEatShark | shark.get(j).getAge() > maxAgeShark) {
                    int x = shark.get(j).getX();
                    int y = shark.get(j).getY();
                    pole[x][y] = 0;
                    shark.get(j).resetEat();
                    shark.get(j).resetBaby();
                    shark.remove(j);

                }
            }
            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j).getEat() > maxEatFish | fish.get(j).getAge() > maxAgeFish) {
                    int x = fish.get(j).getX();
                    int y = fish.get(j).getY();
                    pole[x][y] = 0;
                    fish.get(j).resetEat();
                    fish.get(j).resetBaby();
                    fish.remove(j);
                }
            }
                /*Передвижение*/
            for (int j = 0; j < shark.size(); j++) {
                if (shark.get(j).getAge() < maxAgeShark) {
                    int x = shark.get(j).getX();
                    int y = shark.get(j).getY();
                    if (x > 0 & x < poleX & y > 0 & y < poleY) {
                        if (pole[x][y + 1] == 0) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x, y + 1);
                            pole[x][y + 1] = 3;
                        } else if (pole[x][y - 1] == 0) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x, y - 1);
                            pole[x][y - 1] = 3;
                        } else if (pole[x + 1][y] == 0) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x + 1, y);
                            pole[x + 1][y] = 3;
                        } else if (pole[x - 1][y] == 0) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x - 1, y);
                            pole[x - 1][y] = 3;
                        } else if (pole[x][y + 1] == 2) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x, y + 1);
                            for (int z = 0; z < fish.size(); z++) {
                                if (fish.get(z).getX() == x && fish.get(z).getY() == y + 1)
                                    fish.remove(z);
                            }
                            pole[x][y + 1] = 3;
                            shark.get(j).unsetEat();
                        } else if (pole[x][y - 1] == 2) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x, y - 1);
                            for (int z = 0; z < fish.size(); z++) {
                                if (fish.get(z).getX() == x && fish.get(z).getY() == y - 1)
                                    fish.remove(z);
                            }
                            pole[x][y - 1] = 3;
                            shark.get(j).unsetEat();
                        } else if (pole[x + 1][y] == 2) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x + 1, y);
                            for (int z = 0; z < fish.size(); z++) {
                                if (fish.get(z).getX() == x + 1 && fish.get(z).getY() == y)
                                    fish.remove(z);
                            }
                            pole[x + 1][y] = 3;
                            shark.get(j).unsetEat();
                        } else if (pole[x - 1][y] == 2) {
                            shark.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            shark.get(j).setXY(x - 1, y);
                            for (int z = 0; z < fish.size(); z++) {
                                if (fish.get(z).getX() == x - 1 && fish.get(z).getY() == y)
                                    fish.remove(z);
                            }
                            pole[x - 1][y] = 3;
                            shark.get(j).unsetEat();
                        }
                    }
                }
            }

            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j).getAge() < maxAgeFish) {
                    int x = fish.get(j).getX();
                    int y = fish.get(j).getY();
                    if (x > 0 & x < poleX & y > 0 & y < poleY) {
                        if (pole[x][y + 1] == 0) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x, y + 1);
                            pole[x][y + 1] = 2;
                        } else if (pole[x][y - 1] == 0) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x, y - 1);
                            pole[x][y - 1] = 2;
                        } else if (pole[x + 1][y] == 0) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x + 1, y);
                            pole[x + 1][y] = 2;
                        } else if (pole[x - 1][y] == 0) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x - 1, y);
                            pole[x - 1][y] = 2;
                        } else if (pole[x][y + 1] == 1) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x, y + 1);
                            for (int z = 0; z < eat.size(); z++) {
                                if (eat.get(z).getX() == x && eat.get(z).getY() == y + 1)
                                    eat.remove(z);
                            }
                            pole[x][y + 1] = 2;
                            fish.get(j).unsetEat();
                        } else if (pole[x][y - 1] == 1) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x, y - 1);
                            for (int z = 0; z < eat.size(); z++) {
                                if (eat.get(z).getX() == x && eat.get(z).getY() == y - 1)
                                    eat.remove(z);
                            }
                            pole[x][y - 1] = 2;
                            fish.get(j).unsetEat();
                        } else if (pole[x + 1][y] == 1) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x + 1, y);
                            for (int z = 0; z < eat.size(); z++) {
                                if (eat.get(z).getX() == x + 1 && eat.get(z).getY() == y)
                                    eat.remove(z);
                            }
                            pole[x + 1][y] = 2;
                            fish.get(j).unsetEat();
                        } else if (pole[x - 1][y] == 1) {
                            fish.get(j).setX1Y1(x, y);
                            pole[x][y] = 0;
                            fish.get(j).setXY(x - 1, y);
                            for (int z = 0; z < eat.size(); z++) {
                                if (eat.get(z).getX() == x - 1 && eat.get(z).getY() == y)
                                    eat.remove(z);
                            }
                            pole[x - 1][y] = 2;
                            fish.get(j).unsetEat();
                        }
                    }
                }
            }
            /*Потомки*/
            for (int j = 0; j < shark.size(); j++) {
                if (shark.get(j).getBaby() == maxBabyShark) {
                    shark.add(new Shark());
                    shark.get(shark.size()-1).setXY(shark.get(j).getX1(), shark.get(j).getY1());
                    pole[shark.get(j).getX1()][shark.get(j).getY1()] = 3;
                    shark.get(j).resetBaby();
                    shark.get(j).resetEat();
                }
            }
            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j).getBaby() == maxBabyFish) {
                    fish.add(new Fish());
                    fish.get(fish.size()-1).setXY(fish.get(j).getX1(), fish.get(j).getY1());
                    pole[fish.get(j).getX1()][fish.get(j).getY1()] = 2;
                    fish.get(j).resetBaby();
                    fish.get(j).resetEat();
                }
            }
            for(int j=0;j<shark.size();j++){
                shark.get(j).setEat();
                shark.get(j).setAge();
                shark.get(j).setBaby();
            }
            for(int j=0;j<fish.size();j++){
                fish.get(j).setEat();
                fish.get(j).setAge();
                fish.get(j).setBaby();
            }
            if(eat.size()<eatCount/2){
                for(int j =0;j<eatCount/2;j++){
                    eat.add(new Eat());
                    while(true) {
                        int x = rand.nextInt(poleX);
                        int y = rand.nextInt(poleY);
                        boolean caseEmpty = (pole[x][y] == 0)&&x!=0&&y!=0;
                        if(caseEmpty){eat.get(j).setXY(x,y);pole[eat.get(j).getX()][eat.get(j).getY()]=1; break;}
                    }
                }
            }
            //debug(pole);
            String text = i+" "+shark.size()+" "+fish.size()+" \r\n";
            try(FileOutputStream fos=new FileOutputStream("C://SomeDir//notes.txt",true))
            {
                // перевод строки в байты
                byte[] buffer = text.getBytes();
                fos.write(buffer, 0, buffer.length);
            }
            catch(IOException ex){

                System.out.println(ex.getMessage());
            }
        }


    }
   static void debug(int [][] pole){
        for(int c=0;c<poleX;c++){
            for(int j =0;j<poleY;j++){
                System.out.print(pole[c][j]);
            }
            System.out.println();
        }
    }

}
