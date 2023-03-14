package com.wheel.sys;

import com.apps.util.Prompter;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.*;

public class HostTest {
    Host host = null;

    @Before
    public void initialize(){
        host = new Host();
        host.generatePuzzle();

    }

    @Test
    public void getPlayers_shouldReturnListOfPlayers_matchingGetPlayersTextFile() throws FileNotFoundException {
        String file = "test/consoleInputs/getPlayers.txt";
        host.assignPrompter(new Prompter(new Scanner(new FileInputStream(String.valueOf(Path.of(file))))));

        List<Player> players = host.getPlayers();
        //enter 3 players
        //Sam, Sally, Susan
        assertEquals(players.size(), 3);
        assertEquals("Sam", players.get(0).getName());
        assertEquals("Susan", players.get(1).getName());
        assertEquals("Sally", players.get(2).getName());
    }

    @Test
    public void getRounds() throws FileNotFoundException {
        String file = "test/consoleInputs/getRounds.txt";
        host.assignPrompter(new Prompter(new Scanner(new FileInputStream(file))));
        assertEquals(4,host.getRounds());
    }
}