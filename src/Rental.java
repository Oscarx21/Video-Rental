public class Rental {
    private Movie _movie;
    private int _daysRented;

    public Rental(Movie movie, int daysRented) {
        _movie = movie;
        _daysRented = daysRented;
    }

    public int getDaysRented() {
        return _daysRented;
    }

    public Movie getMovie() {
        return _movie;
    }

    // ===========================================
    // MÉTODO NOVO — movido de Customer
    // ===========================================
    public double getCharge() {
        double result = 0;

        switch (_movie.getPriceCode()) {
            case Movie.REGULAR:
                result += 2;
                if (_daysRented > 2)
                    result += (_daysRented - 2) * 1.5;
                break;

            case Movie.NEW_RELEASE:
                result += _daysRented * 3;
                break;

            case Movie.CHILDRENS:
                result += 1.5;
                if (_daysRented > 3)
                    result += (_daysRented - 3) * 1.5;
                break;
        }

        return result;
    }

    // ===========================================
    // MÉTODO NOVO — movido de Customer
    // ===========================================
    public int getFrequentRenterPoints() {
        if (getMovie().getPriceCode() == Movie.NEW_RELEASE &&
                getDaysRented() > 1) {
            return 2;
        }
        return 1;
    }
}
