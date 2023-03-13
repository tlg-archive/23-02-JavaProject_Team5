package com.wheel.sys;

import com.wheel.resources.wedge.Wedge;
import com.wheel.resources.wedge.WedgeMoney;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Wheel {

    List<WedgeMoney> wedges = Stream.generate(() -> WedgeMoney.US_500)
            .limit(3)
            .collect(Collectors.toList());

    Map<Integer, Wedge> baseValue = new HashMap<>();

    public Wedge spin(){
        Wedge result = null;


        // When wheel spins, pick at random and item in the ArrayList
        return result;

    }


}