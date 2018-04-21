package ru.itis.javawarrior.util.action;

public interface ActionContoller {
    void walk(int tiles);

    void hit(int hpTaken);

    void doBackflip(int awesomenessPercent);
}
