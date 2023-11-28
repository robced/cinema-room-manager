import java.util.Arrays;

public class Cinema {
    private int purchasedTickets = 0;
    private int currentIncome = 0;
    private int numberOfSeats = 0;
    private int totalIncome = 0;
    private final String[][] cinemaSeats;


    public Cinema(int numberOfRows, int numberOfColumns) {
        this.cinemaSeats = buildCinemaRoom(numberOfRows, numberOfColumns);
        this.numberOfSeats = calculateNumberOfSeats(cinemaSeats);
        this.totalIncome = calculateTotalIncome(cinemaSeats,numberOfSeats);
    }

    private static String[][] buildCinemaRoom(int numberOfRows, int numberOfColumns) {
        String[][] cinemaRoom = new String[numberOfRows][numberOfColumns];

        for (String[] strings : cinemaRoom) {
            Arrays.fill(strings, "S");
        }

        return cinemaRoom;
    }

    public void buyTicket(int selectedRow, int selectedColumn) {
        if (selectedRow >= cinemaSeats.length || selectedColumn >= cinemaSeats[0].length) {
            throw new IllegalArgumentException("Wrong input!");
        }

        if ("B".equals(cinemaSeats[selectedRow][selectedColumn])) {
            throw new IllegalStateException("That ticket has already been purchased!");
        }

        cinemaSeats[selectedRow][selectedColumn] = "B";
        purchasedTickets++;
        currentIncome += calculateTicketPrice(selectedRow);
    }

    public int calculateTicketPrice(int selectedRow) {
        int frontRow = calculateFrontRow(cinemaSeats);
        return numberOfSeats < 60 ? 10 : (selectedRow < frontRow ? 10 : 8);
    }

    private static int calculateNumberOfSeats(String[][] cinemaSeats) {
        return cinemaSeats.length * cinemaSeats[0].length;
    }

    private static int calculateFrontRow(String[][] cinemaSeats) {
        return (int) Math.floor(cinemaSeats.length / 2.0d);
    }

    private static int calculateTotalIncome(String[][] cinemaSeats, int numberOfSeats) {
        if (numberOfSeats < 60) {
            return numberOfSeats * 10;
        }

        int frontRow = calculateFrontRow(cinemaSeats);
        int backRow = cinemaSeats.length - frontRow;

        int frontRowSeatsIncome = frontRow * cinemaSeats[0].length * 10;
        int backRowSeatsIncome = backRow * cinemaSeats[0].length * 8;

        return frontRowSeatsIncome + backRowSeatsIncome;
    }

    public String[][] getRoomMap() {
        return cinemaSeats;
    }

    public int getPurchasedTickets() {
        return purchasedTickets;
    }

    public float getNumberOfSeats() {
        return numberOfSeats;
    }

    public int getCurrentIncome() {
        return currentIncome;
    }

    public int getTotalIncome() {
        return totalIncome;
    }
}
