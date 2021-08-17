public class Manufacture extends Thread {

    private Dealer dealer;

    public Manufacture(Dealer dealer) {
        super();
        this.dealer = dealer;
    }

    @Override
    public void run() {
        while (true) {
            if (isInterrupted()) return;
            try {
                System.out.println(Thread.currentThread().getName() + " произвёл новую машину");
                Car newCar = new Car();
                dealer.receiveCar(newCar);
                //notifyAll();
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
