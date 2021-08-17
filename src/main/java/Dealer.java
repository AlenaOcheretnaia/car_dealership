import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Dealer {

    List<Car> cars = new LinkedList<Car>();

    public synchronized boolean hasCar(){
        return cars.size() >0;
    }

    public synchronized void receiveCar(Car car) {
    //    try {
            System.out.println("Dealer: Receiving a car");
            //Thread.sleep(3000);
            this.cars.add(car);
            System.out.println("Dealer: Car is received");
            notifyAll();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    public synchronized Car sellCar() {
        Car car = null;
        if (cars.size() >0) {
            car = cars.get(0);
            cars.remove(0);
        }
        return car;
    }
}