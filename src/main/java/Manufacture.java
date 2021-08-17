public class Manufacture extends Thread {

    private Dealer dealer;

    public Manufacture(Dealer dealer) {
        super();
        this.dealer = dealer;
    }

    @Override
    public void run() {
        int count = 0;
        while (count < 10) {
            if (isInterrupted()) return;
            try {
                System.out.println(Thread.currentThread().getName() + " произвёл новую машину");
                Car newCar = new Car();
                dealer.receiveCar(newCar);
                count++;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
