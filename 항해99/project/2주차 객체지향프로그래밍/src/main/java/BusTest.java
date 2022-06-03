import domain.*;

import java.util.*;

public class BusTest {

    public static void main(String[] args) {
        System.out.println("============각 Bus 번호 다른지 확인====================");
        Bus 타요버스 = new Bus(UUID.randomUUID().toString());
        Bus 고속버스 = new Bus(UUID.randomUUID().toString());

        Passenger 짱구 = new Passenger(30, "가산 디지털 단지", "짱구");
        Passenger 철구 = new Passenger(30, "안양", "철구");

        System.out.println();
        System.out.println("================Bus요금확인 및 승객확인================");
        타요버스.pickUp(짱구);
        타요버스.pickUp(철구);
        타요버스.payPrint();


        System.out.println();
        System.out.println("===========주유량 변경==============");
        타요버스.oilChange(-50);
        타요버스.printOil();

        System.out.println();
        System.out.println("=============주유량 변경 및 상태변경================");
        타요버스.oilChange(10);
        타요버스.printOil();
        타요버스.printStats();
        타요버스.oilChange(20);


        System.out.println();
        System.out.println("======================================");
        for (int i =1; i < 30; i++) {
            타요버스.pickUp(new Passenger(30, "가산 디지털 단지", "짱구"+i+"번째 아들"));
        }


        for (int i =0; i < 5; i++) {
            if(i == 0){
                고속버스.pickUp(철구);
            } else {
                고속버스.pickUp(new Passenger(30, "안양", "철구"+i+"번째 아들"));
            }
        }

        System.out.println();
        System.out.println("======================================");
        고속버스.payPrint();


        System.out.println();
        System.out.println("======================================");
        고속버스.oilChange(-95);
        고속버스.printOil();
        고속버스.printStats();






    }
}