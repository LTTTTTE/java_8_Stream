package ForFun.Game.colony;

import java.util.*;

public class Player extends Colony{
    private Scanner scanner = new Scanner(System.in);
    private List<Colony> colonyList = new ArrayList<>();
    private Integer slaves;

    private Player(){
        this.setStone(0L);
        this.setIron(0L);
        this.setGold(0L);
        this.slaves = 5;
        this.colonyList.add(new Colony("ȨŸ��"));
    }

    public static Player getInstance(){
        return Init.player;
    }

    private static class Init{
        static final Player player = new Player();
    }


    public List<Colony> getColonyList() {
        return colonyList;
    }

    public Integer getSlaves() {
        return slaves;
    }

    public void addSlaves(Integer slaves){
        this.slaves += slaves;
    }

    public boolean subSlaves(Integer slaves){
        if(this.slaves>=slaves) {
            this.slaves -= slaves;
            return true;
        }
        else return false;
    }

    public Integer getColonySize() {
        return colonyList.size();
    }

    public void putSlaves(Colony colony){
        System.out.print("���ϴ� �뿹�� :");
        try {
            Optional<String> temp = Optional.ofNullable(scanner.nextLine());
            Integer x = temp.map(Integer::parseInt).orElse(Integer.MAX_VALUE);
            if (this.getSlaves() >= x) {
                this.subSlaves(x);
                if(colony.putSlaves(x)) {System.out.println("�뿹�߰��Ϸ�");}
                else {this.addSlaves(x);
                    System.out.println("�뿹�߰�����");};
            } else System.out.println("�뿹����");
        }
        catch(Exception e){
            System.out.println("����");
        }
    }

    public void outSlaves(Colony colony){
        System.out.print("���ϴ� �뿹�� :");
        try {
            Optional<String> temp = Optional.ofNullable(scanner.nextLine());
            Integer x = temp.map(Integer::parseInt).orElse(-1);
        if(colony.outSlaves(x)){
            System.out.println("�뿹���ͿϷ�");
            this.slaves += x;
        }
        else System.out.println("�뿹����");
        }
        catch(Exception e){
            System.out.println("����");
        }
    }

    public void showColonyListUI(){

        while(true) {
            Map<Integer, Colony> colonyMap = new HashMap<>();
            int idx = 1;

            System.out.println("[�Ĺ��� ����Ʈ] (���� �뿹�� : " + this.getSlaves() + ")");
            getColonyList().forEach(colony -> {
                System.out.println(idx + "." + colony.getName());
                colonyMap.put(idx, colony);
            });


            try {
                Optional<String> temp = Optional.ofNullable(scanner.nextLine());
                Integer x = temp.map(Integer::parseInt).orElse(-1);
                showColonyUI(colonyMap.get(x));
            } catch (Exception e) {
                break;
            }

        }
    }

    public void showColonyUI(Colony colony){
        while(true) {
            colony.showColonyInfo();
            System.out.println("1.�뿹�߰�");
            System.out.println("2.�뿹����");
            try {
                Optional<String> temp = Optional.ofNullable(scanner.nextLine());
                Integer x = temp.map(Integer::parseInt).orElse(-1);
                if (x == 1)
                    this.putSlaves(colony);
                else if (x == 2)
                    this.outSlaves(colony);
                else break;
            } catch (Exception e) {
                showColonyUI(colony);
            }
        }
    }

    public void work(){
        this.getColonyList().forEach(colony -> {
            colony.subWood(colony.getWorkTable().get(1).longValue());
            colony.subFood(colony.getWorkTable().get(2).longValue());
            colony.subStone(colony.getWorkTable().get(3).longValue());
            colony.subIron(colony.getWorkTable().get(4).longValue());
            colony.subGold(colony.getWorkTable().get(5).longValue());
            this.addWood(colony.getWorkTable().get(1).longValue());
            this.addFood(colony.getWorkTable().get(2).longValue());
            this.addStone(colony.getWorkTable().get(3).longValue());
            this.addIron(colony.getWorkTable().get(4).longValue());
            this.addGold(colony.getWorkTable().get(5).longValue());
        });
    }
}
