import domain.*;

import java.util.*;

public class TaxiTest {

    public static void main(String[] args) {
        System.out.println("===========택시 2대 생성=============");
        Taxi 개인택시 = new Taxi(UUID.randomUUID().toString());
        Taxi 범인택시 = new Taxi(UUID.randomUUID().toString());

        Passenger 짱구 = new Passenger(2, "서울역", "짱구");
        Passenger 철구 = new Passenger(2, "서울역", "철구");

        System.out.println("===========승객 탑승=============");
        개인택시.pickUp(짱구);
        개인택시.pickUp(철구);
        개인택시.printTaxi();

        System.out.println("===========주유량 확인 및 누적 요금=============");
        개인택시.moveStart();
        개인택시.oilChange(-80);
        개인택시.printOil();

        System.out.println("===========최대 승객수 초과 =============");
        for (int i =1; i < 6; i++) {
            개인택시.pickUp(new Passenger(2, "서울역", "짱구"+i+"번째 아들"));
        }
        개인택시.getOut();

        System.out.println("===========승객 3명 태우기 =============");
        개인택시.pickUp(new Passenger(12, "구로 디지털 단지역", "짱구"));
        개인택시.pickUp(new Passenger(12, "구로 디지털 단지역", "짱구엄마"));
        개인택시.pickUp(new Passenger(12, "구로 디지털 단지역", "짱구아빠"));
        개인택시.printTaxi();

        System.out.println("===========요금결제 =============");
        개인택시.oilChange(-20);
        개인택시.printOil();
        개인택시.moveStart();








    }

}