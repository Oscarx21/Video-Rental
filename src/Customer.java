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

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder result = new StringBuilder("Rental Record for " + getName() + "\n");

        for (Rental rental : rentals) {

            totalAmount += rental.getCharge();
            frequentRenterPoints += rental.getFrequentRenterPoints();

            result.append("\t")
                    .append(rental.getMovie().getTitle())
                    .append("\t")
                    .append(rental.getCharge())
                    .append("\n");
        }

        result.append("Amount owed is ").append(totalAmount).append("\n");
        result.append("You earned ").append(frequentRenterPoints).append(" frequent renter points");

        return result.toString();
    }

    public String htmlStatement() {

        double totalAmount = 0;
        int frequentRenterPoints = 0;

        StringBuilder result = new StringBuilder("<h1>Rental Record for <em>" + getName() + "</em></h1><p>\n");

        for (Rental rental : rentals) {

            totalAmount += rental.getCharge();
            frequentRenterPoints += rental.getFrequentRenterPoints();

            result.append(rental.getMovie().getTitle())
                    .append(": ")
                    .append(rental.getCharge())
                    .append("<br>\n");
        }

        result.append("<p>You owe <em>")
                .append(totalAmount)
                .append("</em><p>\n");

        result.append("On this rental you earned <em>")
                .append(frequentRenterPoints)
                .append("</em> frequent renter points<p>");

        return result.toString();
    }
}
