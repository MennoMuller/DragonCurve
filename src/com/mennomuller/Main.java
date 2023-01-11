package com.mennomuller;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        drawDragon(5);
        System.out.println();
        drawDragon(6);
        System.out.println();
        drawDragon(7);
        System.out.println();
        drawDragon(8);
        System.out.println();
        drawDragon(9);
    }

    public static void drawDragon(int level) {
        ArrayList<Direction> dragon = new ArrayList<>();
        dragon.add(Direction.E);
        for (int i = 0; i < level; i++) {
            dragon = iterateDragon(dragon);
        }
        renderDragon(dragon);
    }


    public static ArrayList<Direction> iterateDragon(ArrayList<Direction> previous) {
        ArrayList<Direction> newCurve = new ArrayList<>();
        boolean isUp = true;
        for (Direction d : previous) {
            if (isUp) {
                newCurve.add(Direction.valueOf(d.dir1));
                newCurve.add(Direction.valueOf(d.dir2));
            } else {
                newCurve.add(Direction.valueOf(d.dir2));
                newCurve.add(Direction.valueOf(d.dir1));
            }
            isUp = !isUp;
        }
        return newCurve;
    }

    public static void renderDragon(ArrayList<Direction> dragon) {
        ArrayList<StringBuilder> canvas = new ArrayList<>();
        canvas.add(new StringBuilder());
        int x = 0, y = 0;
        // drawing out the curve goes here
        for (Direction d : dragon) {
            x += d.prex;
            y += d.prey;
            if (y > canvas.size() - 1) {
                canvas.add(new StringBuilder());
            } else if (y == -1) {
                canvas.add(0, new StringBuilder());
                y++;
            }
            StringBuilder line = canvas.get(y);
            if(line.length()<x+1){
                line.append(" ".repeat(x+1-line.length()));
            } else if (x == -1){
                line.insert(0," ");
                x++;
            }
            line.replace(x,x+1, d.string);
            x += d.dx;
            y += d.dy;
        }
        //printing the canvas
        for (StringBuilder line : canvas) {
            System.out.println(line);
        }
    }

    public enum Direction {
        N("NW", "NE", "|", 0,0,0, -1),
        NE("N", "E", "/", 0,0,1, -1),
        E("NE", "SE", "_", 1,0,1, 0),
        SE("E", "S", "\\", 0,1,1, 0),
        S("SE", "SW", "|", 0, 1,0,0),
        SW("S", "W", "/", -1, 1,0,0),
        W("SW", "NW", "_", -1, 0,-1,0),
        NW("W", "N", "\\", -1,0,0, -1);
        public final String dir1, dir2, string;
        public final int prex, prey, dx, dy;

        Direction(String dir1, String dir2, String string, int prex, int prey, int dx, int dy) {
            this.dir1 = dir1;
            this.dir2 = dir2;
            this.string = string;
            this.dx = dx;
            this.dy = dy;
            this.prex = prex;
            this.prey = prey;
        }
    }
}
/*
   _|_|_|_|_
   _|_|_|_|_




 */


