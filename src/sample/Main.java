package sample;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static java.awt.SystemColor.text;

public class Main extends Exception{

    public static void main(String[] args)  {
        Random rand = new Random();
        int [][] pole = new int [20][20];
        ArrayList<Shark> shark = new ArrayList<>();ArrayList<Fish> fish = new ArrayList<>(); ArrayList<Eat> eat = new ArrayList<>();
        for(int i =0;i<10;i++){
            shark.add(new Shark());
            while(true) {
                int x = rand.nextInt(20);
                int y = rand.nextInt(20);
                boolean caseEmpty = pole[x][y] == 0;
                if(caseEmpty){shark.get(i).setXY(x,y);pole[shark.get(i).getX()][shark.get(i).getY()]=3; break;}
            }
        }
        for(int i =0;i<100;i++){
            fish.add(new Fish());
            while(true) {
                int x = rand.nextInt(20);
                int y = rand.nextInt(20);
                boolean caseEmpty = pole[x][y] == 0;
                if(caseEmpty){fish.get(i).setXY(x,y);pole[fish.get(i).getX()][fish.get(i).getY()]=2; break;}
            }
        }
        for(int i =0;i<290;i++){
            eat.add(new Eat());
            while(true) {
                int x = rand.nextInt(20);
                int y = rand.nextInt(20);
                boolean caseEmpty = pole[x][y] == 0;
                if(caseEmpty){eat.get(i).setXY(x,y);pole[eat.get(i).getX()][eat.get(i).getY()]=1; break;}
            }
        }
        for(int i=0;i<300;i++) {
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
                /*Смерть*/
            for (int j = 0; j < shark.size(); j++) {
                if (shark.get(j).getEat() > 14 | shark.get(j).getAge() > 20) {
                    int x = shark.get(j).getX();
                    int y = shark.get(j).getY();
                    pole[x][y] = 0;
                    shark.remove(j);
                }
            }
            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j).getEat() > 9 | fish.get(j).getAge() > 13) {
                    int x = fish.get(j).getX();
                    int y = fish.get(j).getY();
                    pole[x][y] = 0;
                    fish.remove(j);
                }
            }
                /*Передвижение*/
            for (int j = 0; j < shark.size(); j++) {
                if (shark.get(j).getAge() < 20) {
                    int x = shark.get(j).getX();
                    int y = shark.get(j).getY();
                    if (x > 0 & x < 19 & y > 0 & y < 19) {
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
                if (fish.get(j).getAge() < 13) {
                    int x = fish.get(j).getX();
                    int y = fish.get(j).getY();
                    if (x > 0 & x < 19 & y > 0 & y < 19) {
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
                if (shark.get(j).getBaby() >= 15) {
                    shark.add(new Shark());
                    shark.get(shark.size()-1).setXY(shark.get(j).getX1(), shark.get(j).getY1());
                    pole[shark.get(j).getX1()][shark.get(j).getY1()] = 3;
                    shark.get(j).resetBaby();
                    shark.get(j).resetEat();
                }
            }
            for (int j = 0; j < fish.size(); j++) {
                if (fish.get(j).getBaby() >= 10) {
                    fish.add(new Fish());
                    fish.get(fish.size()-1).setXY(fish.get(j).getX1(), fish.get(j).getY1());
                    pole[fish.get(j).getX1()][fish.get(j).getY1()] = 2;
                    fish.get(j).resetBaby();
                    fish.get(j).resetEat();
                }
            }
            debug(pole);
            String text = i+" "+shark.size()+" "+fish.size()+" "+eat.size()+"\n";
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
        for(int c=0;c<20;c++){
            for(int j =0;j<20;j++){
                System.out.print(pole[c][j]);
            }
            System.out.println();
        }
    }

}
