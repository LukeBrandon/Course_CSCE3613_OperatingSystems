The program works as the following:
    The input is read in from the user containing the sleep time, # producers, # consumers
    The semaphores for the mutex (semaphore with size 1), the empty, and full are instantiated
    There is a for loop that creates all of the producers
    There is a for loop that cerates all of the consumers

    In the Producers there is a loop that runs 100 times
        The loop chooses a random time to sleep
        Sleeps
        Then wakes up, attempts to acquire resources by calling full.acquire() which takes a permit from the full semaphore
            and calls mutex.acquire() which gets a permit to add to the buffer
        If both of these resources are granted, then a random number is chosen and added to the queue
        After adding to the queue, empty.release() is called to add one to the empty semaphore, because it is less empty now
            then mutex.release() to allow other processes to access the buffer.

    In the Consumers there is a loop that runs 100 times    
        The loop chooses a random time to sleep
        Sleeps
        Then wakes up, attempts to acquire resources by calling empty.acquire() which takes a permit from the empty semaphore  
            and calls mutex.acquire() which gets a permit to add to the buffer
        If both of these resources are granted, then a number is "consumed" from the queue
        After consuming from the queue, full.release() is called to add one to the full semaphore, because it is less full now
            then mutex.release() to allow other processes to access the buffer.

    After all of the Producers and Consumers are created, the main thread sleeps for the given time by the user
        then the program exits by running System.exit(0)
