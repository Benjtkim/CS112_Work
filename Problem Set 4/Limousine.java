public class Limousine extends Automobile {

    private boolean sunRoof;

    private int fridgeSpace;

    private int hi = 3;

    public Limousine(String make, String model, int year, int numSeats, boolean isSUV, boolean sunRoof, int fridgeSpace) {
        // call the first constructor
        super(make, model, year, numSeats, isSUV);

        if (fridgeSpace < 0) {
            throw new IllegalArgumentException(); 
        }

        this.sunRoof = sunRoof;
        this.fridgeSpace = fridgeSpace;
    }
    
    public boolean getSunRoof() {
        return this.sunRoof;
    }
    
    public int getFridgeSpace() {
        return this.fridgeSpace;
    }

    public String toString() {
        return this.getMake() + " " + this.getModel() + " (seats up to " + (this.getNumSeats() - 2) + " customers)" + hi;
    }

}


class limoClient {
    public static void main(String[] args) {
        Limousine limo = new Limousine("Cadillac", "XTS-L", 2013, 8, true, true, 5);
        System.out.println(limo);
        System.out.println(limo.getSunRoof());
        System.out.println(limo.getFridgeSpace());
    }
}