import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Cinema cinemaRoom = buildCinemaRoom(scanner);
        String[][] cinemaRoomMap = cinemaRoom.getRoomMap();

        printMenu(scanner, cinemaRoomMap, cinemaRoom);
    }

    private static Cinema buildCinemaRoom(Scanner scanner) {
        System.out.println("Enter the number of rows:");
        int numberOfRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numberOfColumns = scanner.nextInt();
        System.out.println();

        return new Cinema(numberOfRows, numberOfColumns);
    }

    public static void printCinemaSeats(String[][] cinemaRoomMap) {
        System.out.println("Cinema: ");
        System.out.print("  ");

        for (int i = 1; i <= cinemaRoomMap[0].length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < cinemaRoomMap.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j= 0; j < cinemaRoomMap[i].length; j++) {
                System.out.print(cinemaRoomMap[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }

    private static void  buyTicket(Scanner scanner, Cinema cinemaRoom) {
        System.out.println("Enter a row number:");
        int selectedRow = scanner.nextInt() - 1;
        System.out.println("Enter a seat number in that row:");
        int selectedColumn = scanner.nextInt() - 1;

        try {
            cinemaRoom.buyTicket(selectedRow, selectedColumn);
            System.out.println();

            System.out.println("Ticket price: $" + cinemaRoom.calculateTicketPrice(selectedRow));
            System.out.println();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println();
            buyTicket(scanner, cinemaRoom);
        }
    }

    private static void printStatistics(Cinema cinemaRoom) {
        int purchasedTickets = cinemaRoom.getPurchasedTickets();
        System.out.println("Number of purchased tickets: " + purchasedTickets);
        System.out.printf("Percentage: %.2f%%%n", ( (float) purchasedTickets / cinemaRoom.getNumberOfSeats() * 100 ));
        System.out.println("Current income: $" + cinemaRoom.getCurrentIncome());
        System.out.println("Total income: $" + cinemaRoom.getTotalIncome());
        System.out.println();
    }

    private static void printMenu(Scanner scanner, String[][] cinemaRoomMap, Cinema cinemaRoom) {
        while (true) {
            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int userMenuOption = scanner.nextInt();

            if (userMenuOption < 0 || userMenuOption > 3) {
                System.out.println("Wrong input.");
                continue;
            }

            System.out.println();

            switch (userMenuOption) {
                case 1: {
                    printCinemaSeats(cinemaRoomMap);
                    break;
                }

                case 2: {
                    buyTicket(scanner, cinemaRoom);
                    break;
                }

                case 3: {
                    printStatistics(cinemaRoom);
                    break;
                }

                case 0: {
                    return;
                }
            }
        }
    }
}