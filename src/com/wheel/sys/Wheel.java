package com.wheel.sys;


import com.wheel.resources.wedge.*;

import java.util.*;


public class Wheel {


    private List<Wedge> wheel = new ArrayList<>();


    public Wheel() {
        wheel.add(WedgeMoney.TOP_VALUE);
        wheel.add(WedgeMoney.$500);
        wheel.add(WedgeMoney.$500);
        wheel.add(WedgeMoney.$500);
        wheel.add(WedgeMoney.$550);
        wheel.add(WedgeMoney.$550);
        wheel.add(WedgeMoney.$600);
        wheel.add(WedgeMoney.$600);
        wheel.add(WedgeMoney.$650);
        wheel.add(WedgeMoney.$650);
        wheel.add(WedgeMoney.$700);
        wheel.add(WedgeMoney.$700);
        wheel.add(WedgeMoney.$750);
        wheel.add(WedgeMoney.$750);
        wheel.add(WedgeMoney.$800);
        wheel.add(WedgeMoney.$800);
        wheel.add(WedgeMoney.$850);
        wheel.add(WedgeMoney.$850);
        wheel.add(WedgeMoney.$900);
        wheel.add(WedgeMoney.$900);
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





