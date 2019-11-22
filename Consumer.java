import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Queue;

/*
 * Class for consumers
 *  Luke Brandon
 *  010817623
 */
class Consumer extends Thread{
    Queue<Long> buffer;
    Semaphore mutex;
    Semaphore empty;    
    Semaphore full;

    public Consumer(Queue<Long> buffer, Semaphore mutex, Semaphore empty, Semaphore full){
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    public void run(){
        int i = 0;
        while(i < 100){
            // Random slep time between 0 and 500 milliseconds
            Random rand = new Random();
            long sleepTime = rand.nextInt(500);

            try{
                Thread.sleep(sleepTime);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            // Tries to consume if it can
            try{
                empty.acquire();
                mutex.acquire();

                long consumed = buffer.remove();
                System.out.println("    Consumed " + consumed);
                  
            } catch (InterruptedException e){
                System.out.println("Could not consume");
            }

            full.release();
            mutex.release();

            // Iterate counter
            i++;
        }
    }




}