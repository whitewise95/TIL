package domain;

public class Bus extends PublicTransport {

    private int pay;  //요금

    public Bus(String num) {
        super(num);
        this.maxPassanger = 30;
        this.pay = 0;
        System.out.println(num + "번 버스가 생성되었습니다.");
    }

    @Override
    public void payPrint() {
        System.out.println("탑승 승객 수 = " + this.currentPassanger);
        System.out.println("잔여 승객 수 = " + this.maxPassanger);
        System.out.println("요금 확인= " + this.pay);
    }

    @Override
    public void moveStart() {
    }

    @Override
    public void stateChange() {
        if(this.oil <= 60) {
            this.state = false;
        } else {
            this.state = true;
        }
    }

    @Override
    public void pickUp(Passenger passenger) {
        if(!this.state) {
            printStats();
        }
        if(this.maxPassanger == 0) {
            System.out.println("최대 승객 수 초과");
            return;
        }
        this.maxPassanger--;
        this.currentPassanger++;
        this.pay += 1000;
    }

    @Override
    public void speedChange() {

    }

    @Override
    public void oilChange(int oil){
        this.oil += (oil);
        stateChange();
    }

    @Override
    public void printOil(){
        System.out.println("주유량 = " + this.oil);
        if(this.oil <= 5) {
            System.out.println("주유 필요");
        }
    }

    @Override
    public void printStats(){

        System.out.println("상태 = " + (this.state ? "운행중" : "차고지행"));
    }
}
