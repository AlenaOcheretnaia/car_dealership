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
            while (true) {
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
            e.printStackTrace();
        }
    }
}
