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
public class RingBuffer {
    
    private ArrayList<Double> buffer;//array where items in buffer are stored
    private int maxSize;
    
    
    /**
     * Create an empty ring buffer, with a given maximum capacity
     * @param capacity the maximum size of the ring buffer
     */
    public RingBuffer(int capacity) {
        buffer = new ArrayList<>();
        maxSize = capacity;
    }
    
    /**
     * Returns the number of items currently in the buffer
     * @return number of items in the buffer
     */
    public int size() {
        return buffer.size();
    }
    
    /**
     * Returns the maximum number of values that can be stored in the Ring Buffer
     * @return size of the buffer
     */
    public int capacity() {
        return maxSize;
    }
    
    /**
     * Is the buffer empty (size equals zero)?
     * @return true if empty, false if not
     */
    public boolean isEmpty() {
        return size() == 0;
    }
    
    /**
     * Is the buffer full (size equals capacity)?
     * @return true if full, false if not
     */
    public boolean isFull() {
        return size() == maxSize;
    }
    
    /**
     * Adds an item to the queue
     * @param newValue item to be added to the queue
     */
    public void enqueue(double newValue) {
        if(isFull()) {
            System.err.println("Full");
            return;
        }
        buffer.add(newValue);
    }
    
    /**
     * Delete and returns the first item from the queue
     * @return the first item in the queue
     */
    public double dequeue() {
        if(isEmpty()) {
            System.err.println("Empty");
            return 0;
        }
        double result = peek();
        buffer.remove(result);
        return result;
    }
    
    /**
     * Return (but does not delete) the first item in the queue
     * @return the first item in the queue
     */
    public double peek() {
        if(isEmpty()) {
            System.err.println("Empty");
            return 0;
        }
        return buffer.get(0);
    }
    
    /**
     * Prints the entire buffer to the terminal window. Useful for debugging.
     */
    public void printBuffer() {
        System.out.print("[");
        for(double d : buffer) {
            System.out.print(d + ", ");
        }
        System.out.println("[");
    }
}
