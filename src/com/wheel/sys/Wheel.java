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



    }
    public Wedge getRandomWedge() {

        // Generate a random index
        int randomIndex = new Random().nextInt(wheel.size());

        // Return the corresponding wedge
        return wheel.get(randomIndex);
    }


}





