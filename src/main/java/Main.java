public class Main {

    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        Seller seller = new Seller(dealer);

        Manufacture manufacture = new Manufacture(dealer);

        Buyer buyer1 = new Buyer(seller, "Buyer1");
        Buyer buyer2 = new Buyer(seller, "Buyer2");
        Buyer buyer3 = new Buyer(seller, "Buyer3");

        manufacture.start();
        seller.start();
        buyer1.start();
        buyer2.start();
        buyer3.start();

        try {
            seller.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (!seller.isAlive()) {
            manufacture.interrupt();
            buyer1.interrupt();
            buyer2.interrupt();
            buyer3.interrupt();
        }

    }
}
