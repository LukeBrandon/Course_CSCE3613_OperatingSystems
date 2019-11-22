import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.Queue;

/*
 * Class for producers
 *  Luke Brandon
 *  010817623
 */
class Producer extends Thread{
    Queue<Long> buffer;
    Semaphore mutex;
    Semaphore empty;
    Semaphore full;

    public Producer(Queue<Long> buffer, Semaphore mutex, Semaphore empty, Semaphore full){
        this.buffer = buffer;
        this.mutex = mutex;
        this.empty = empty;
        this.full = full;
    }

    public void run(){
        int i = 0;
        while(i < 100){
            // Random sleep time between 0 and 500 milliseconds
            Random rand = new Random();
            long sleepTime = rand.nextInt(500);
            
            try{
                Thread.sleep(sleepTime);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            // Tries to produce if it can
            try{
                full.acquire();
                mutex.acquire();

                int produced = rand.nextInt(100000);
                buffer.add((long)produced);    
                System.out.println("Produced " + produced); 

            } catch (InterruptedException e){
                System.out.println("Could not produce");
            }

            empty.release();
            mutex.release();

            i++;
        }
    }



}