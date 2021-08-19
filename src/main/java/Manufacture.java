public class Manufacture extends Thread {

    private Dealer dealer;

    public Manufacture(Dealer dealer) {
        super();
        this.dealer = dealer;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                System.out.println("Manufacture произвёл новую машину");
                Car newCar = new Car();
                dealer.receiveCar(newCar);
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("Manufacture thread was finished");
                break;
            }
        }
    }


}
