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
        try {
            System.out.println("Seller: Selling car");
            int count = 0;
            while (true) {
                if (isInterrupted()) return;
                if (!dealer.hasCar()) {
                    System.out.println("Seller: Can't sell car - have no car");
                    synchronized (dealer) {
                        dealer.wait();
                    }
                } else {
                    System.out.println("Seller: I have a car");
                    synchronized (this) {
                        this.notifyAll();
                    }
                    synchronized (dealer) {
                        dealer.wait();
                    }
                }
            }
        } catch (
                InterruptedException e) {
            System.out.println("Seller thread was winished");
        }
    }
}
