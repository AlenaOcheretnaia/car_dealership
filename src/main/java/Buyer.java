public class Buyer extends Thread {


    private boolean doIhaveCar = false;
    private Seller seller = null;
    private String name;

    public Buyer(Seller seller, String name) {
        super();
        this.seller = seller;
        this.name = name;
    }


    @Override
    public void run() {
        System.out.println(this.name + ": want to buy a car!");
        try {
            while (!isInterrupted()) {
                if (!seller.hasCar()) {
                    System.out.println(this.name + ": waiting car");
                    synchronized (seller) {
                        seller.wait();
                    }
                } else {
                    Car c = seller.getCar();
                    System.out.println(this.name + ": got car " + c.toString());
                    doIhaveCar = true;
                    Thread.sleep(3000);
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Buyer thread was finished");
        }
    }
}

