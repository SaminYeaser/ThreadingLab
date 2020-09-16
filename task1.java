package task1;

import java.util.concurrent.Semaphore;

class OddPrint implements Runnable{

    Semaphore one;
    Semaphore two;

    public OddPrint(Semaphore one, Semaphore two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public void run() {
            try {
                for (int i = 1;i<=10; i++) {
                    one.acquire();
                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    one.release();

                }
                two.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


class EvenPrint implements Runnable {
    Semaphore two;
    Semaphore one;

    public EvenPrint(Semaphore two, Semaphore one) {
        this.two= two;
        this.one = one;
    }

    @Override
    public void run() {

            try {
                for (int i = 11;i<=20; i++) {
                    two.acquire();

                    System.out.println(Thread.currentThread().getName() + " -> " + i);
                    two.release();
                }
                one.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


public class task1 {

    public static void main(String[] args) {

        Semaphore one = new Semaphore(1);
        Semaphore two = new Semaphore(0);


        OddPrint printOneNumber = new OddPrint(one, two);
        EvenPrint printTwoNumber = new EvenPrint(two, one);
        new Thread(printOneNumber).start();
        new Thread(printTwoNumber).start();
    }
}