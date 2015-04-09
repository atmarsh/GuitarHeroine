/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GuitarHeroine;

/**
 *
 * @author atmarsh
 */
public class RingBuffer {
    
    private double [] buffer;//array where items in buffer are stored
    private int firstIndex;//index of first value
    private int lastIndex;//index of last value
    
    /**
     * Create an empty ring buffer, with a given maximum capacity
     * @param capacity the maximum size of the ring buffer
     */
    public RingBuffer(int capacity) {
        buffer = new double[capacity];
        firstIndex = 0;
        lastIndex = 0;
    }
    
    /**
     * Returns the number of items currently in the buffer
     * @return number of items in the buffer
     */
    public int size() {
        return lastIndex - firstIndex;
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
        return size() == buffer.length;
    }
    
    /**
     * Adds an item to the queue
     * @param newValue item to be added to the queue
     */
    public void enqueue(double newValue) throws BufferFullException {
        if(isFull()) {
            throw new BufferFullException();
        }
        buffer[lastIndex] = newValue;
        lastIndex++;
    }
    
    /**
     * Delete and returns the first item from the queue
     * @return the first item in the queue
     */
    public double dequeue() throws BufferEmptyException{
        if(isEmpty()) {
            throw new BufferEmptyException();
        }
        double firstItem = peek();
        firstIndex++;
        checkIndexes();
        return firstItem;
    }
    
    /**
     * Return (but does not delete) the first item in the queue
     * @return the first item in the queue
     */
    public double peek() throws BufferEmptyException{
        if(isEmpty()) {
            throw new BufferEmptyException();
        }
        return buffer[firstIndex];
    }

    /**
     * This method checks to make sure that both indexes are inside of the scope 
     * of the buffer. If one index does not exist in the buffer, the index is 
     * wrapped around.
     */
    private void checkIndexes() {
        if(firstIndex >= buffer.length) {
            firstIndex -= buffer.length;
        }
        
        if(lastIndex >= buffer.length) {
            lastIndex -= buffer.length;
        }
    }
    
    /**
     * Special Exception for when the buffer is full.
     */
    private class BufferFullException extends Exception {
        
        public BufferFullException() {
            
        }
        
        public BufferFullException(String message) {
            super(message);
        }
    }
    
    /**
     * Special exception for when the buffer is empty.
     */
    private class BufferEmptyException extends Exception {
        
        public BufferEmptyException() {
            
        }
        
        public BufferEmptyException(String message) {
            super(message);
        }
    }
}
