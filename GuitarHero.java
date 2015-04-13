/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuitarHeroine;

import java.util.ArrayList;

/**
 *
 * @author atmarsh
 */
public class GuitarHero {

    private static String keyboardString = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    private static ArrayList<GuitarString> strings;

    public static void main(String [] args) {
        strings = new ArrayList<>();
        for (int i = 0; i < keyboardString.length(); i++) {
            strings.add(new GuitarString(440 * Math.pow(1.05956, i - 24)));
        }

        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                if(keyboardString.contains(key + "")) {
                    int index = keyboardString.indexOf(key);
                    strings.get(index).pluck();
                }
            }

            double sample = 0;
            for (GuitarString string : strings) {
                sample += string.sample();
                string.tic();
            }

            StdAudio.play(sample);
        }
    }
}
