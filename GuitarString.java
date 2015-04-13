/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuitarHeroine;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author atmarsh
 */
public class GuitarString {

    private final double samplingRate = 44100;
    private RingBuffer buffer;
    private int ticLog = 0;
    private double energyDecayFactor = 0.994;
    private double frequency;

    /**
     * Create a guitar string of the given frequency, using a sampling rate of
     * 44,100
     *
     * @param frequency Frequency of the guitar string
     */
    public GuitarString(double frequency) {
        this.frequency = frequency;
        buffer = new RingBuffer((int) (samplingRate / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0);
        }
    }

    /**
     * Create a guitar string whose size and initial values are given by the
     * array
     *
     * @param init Array of initial values
     */
    public GuitarString(double[] init) {
        buffer = new RingBuffer(init.length);
        for (double i : init) {
            buffer.enqueue((int) i);
        }
    }

    public void reset() {
        buffer = new RingBuffer((int) (samplingRate / frequency));
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(0);
        }
    }
    
    /**
     * Sets the buffer to white noise.
     */
    public void pluck() {
        Random rand = new Random();
        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.dequeue();
        }
        for (int i = 0; i < buffer.capacity(); i++) {
            double n = rand.nextDouble() - 0.5;
            buffer.enqueue(n);
        }
    }

    /**
     * Advances the simulation by one time step.
     */
    public void tic() {
        double firstSample = buffer.dequeue();
//        if(newSample != 0) {
//            System.out.println(newSample);
//        }
        double secondSample = buffer.peek();
//        if(newSample != 0) {
//            System.out.println(newSample);
//        }
        double newSample = energyDecayFactor * (0.5 * (firstSample + secondSample));
//        System.out.println(energyDecayFactor);
//        if(newSample != 0) {
//            System.out.println(newSample);
//        }
        buffer.enqueue(newSample);
        
        ticLog++;
    }

    /**
     * Returns the current sound sample.
     *
     * @return current sound sample
     */
    public double sample() {
//        buffer.printBuffer();
        return buffer.peek();
    }

    /**
     * Returns the amount of time that has elapsed.
     *
     * @return the number of tics elapsed.
     */
    public int time() {
        return ticLog;
    }
}
