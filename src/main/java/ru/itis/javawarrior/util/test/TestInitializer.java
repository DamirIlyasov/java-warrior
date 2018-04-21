package ru.itis.javawarrior.util.test;


import ru.itis.javawarrior.util.action.ActionContoller;
import ru.itis.javawarrior.util.action.ActionControllerImpl;

public abstract class TestInitializer implements ControlInteface {

    protected ActionContoller actionContoller;

    @Override
    public void run() {
        actionContoller = new ActionControllerImpl();
        main();
    }

    abstract void main();
}
