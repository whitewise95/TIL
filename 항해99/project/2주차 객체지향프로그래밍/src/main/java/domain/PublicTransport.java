package domain;

public abstract class PublicTransport {

    public String num;  //차번호
    public int currentSpeed;  //현재속도
    public int changeSpeed;  //속도변경
    public int currentPassanger; //현재 승객수
    public boolean state; //상태
    public int oil;  //가스
    public int maxPassanger;  //최대승객수


    public PublicTransport(String num) {
        this.num = num;
        this.currentSpeed = 0;
        this.changeSpeed = 0;
        this.currentPassanger = 0;
        this.state = true;
        this.oil = 100;
    }

    abstract void payPrint();

    abstract void moveStart( );

    abstract void stateChange();

    abstract void pickUp(Passenger passenger);

    abstract void speedChange( );

    abstract void oilChange(int oil);

    abstract void printOil();

    abstract void printStats();
}
