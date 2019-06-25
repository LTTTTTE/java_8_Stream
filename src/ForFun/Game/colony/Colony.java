package ForFun.Game.colony;


import java.util.Hashtable;
import java.util.Optional;
import java.util.Scanner;

public class Colony {

    private String name;

    private Long wood;
    private Long food;
    private Long stone;
    private Long iron;
    private Long gold;

    private Hashtable<Integer,Integer> workTable = new Hashtable<>();

    public Colony() {
        this.wood = 1000L;  workTable.put(1,0);
        this.food = 1000L;  workTable.put(2,0);
        this.stone = 1000L; workTable.put(3,0);
        this.iron = 1000L;  workTable.put(4,0);
        this.gold = 1000L;  workTable.put(5,0);
    }

    public Colony(String name){
        this();
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    public Long getWood() {
        return wood;
    }

    public void setWood(Long wood) {
        this.wood = wood;
    }

    public Long getFood() {
        return food;
    }

    public void setFood(Long food) {
        this.food = food;
    }

    public Long getStone() {
        return stone;
    }

    public void setStone(Long stone) {
        this.stone = stone;
    }

    public Long getIron() {
        return iron;
    }

    public void setIron(Long iron) {
        this.iron = iron;
    }

    public Long getGold() {
        return gold;
    }

    public void setGold(Long gold) {
        this.gold = gold;
    }

    public void addWood(Long wood){
        this.wood += wood;
    }
    public boolean subWood(Long wood){
        if(this.wood >= wood) {
            this.wood -= wood;
            return true;
        }
        else return false;
    }

    public void addFood(Long food){
        this.food += food;
    }
    public boolean subFood(Long food){
        if(this.food >= food) {
            this.food -= food;
            return true;
        }
        else return false;
    }

    public void addStone(Long stone){
        this.stone += stone;
    }
    public boolean subStone(Long stone){
        if(this.stone >= stone) {
            this.stone -= stone;
            return true;
        }
        else return false;
    }

    public void addIron(Long iron){
        this.iron += iron;
    }
    public boolean subIron(Long iron){
        if(this.iron >= iron) {
            this.iron -= iron;
            return true;
        }
        else return false;
    }

    public void addGold(Long gold){
        this.gold += gold;
    }
    public boolean subGold(Long gold){
        if(this.gold >= gold) {
            this.gold -= gold;
            return true;
        }
        else return false;
    }

    public boolean putSlaves(Integer slaves){

        System.out.println("1.����� 2.���� 3.����");

        Scanner scanner = new Scanner(System.in);
        Optional<String> temp = Optional.ofNullable(scanner.nextLine());
        try {
            Integer x = temp.map(Integer::parseInt).orElse(-1);
            workTable.replace(x, workTable.get(x) + slaves);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public boolean outSlaves(Integer slaves){

        System.out.println("1.����� 2.���� 3.����");

        Scanner scanner = new Scanner(System.in);
        Optional<String> temp = Optional.ofNullable(scanner.nextLine());
        try {
            Integer x = temp.map(Integer::parseInt).orElse(-1);
            if(workTable.get(x) < slaves) return false;
            workTable.replace(x, workTable.get(x) - slaves);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    public Hashtable<Integer, Integer> getWorkTable() {
        return workTable;
    }

    public void showColonyInfo(){
        System.out.println("[�Ĺ�������]");
        System.out.println("�̸� : "+ this.getName());
        System.out.println("ä������ ���緮 : " + this.getWood() + "  ����� ���ϴ� �뿹�� : "+this.getWorkTable().get(1));
        System.out.println("ä������ �ķ�   : " + this.getFood() + "  ������ �ϴ� �뿹�� : "+this.getWorkTable().get(2));
        System.out.println("ä������ ���緮 : " + this.getStone() + "  ���� ���ϴ� �뿹�� : "+this.getWorkTable().get(3));
        System.out.println("ä������ ö���� : " + this.getIron());
        System.out.println("ä������ �ݱ��� : " + this.getGold());

    }
}
