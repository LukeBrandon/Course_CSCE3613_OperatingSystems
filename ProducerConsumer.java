import java.util.Queue;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/*
 *  Luke Brandon
 *  010817623
 */
class ProducerConsumer{

    public static void main (String[] args){
        // Get the user input
        long seconds = Long.parseLong(args[0]);
        long numProducers = Long.parseLong(args[1]);
        long numConsumers = Long.parseLong(args[2]);
        System.out.println("Seconds: " + seconds + " // Producers: " + numProducers + " // Consumers: " + numConsumers);
    
        // Mutex for the buffer
        Semaphore mutex = new Semaphore(1);

        // Buffer starts as empty
        Semaphore empty = new Semaphore(0);
        Semaphore full = new Semaphore(5);

        // Create the buffer
        Queue<Long> buffer = new LinkedList<Long>();

        //Make all of the producers
        for(int i = 0; i < numProducers; i++){
            Producer newProducer = new Producer(buffer, mutex, empty, full);
            newProducer.start();
        }
        
        // Make all of the consumers
        for(int i = 0; i < numConsumers; i++){
            Consumer newConsumer = new Consumer(buffer, mutex, empty, full);
            newConsumer.start();
        }

        // Sleep for number of seconds inputted after making the producers and consumers
        try{
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }

        // Close the program after inputted time
        System.exit(0);
    }
}
