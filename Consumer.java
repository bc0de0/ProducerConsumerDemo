import java.util.LinkedList;

public class Consumer implements Runnable{
    private LinkedList<Integer> buffer;

    public Consumer(LinkedList<Integer> buffer) {
        this.buffer = buffer;
    }

    @Override
    public void run() {
        while (true){
            synchronized (buffer){
                while (buffer.isEmpty()){
                    try {
                        buffer.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                int number = buffer.removeLast();
                System.out.println("Consumed: " + number);
                buffer.notify();
            }
        }
    }
}
