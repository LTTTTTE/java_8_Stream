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
                    System.out.println("�ð��� �̻��ϰ԰��ϴ�.");
                }
                player.work();
            }
        }).start();

        while(true){
            System.out.println("WELCOME");
            System.out.println("���� ���緮 : " + player.getWood() + "  ����� ���ϴ� �뿹�� : "+player.getWorkTable().get(1));
            System.out.println("���� �ķ�   : " + player.getFood() + "  ������ �ϴ� �뿹�� : "+player.getWorkTable().get(2));
            System.out.println("���� ���緮 : " + player.getStone() + "  ���� ���ϴ� �뿹�� : "+player.getWorkTable().get(3));
            System.out.println("���� ö���� : " + player.getIron());
            System.out.println("���� �ݱ��� : " + player.getGold());
            System.out.println("1.�Ĺ�������");
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
