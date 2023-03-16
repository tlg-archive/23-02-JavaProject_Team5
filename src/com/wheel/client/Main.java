package com.wheel.client;

import com.apps.util.SplashApp;
import com.wheel.app.WheelOfFortune;

class Main implements SplashApp {
    public static void main(String[] args) {
        Main app = new Main();
        app.welcome("images/logo.jpg");
        app.start();
    }

    @Override
    public void start() {
        WheelOfFortune game = new WheelOfFortune();
        game.play();
    }
}