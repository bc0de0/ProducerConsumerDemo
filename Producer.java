import java.util.LinkedList;

public class Producer implements Runnable{

    private LinkedList<Integer> buffer;
    private int max_size;

    public Producer(LinkedList<Integer> buffer, int max_size) {
        this.buffer = buffer;
        this.max_size = max_size;
    }

    @Override
    public void run() {
        while (true){
            synchronized (buffer){
                while (buffer.size() == max_size){
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int number = (int) (Math.random()*100);
                buffer.add(number);
                System.out.println("produced: " + number);
                buffer.notify();
            }
        }
    }
}
