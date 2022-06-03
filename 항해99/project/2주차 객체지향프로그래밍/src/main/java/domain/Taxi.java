package domain;

public class Taxi extends PublicTransport {

    private String goal;  //목적지
    private int basicDistance; // 기본거리
    private int goalDistance; // 목적지까지거리
    private int pay;  //기본요금
    private int accumulatedPay;  //기본요금
    private int addPay; //거리당 요금

    public Taxi(String num) {
        super(num);
        this.goal = "";
        this.basicDistance = 1;
        this.goalDistance = 0;
        this.pay = 3000;
        this.addPay = 1000;
        this.maxPassanger = 4;
        this.accumulatedPay =0;
        System.out.println(num + "번 택시가 활동 중입니다.");
        System.out.println("주유량 "+ this.oil);
        System.out.println("상태 "+ (this.state ? "일반" : "운행불가"));
    }

    @Override
    public void payPrint() {

    }

    @Override
    public void moveStart() {
        this.accumulatedPay += this.pay + this.addPay * (this.goalDistance - this.basicDistance);
        this.currentPassanger = 0;
        this.goal = "";
        this.state = true;
        this.goalDistance = 0;
        System.out.println("누적 요금 = " + accumulatedPay);
    }

    @Override
    public void stateChange() {

    }

    @Override
    public void pickUp(Passenger passenger) {
        if(currentPassanger == 4) {
            System.out.println("최대 승객 수 초과");
            return;
        }
        this.currentPassanger++;
        this.goal = passenger.getGoal();
        this.state = false;
        this.goalDistance = passenger.getDestination();

    }

    @Override
    public void speedChange() {

    }

    @Override
    public void oilChange(int oil) {
        this.oil += (oil);
    }

    @Override
    public void printOil() {
        System.out.println("주유량 = " + this.oil);
        if(this.oil == 0) {
            this.state = false;
            System.out.println("상태 = " + (this.state ? "일반" : "운행불가"));
        }
    }

    @Override
    public void printStats() {
        System.out.println("상태 = " + (this.state ? "일반" : "운행불가"));
        System.out.println("주요 필요");
        System.out.println("주유량 0");
        System.out.println("누적 요금 19000");
    }

    public void printTaxi() {
        System.out.println("탑승 승객 수 = " + this.currentPassanger);
        System.out.println("잔여 승객 수 = " + (this.maxPassanger - this.currentPassanger));
        System.out.println("기본요금 확인 = " + this.pay);
        System.out.println("목적지 = " + this.goal);
        System.out.println("지불 요금  = " + (this.pay + this.addPay * (this.goalDistance - this.basicDistance)));
        System.out.println("상태 = " + (this.state ? "일반" : "운행중"));
    }

    public void getOut() {
        this.currentPassanger = 0;
        this.goal = "";
        this.state = true;
        this.goalDistance = 0;
    }
}
