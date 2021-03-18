package com.Lesson1;


public class ABCWriter {
    private char currentChar='A';
    private final int numberOfWrites;
    private Object lock1= new Object();


    public ABCWriter(int numberOfWrites) {
        this.numberOfWrites = numberOfWrites;
    }

    public void write(){
        Thread threadA = new Thread(()-> {
            displayTheLetter('A','B');
            });
        Thread threadB = new Thread(()-> {
            displayTheLetter('B','C');
        });
        Thread threadC = new Thread(()-> {
            displayTheLetter('C','A');
        });
        threadA.start();
        threadB.start();
        threadC.start();

    }

    public void displayTheLetter(char threadsChar, char nextChar)  {
        for (int i = 0; i < numberOfWrites; i++) {
            synchronized (lock1){
                while (currentChar != threadsChar){
                    try {
                        lock1.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (currentChar == 'C') {
                    System.out.println(currentChar);
                }else{
                    System.out.print(currentChar);
                }
                currentChar = nextChar;
                lock1.notifyAll();
            }
        }
    }
}
