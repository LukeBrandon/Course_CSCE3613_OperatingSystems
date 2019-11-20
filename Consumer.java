import java.util.Random;
import java.util.Queue;

/*
 * Class for consumers
 */
class Consumer extends Thread{
    Queue<Long> buffer;

    public Consumer(Queue<Long> buffer){
        this.buffer = buffer;
    }

    public void run(){
        System.out.println("Hello from a thread!");

        while(true){
            // Gets random number between 0 and 500 milliseconds
            Random rand = new Random();
            long sleepTime = rand.nextInt(500);

            System.out.println("Sleeping for " + sleepTime);

            try{
                Thread.sleep(sleepTime);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            System.out.println("Done sleeping.");
            long consumed = buffer.remove();
            System.out.println("Consumed " + consumed);
        }
    }

}