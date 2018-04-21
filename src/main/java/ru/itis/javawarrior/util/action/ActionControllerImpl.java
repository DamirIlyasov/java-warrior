package ru.itis.javawarrior.util.action;

public class ActionControllerImpl implements ActionContoller {

    @Override
    public void walk(int tiles) {
        System.out.println("walked for " + tiles + " tiles");
    }

    @Override
    public void hit(int hpTaken) {
        System.out.println("hit for " + hpTaken + " hp");
    }

    @Override
    public void doBackflip(int awesomenessPercent) {
        System.out.println("WOW! THAT WAS " + awesomenessPercent + "% AWESOME!!!");
    }
}
