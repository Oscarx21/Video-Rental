import java.util.Enumeration;
import java.util.Vector;

public class Customer {

    private String _name;
    private Vector _rentals = new Vector();

    public Customer(String name) {
        this._name = name;
    }

    public void addRental(Rental rental) {
        _rentals.addElement(rental);
    }

    public String getName() {
        return _name;
    }

    // ===========================================
    // REMOVIDOS (agora estão em Rental):
    //  - getCharge(Rental)
    //  - getFrequentRenterPoints(Rental)
    // ===========================================

    private double getTotalCharge() {
        double result = 0;

        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getCharge(); // ← agora via Rental
        }

        return result;
    }

    private int getTotalFrequentRenterPoints() {
        int result = 0;

        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += each.getFrequentRenterPoints(); // ← agora via Rental
        }

        return result;
    }

    public String statement() {
        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            result += "\t" + each.getMovie().getTitle() + "\t" +
                    each.getCharge() + "\n"; // ← atualizado
        }

        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + getTotalFrequentRenterPoints() +
                " frequent renter points";

        return result;
    }

    public String htmlStatement() {
        Enumeration rentals = _rentals.elements();
        String result = "<h1>Rental Record for <em>" + getName() + "</em></h1><p>\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            result += each.getMovie().getTitle() + ": " +
                    each.getCharge() + "<br>\n"; // ← atualizado
        }

        result += "<p>You owe <em>" + getTotalCharge() + "</em><p>\n";
        result += "On this rental you earned <em>" +
                getTotalFrequentRenterPoints() +
                "</em> frequent renter points<p>";

        return result;
    }
}
