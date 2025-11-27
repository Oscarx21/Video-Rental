import java.util.ArrayList;
import java.util.List;

public class Customer {

    private String name;
    private List<Rental> rentals = new ArrayList<>();

    public Customer(String name) {
        this.name = name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public String getName() {
        return name;
    }

    public String statement() {

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {
            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(rental.getCharge())
                    .append("\n");
        }

        result.append("Amount owed is ")
                .append(getTotalCharge())
                .append("\n");

        result.append("You earned ")
                .append(getTotalFrequentRenterPoints())
                .append(" frequent renter points");

        return result.toString();
    }

    public String htmlStatement() {

        StringBuilder result = new StringBuilder("<h1>Rental Record for <em>" + getName() + "</em></h1><p>\n");

        for (Rental rental : rentals) {

            result.append(rental.getMovie().getTitle())
                    .append(": ")
                    .append(rental.getCharge())
                    .append("<br>\n");
        }

        result.append("<p>You owe <em>")
                .append(getTotalCharge())
                .append("</em><p>\n");

        result.append("On this rental you earned <em>")
                .append(getTotalFrequentRenterPoints())
                .append("</em> frequent renter points<p>");

        return result.toString();
    }

    // ==============================
    // Métodos auxiliares (commit 6)
    // ==============================

    private double getTotalCharge() {
        double total = 0;
        for (Rental r : rentals) {
            total += r.getCharge();
        }
        return total;
    }

    private int getTotalFrequentRenterPoints() {
        int points = 0;
        for (Rental r : rentals) {
            points += r.getFrequentRenterPoints();
        }
        return points;
    }
}
