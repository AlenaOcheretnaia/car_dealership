public class Main {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        Seller seller = new Seller(dealer);

        Manufacture manufacture = new Manufacture(dealer);

        Buyer buyer1 = new Buyer(seller, "buyer1");
        Buyer buyer2 = new Buyer(seller, "buyer2");
        Buyer buyer3 = new Buyer(seller, "buyer3");

        manufacture.start();
        seller.start();
        buyer1.start();
        buyer2.start();
        buyer3.start();

        try {
            manufacture.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!manufacture.isAlive()) {
            seller.interrupt();
            buyer1.interrupt();
            buyer2.interrupt();
            buyer3.interrupt();
        }

    }
}
