
import java.util.*;
public class Cinema {
    public static final Scanner sc = new Scanner(System.in);
    public static final char bookedSeat = 'B';
    public static final char emptySeat = 'S';
    public static final int frontSeatPrice =10;
    public static final int backSeatPrice = 8;
    public static final int smallCinemaSize = 60;
    public static int ticPrice;
    public static int rows;
    public static int cols;
    public static int row;
    public static int col;
    public static int not=0;
    public static char[][] seats;


    public static void main(String[] args) {
        fillSeats();
        menu(rows,cols);


    }
    public static void fillSeats() {
        System.out.println("Enter the number of rows:");
        rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        cols = sc.nextInt();
        seats = new char[rows][cols];
        for(int i = 0; i < rows; i++) {

            for(int j = 0; j < cols; j++) {
                seats[i][j]=emptySeat;
            }

        }
    }
    public static void menu(int rows, int cols) {
        Scanner sc=new Scanner(System.in);
        System.out.println("1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
        int choice = sc.nextInt();
        while (choice != 0) {

            switch (choice) {
                case 1 : printCinema(rows,cols);
                    menu(rows,cols);
                    break;
                case 2 : buyTickets(rows,cols);
                    menu(rows,cols);
                    break;
                case 3: statistics();
                    menu(rows,cols);
                    break;
                case 0 : break;
                default : System.out.println("choose number from 0 to 3!");
                    menu(rows,cols);
                    break;
            }
        }

    }
    public static void printCinema(int rows,int cols) {
        System.out.println("\nCinema:");
        System.out.println("  ");

        for (int i = 1; i < cols +1; i++) {
            if(i==1){
                System.out.print("  ");
            }
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < rows; i++) {
            System.out.print((i+1) + " ");
            for (int j = 0; j < cols; j++) {
                System.out.print(seats[i][j] + " ");
            }
            System.out.println();
        }

    }
    public static void buyTickets(int rows, int cols) {
        System.out.println("Enter a row number:");
        row = sc.nextInt();
        System.out.println("Enter a seat number in that row:");
        col = sc.nextInt();
        if(row <= rows && col <= cols) {
            if (seats[row-1][col-1] == 'S') {

                seats[row-1][col-1] = bookedSeat;
                int ticketPrice = calculatePrice(row,col);
                ticPrice=ticketPrice;
                System.out.printf("Ticket price: $%d", ticketPrice);
                System.out.println();
            } else {
                System.out.println("That ticket has already been purchased!");
                System.out.println("Re-Enter row and column ");
                buyTickets(rows,cols);
                return;

            }
        }else {
            System.out.println("Wrong Input!");
        }
        not++;

    }
    public static int calculatePrice(int row, int col) {
        int totalseats = rows * cols;
        if (totalseats <= smallCinemaSize) {

            return frontSeatPrice;

        }else {
            if (row <= rows/2) {
                return frontSeatPrice;
            }else {
                return backSeatPrice;
            }
        }
    }
    public static void statistics() {

        System.out.println("Number of purchased tickets: " + not);
        int totalseats = rows * cols;
        int current_Income = 0;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++) {
                if (seats[i][j] == 'B') {
                    current_Income += i >= rows/2 ? 8 : 10;
                }
            }
        }

        float percentage =(float)not/totalseats*100;
        System.out.printf("Percentage: %.2f%%",percentage);

        System.out.println();
        System.out.println("Current income: $" + current_Income);
        final int totalIncomeGain = totalIncome();
        System.out.println("Total Income: $" + totalIncomeGain);

    }
    public static int totalIncome(){
        int total;
        int totalseats = rows * cols;
        if (totalseats <= smallCinemaSize) {

            return frontSeatPrice * totalseats;

        }else {
            total=frontSeatPrice*(seats.length/2);
            int total1=backSeatPrice*(seats.length/2);
            return (total+total1)*10;
        }
    }





}