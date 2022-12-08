import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager(2,2,2,1);

        while (true){
            printMenu();
            Scanner sc = new Scanner(System.in);
            int x = sc.nextInt();

            if (x == 1){
                System.out.println("Enter Name, Berth(L,M,U)");
                String name = sc.next();
                String berth =  sc.next();
                manager.book(berth.charAt(0),name);

            }else if (x == 2){
                manager.printBooking();
            }
            else if (x==3){

                System.out.println("Enter TicketId");
                String ticketId = sc.next();
                manager.cancel(ticketId);
            }
            else break;
        }
    }

    private static void printMenu(){
        System.out.print("1. Book Ticket\n2.Print Bookings\n3.Cancel Ticket");
    }
}
