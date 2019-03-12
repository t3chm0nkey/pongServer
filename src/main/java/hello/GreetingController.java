package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.RestController;

import java.util.Timer;
import java.util.TimerTask;

@RestController
public class GreetingController {
    Player player1 = new Player();
    Player player2 = new Player();
    Ball ball = new Ball(new float[]{.5f,.5f,0});

    @MessageMapping("/player1")
    @SendTo("/topic/position1")
    public Player player1Input(String input) throws Exception {
        inputController(input, player1);
        return player1;
    }

    @MessageMapping("/player2")
    @SendTo("/topic/position2")
    public Player player2Input(String input) throws Exception {

        inputController(input, player2);

        return player2;
    }

    @MessageMapping("/scene")
    @SendTo("/topic/scene")
    public Ball getScene() throws Exception {
        return ball;
    }

    private void inputController(String input, Player player) {
        switch (input) {
            case ("up"):
                player.movePlayerUp();
                break;
            case ("down"):
                player.movePlayerDown();
                break;
            case("start"):
                StartTimer();
                break;
        }
    }

    private void StartTimer(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ball.move();
            }
        };
        long period  = 30L;
        timer.scheduleAtFixedRate(task,0,period);
    }

}
