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

    public String statement() {
        double totalAmount = 0;
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            double thisAmount = 0;
            Rental each = (Rental) rentals.nextElement();

            // cálculo baseado no tipo de filme
            switch (each.getMovie().getPriceCode()) {
                case Movie.REGULAR:
                    thisAmount += 2;
                    if (each.getDaysRented() > 2)
                        thisAmount += (each.getDaysRented() - 2) * 1.5;
                    break;

                case Movie.NEW_RELEASE:
                    thisAmount += each.getDaysRented() * 3;
                    break;

                case Movie.CHILDRENS:
                    thisAmount += 1.5;
                    if (each.getDaysRented() > 3)
                        thisAmount += (each.getDaysRented() - 3) * 1.5;
                    break;
            }

            // adicionar pontos
            frequentRenterPoints++;

            // bônus para lançamento novo alugado por mais de um dia
            if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                    each.getDaysRented() > 1) {
                frequentRenterPoints++;
            }

            // mostrar valores desta locação
            result += "\t" + each.getMovie().getTitle() + "\t" + thisAmount + "\n";

            totalAmount += thisAmount;
        }

        // rodapé
        result += "Amount owed is " + totalAmount + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;
    }
}
