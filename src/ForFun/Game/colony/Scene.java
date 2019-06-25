package ForFun.Game.colony;

import java.util.Optional;
import java.util.Scanner;

public class Scene implements Runnable{

    private Player player;
    private Scanner scanner = new Scanner(System.in);

    public Scene(){
        player = Player.getInstance();
    }

    @Override
    public void run() {

        int idx = 1;

        new Thread(()->{
            while(true) {
                try {
                    Thread.sleep(1000);
                }
                catch (Exception e){
                    System.out.println("시간이 이상하게갑니다.");
                }
                player.work();
            }
        }).start();

        while(true){
            System.out.println("WELCOME");
            System.out.println("가진 목재량 : " + player.getWood() + "  목재소 일하는 노예수 : "+player.getWorkTable().get(1));
            System.out.println("가진 식량   : " + player.getFood() + "  농장일 하는 노예수 : "+player.getWorkTable().get(2));
            System.out.println("가진 석재량 : " + player.getStone() + "  광산 일하는 노예수 : "+player.getWorkTable().get(3));
            System.out.println("가진 철광석 : " + player.getIron());
            System.out.println("가진 금광석 : " + player.getGold());
            System.out.println("1.식민지관리");
            Optional<String> temp = Optional.ofNullable(scanner.nextLine());
            try {
                Integer x = temp.map(Integer::parseInt).orElse(-1);
                switch (x){

                    case 1:
                        player.showColonyListUI();
                        break;
                    case 2:
                        System.out.println("2");break;
                    case 3:
                        System.out.println("3");break;
                    case 4:
                        System.out.println("4");break;
                    case 5:
                        System.out.println("5");break;
                    default:
                        System.out.println("def");
                }
            }
            catch (Exception e){
                System.out.println("def");
            }
        }
    }
}
