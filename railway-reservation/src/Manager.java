import java.util.*;

public class Manager {
    private HashMap<String,String> upperBerth,lowerBerth,middleBerth;
    private LinkedList<String> waitingList;

    int uBerthCapacity,lBerthCapacity,mBerthCapacity, wlCapacity;
    int ticketId = 0;

    public Manager(int uBerthCapacity, int lBerthCapacity, int mBerthCapacity, int wlCapacity) {
        this.upperBerth = new HashMap<>(uBerthCapacity);
        this.lowerBerth = new HashMap<>(lBerthCapacity);
        this.middleBerth = new HashMap<>(mBerthCapacity);
        this.waitingList = new LinkedList<>();

        this.uBerthCapacity = uBerthCapacity;
        this.mBerthCapacity = mBerthCapacity;
        this.lBerthCapacity = lBerthCapacity;
        this.wlCapacity = wlCapacity;
    }

    public void book(char berthName,String passengerName){
        switch (berthName){
            case 'U':
                if (upperBerth.size() < uBerthCapacity){
                    bookUpper(passengerName);
                } else if (middleBerth.size() < mBerthCapacity) {
                    bookMiddle(passengerName);
                } else if (lowerBerth.size() < lBerthCapacity) {
                    bookLower(passengerName);
                } else if (waitingList.size() < wlCapacity) {
                    bookWL(passengerName);
                } else {
                    System.out.println("Booking not available");
                }
                break;
            case 'M':
                if (middleBerth.size() < mBerthCapacity){
                    bookMiddle(passengerName);
                } else if (lowerBerth.size() < lBerthCapacity) {
                    bookLower(passengerName);
                } else if (upperBerth.size() < uBerthCapacity) {
                    bookUpper(passengerName);
                } else if (waitingList.size() < wlCapacity) {
                    bookWL(passengerName);
                } else {
                    System.out.println("Booking not available");
                }
                break;
            case 'L':
                if (lowerBerth.size() < lBerthCapacity){
                    bookLower(passengerName);
                } else if (upperBerth.size() < uBerthCapacity) {
                    bookUpper(passengerName);
                } else if (middleBerth.size() < mBerthCapacity) {
                    bookMiddle(passengerName);
                } else if (waitingList.size() < wlCapacity) {
                    bookWL(passengerName);
                } else {
                    System.out.println("Booking not available");
                }
                break;
            default:
                System.out.println("invalid berth");
                break;
        }
    }

    public void printBooking(){
        for (Map.Entry<String,String> entry: upperBerth.entrySet()){
            System.out.println("Ticket No "+ entry.getKey() + " , Passenger Name " + entry.getValue());
        }

        for (Map.Entry<String,String> entry: middleBerth.entrySet()){
            System.out.println("Ticket No "+ entry.getKey() + " , Passenger Name " + entry.getValue());
        }

        for (Map.Entry<String,String> entry: lowerBerth.entrySet()){
            System.out.println("Ticket No "+ entry.getKey() + " , Passenger Name " + entry.getValue());
        }

        for (int i = 0; i < waitingList.size(); i++){
            System.out.println("Ticket No W"+i+" , Passenger Name "+ waitingList.get(i));
        }
    }
    public void cancel(String ticketId){
        if (ticketId.contains("U")){

            if( upperBerth.remove(ticketId) == null ){
                System.out.println("Invalid Ticket");
            }
            else{
                System.out.println("Cancelled "+ ticketId);
                if (!waitingList.isEmpty()) bookUpper(waitingList.poll());
            }

        } else if (ticketId.contains("M")) {

            if( middleBerth.remove(ticketId) == null ){
                System.out.println("Invalid Ticket");
            }
            else{
                System.out.println("Cancelled "+ ticketId);
                if (!waitingList.isEmpty()) bookUpper(waitingList.poll());
            }

        } else if (ticketId.contains("L")) {

            if( lowerBerth.remove(ticketId) == null ){
                System.out.println("Invalid Ticket");
            }
            else{
                System.out.println("Cancelled "+ ticketId);
                if (!waitingList.isEmpty()) bookUpper(waitingList.poll());
            }

        } else if (ticketId.contains("W")) {

            waitingList.remove(Integer.parseInt(ticketId.charAt(1)+""));
        }
    }


    private void bookUpper(String passengerName){
        upperBerth.put("U"+ticketId,passengerName);
        System.out.println("Upper berth booked. Ticket No : U"+ ticketId );
        ticketId++;
    }

    private void bookMiddle(String passengerName){
        middleBerth.put("M"+ticketId,passengerName);
        System.out.println("Middle berth booked. Ticket No : M"+ticketId );
        ticketId++;
    }

    private void bookLower(String passengerName){
        lowerBerth.put("L"+ticketId,passengerName);
        System.out.println("Lower berth booked. Ticket No : L"+ ticketId );
        ticketId++;
    }

    private void bookWL(String passengerName)
    {
        waitingList.add(passengerName);
        System.out.println("Added to Waiting List. No W" + ( waitingList.size() - 1) );
    }
}

