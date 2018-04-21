//package ru.itis.javawarrior.util.compile;
//
//import java.util.List;
//
//import ru.itis.javawarrior.service.impl.ActionServiceImpl;
//import ru.itis.javawarrior.util.ActionEnum;
//
//
//public class CompiledClass implements Runner {
//
//    private final ActionServiceImpl actionService = new ActionServiceImpl();
//
//    @Override
//    public List<ActionEnum> main() {
//        walk();
//        shoot();
//        jump();
//        return actionService.responseActions;
//    }
//
//    private void walk() {
//        actionService.walk();
//    }
//
//    private void shoot() {
//        actionService.shoot();
//    }
//
//    private void jump() {
//        actionService.jump();
//    }
//}
