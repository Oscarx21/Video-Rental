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

    private double getCharge(Rental each) {
        double result = 0;

        switch (each.getMovie().getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (each.getDaysRented() > 2)
                    result += (each.getDaysRented() - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += each.getDaysRented() * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if (each.getDaysRented() > 3)
                    result += (each.getDaysRented() - 3) * 1.5;
                break;
        }
        return result;
    }

    private int getFrequentRenterPoints(Rental each) {
        if (each.getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                each.getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }

    // =======================================
    // NOVO MÉTODO DO COMMIT 15
    // Soma de todos os valores das locações
    // =======================================
    private double getTotalCharge() {
        double result = 0;

        Enumeration rentals = _rentals.elements();
        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();
            result += getCharge(each);
        }

        return result;
    }

    public String statement() {
        int frequentRenterPoints = 0;

        Enumeration rentals = _rentals.elements();
        String result = "Rental Record for " + getName() + "\n";

        while (rentals.hasMoreElements()) {
            Rental each = (Rental) rentals.nextElement();

            frequentRenterPoints += getFrequentRenterPoints(each);

            result += "\t" + each.getMovie().getTitle() + "\t" +
                    getCharge(each) + "\n";
        }

        // substituído: totalAmount
        result += "Amount owed is " + getTotalCharge() + "\n";
        result += "You earned " + frequentRenterPoints + " frequent renter points";

        return result;
    }
}
