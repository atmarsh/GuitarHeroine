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

    private final int samplingRate = 44100;
    private RingBuffer buffer;
    private int ticLog = 0;

    /**
     * Create a guitar string of the given frequency, using a sampling rate of
     * 44,100
     *
     * @param frequency Frequency of the guitar string
     */
    public GuitarString(double frequency) {
        int n = (int) (samplingRate / frequency);
        buffer = new RingBuffer(n);
        for (int i = 0; i < n; i++) {
            try {
                buffer.enqueue(0);
            } catch (RingBuffer.BufferFullException ex) {
                Logger.getLogger(GuitarString.class.getName()).log(Level.SEVERE, null, ex);
            }
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
            try {
                buffer.enqueue((int) i);
            } catch (RingBuffer.BufferFullException ex) {
                Logger.getLogger(GuitarString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Sets the buffer to white noise.
     */
    public void pluck() {
        Random rand = new Random();
        for (int i = 0; i < buffer.size(); i++) {
            try {
                buffer.dequeue();
            } catch (RingBuffer.BufferEmptyException ex) {
                Logger.getLogger(GuitarString.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                double n = rand.nextDouble() / 2;
                if(rand.nextBoolean()) {
                    n *= -1;
                }
                buffer.enqueue(n);
            } catch (RingBuffer.BufferFullException ex) {
                Logger.getLogger(GuitarString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Advances the simulation by one time step.
     */
    public void tic() {
        ticLog++;
    }

    /**
     * Returns the current sound sample.
     *
     * @return current sound sample
     */
    public double sample() {
        try {
            return buffer.peek();
        } catch (RingBuffer.BufferEmptyException ex) {
            Logger.getLogger(GuitarString.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
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
