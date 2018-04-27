//package ru.itis.javawarrior.util.compile;
//
//import ru.itis.javawarrior.entity.GameResult;
//import ru.itis.javawarrior.exception.InvalidActionException;
//import ru.itis.javawarrior.exception.StageCompletedException;
//import ru.itis.javawarrior.service.impl.ActionServiceImpl;
//
//
//public class CompiledClass implements Runner {
//
//    private final ActionServiceImpl actionService = new ActionServiceImpl();
//
//    @Override
//    public GameResult main() {
//        try {
//            walk();
//            attack();
//            jump();
//        }
//        catch (InvalidActionException actionException) {
//            return new GameResult(actionService.getResponseActions(), false);
//        }
//        catch (StageCompletedException stageCompleteExeption) {
//            return new GameResult(actionService.getResponseActions(), true);
//        }
//        // stage failed if StageCompleteException wasn't caught
//        return new GameResult(actionService.getResponseActions(), false);
//    }
//
//    private void walk() {
//        actionService.walk();
//    }
//
//    private void attack() {
//        actionService.attack();
//    }
//
//    private void jump() {
//        actionService.jump();
//    }
//}
