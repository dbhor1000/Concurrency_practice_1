import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class BlockingQueue<T> {
    private final Queue<T> queue = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enqueue(T item) throws InterruptedException {
        while (queue.size() == capacity) {
            wait();
        }
        queue.add(item);
        sleep(1000);
        notifyAll();
    }

    public synchronized T dequeue() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        T item = queue.remove();
        notifyAll();
        return item;
    }

    public synchronized int size() {
        return queue.size();
    }
}