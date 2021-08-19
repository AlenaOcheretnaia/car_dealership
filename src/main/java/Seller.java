import java.util.TreeMap;

public class Seller extends Thread {

    private Dealer dealer;

    public Seller(Dealer dealer) {
        super();
        this.dealer = dealer;
    }

    public synchronized boolean hasCar() {
        return dealer.hasCar();
    }

    public synchronized Car getCar() {
        return dealer.sellCar();
    }

    @Override
    public void run() {
        int count = 0;
        try {
            System.out.println("Seller: Selling car");
            while (count < 10) {
                if (isInterrupted()) return;
                if (!dealer.hasCar()) {
                    System.out.println("Seller: Can't sell car - have no car");
                    synchronized (dealer) {
                        dealer.wait();
                    }
                } else {
                    Thread.sleep(2000);
                    System.out.println("Seller: I have a car");
                    count++;
                    synchronized (this) {
                        this.notifyAll();
                    }
                    synchronized (dealer) {
                        dealer.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Seller thread was finished");
        }
    }
}
