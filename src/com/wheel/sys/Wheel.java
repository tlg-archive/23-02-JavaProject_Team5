package com.wheel.sys;


import com.wheel.resources.wedge.*;

import java.util.*;


public class Wheel {


    List<Wedge> wheel = new ArrayList<>();



    public Wheel() {
        wheel.add(WedgeMoney.TOP_VALUE);
        wheel.add(WedgeMoney.US_500);
        wheel.add(WedgeMoney.US_500);
        wheel.add(WedgeMoney.US_500);
        wheel.add(WedgeMoney.US_550);
        wheel.add(WedgeMoney.US_550);
        wheel.add(WedgeMoney.US_600);
        wheel.add(WedgeMoney.US_600);
        wheel.add(WedgeMoney.US_650);
        wheel.add(WedgeMoney.US_650);
        wheel.add(WedgeMoney.US_700);
        wheel.add(WedgeMoney.US_700);
        wheel.add(WedgeMoney.US_750);
        wheel.add(WedgeMoney.US_750);
        wheel.add(WedgeMoney.US_800);
        wheel.add(WedgeMoney.US_800);
        wheel.add(WedgeMoney.US_850);
        wheel.add(WedgeMoney.US_850);
        wheel.add(WedgeMoney.US_900);
        wheel.add(WedgeMoney.US_900);
        wheel.add(WedgeBad.BANKRUPT);
        wheel.add(WedgeBad.BANKRUPT);
        wheel.add(WedgeBad.LOSE_TURN);
        wheel.add(WedgeGood.FREE_SPIN);


    }
    public Wedge getRandomWedge() {

        // Generate a random index
        int randomIndex = new Random().nextInt(wheel.size());

        // Return the corresponding wedge
        return wheel.get(randomIndex);
    }


}





