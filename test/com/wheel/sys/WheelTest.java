package com.wheel.sys;

import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeBad;
import com.wheel.resources.wedge.WedgeGood;
import com.wheel.resources.wedge.WedgeMoney;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WheelTest {

    @Test
    public void getRandomWedge() {
        Wheel wheel = new Wheel();
        List<Wedge> money = new ArrayList<>();
        List<Wedge> bad = new ArrayList<>();
        List<Wedge> good = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Wedge wedge = wheel.getRandomWedge();
            if (wedge instanceof WedgeMoney)
                money.add(wedge);
            if (wedge instanceof WedgeGood)
                good.add(wedge);
            if (wedge instanceof WedgeBad)
                bad.add(wedge);
        }
        System.out.println("Out of 10000:");
        System.out.println("Money: " + money.size());
        System.out.println("Good: " + good.size());
        System.out.println("Bad: " + bad.size());
    }

}