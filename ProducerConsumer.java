import java.util.Queue;
import java.util.LinkedList;

/*
 *  This class is responsible for the main loop, generating the producers and consumers and starting everything.
 */
class ProducerConsumer{

    public static void main (String[] args){
        // Get the user input
        long seconds = Long.parseLong(args[0]);
        long numProducers = Long.parseLong(args[1]);
        long numConsumers = Long.parseLong(args[2]);
        System.out.println("Seconds: " + seconds + " // Producers: " + numProducers + " // Consumers: " + numConsumers);
    
        // Create the buffer
        Queue<Long> buffer = new LinkedList<Long>();

        //Make all of the producers
        for(int i = 0; i < numProducers; i++){
            Producer newProducer = new Producer(buffer);
            newProducer.start();
        }
        
        // Make all of the consumers
        for(int i = 0; i < numConsumers; i++){
            Consumer newConsumer = new Consumer(buffer);
            newConsumer.start();
        }
    }
}
