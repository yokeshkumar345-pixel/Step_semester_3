class MovieTicket
{
    private int seatNumber;
    String screenId;
    protected double ticketPrice;
    public String movieTitle;
}

public class movieticketvalidater
{
    static String classifyAccess(String modifier, String context)
    {
        if(modifier.equals("public"))
            return "ALLOWED";

        if(modifier.equals("private"))
            return context.equals("SAME_CLASS") ? "ALLOWED" : "DENIED";

        if(modifier.equals("default"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        if(modifier.equals("protected"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        return "DENIED";
    }

    static String summarizeBatch(String[][] attempts)
    {
        int allowed = 0, denied = 0;

        for(String[] a : attempts)
        {
            if(classifyAccess(a[0], a[1]).equals("ALLOWED"))
                allowed++;
            else
                denied++;
        }

        return "Allowed: " + allowed + " | Denied: " + denied;
    }

    public static void main(String[] args)
    {
        System.out.println(
            classifyAccess("private", "SAME_CLASS"));

        String[][] a =
        {
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(summarizeBatch(a));
    }
}

//

class MovieTicket
{
    protected double ticketPrice;
    private int seatNumber;
    String screenId;
    public String movieTitle;
}

class PremiumMovieTicket extends MovieTicket
{
}

public class Main
{
    static String classifyAccess(String modifier, String context)
    {
        if(modifier.equals("public"))
            return "ALLOWED";

        if(modifier.equals("private"))
            return context.equals("SAME_CLASS") ?
                   "ALLOWED" : "DENIED";

        if(modifier.equals("default"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        if(modifier.equals("protected"))
        {
            if(context.equals("SAME_CLASS") ||
               context.equals("SAME_PACKAGE") ||
               context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    public static void main(String[] args)
    {
        System.out.println(
            classifyAccess("protected",
            "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));

        System.out.println(
            classifyAccess("protected",
            "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
    }
}

//

class CineScreen
{
    private int seatsTotal;
    private int seatsAvailable;

    CineScreen(int seatsTotal)
    {
        if(seatsTotal <= 0)
            throw new IllegalArgumentException();

        this.seatsTotal = seatsTotal;
        seatsAvailable = seatsTotal;
    }

    void bookSeat()
    {
        if(seatsAvailable > 0)
            seatsAvailable--;
    }

    void cancelBooking()
    {
        if(seatsAvailable < seatsTotal)
            seatsAvailable++;
    }

    int getSeatsAvailable()
    {
        return seatsAvailable;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        CineScreen c = new CineScreen(2);

        c.bookSeat();
        c.bookSeat();
        c.bookSeat();

        System.out.println(c.getSeatsAvailable());

        c.cancelBooking();
        c.cancelBooking();
        c.cancelBooking();

        System.out.println(c.getSeatsAvailable());
    }
}

//
class MovieBookingProfile
{
    private String name;
    private boolean confirmed;
    private String otp;

    public MovieBookingProfile()
    {
        name = "";
        confirmed = false;
    }

    public MovieBookingProfile(String name)
    {
        this();
        this.name = name;
    }

    String getName()
    {
        return name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    boolean isConfirmed()
    {
        return confirmed;
    }

    void setConfirmed(boolean confirmed)
    {
        this.confirmed = confirmed;
    }

    void setOtp(String otp)
    {
        this.otp = otp;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        MovieBookingProfile p =
            new MovieBookingProfile("Rahul Dev");

        System.out.println(p.getName());

        p.setConfirmed(true);
        System.out.println(p.isConfirmed());

        p.setOtp("4471");
    }
}

//

final class BookingReceipt
{
    private final String bookingId;
    private final String[] seatNumbers;

    public BookingReceipt(String bookingId, String[] seatNumbers)
    {
        this.bookingId = bookingId;
        this.seatNumbers = seatNumbers.clone();
    }

    String[] getSeatNumbers()
    {
        return seatNumbers.clone();
    }

    BookingReceipt withUpdatedSeat(int index, String newSeat)
    {
        String[] seats = seatNumbers.clone();
        seats[index] = newSeat;

        return new BookingReceipt(bookingId, seats);
    }
}

class GroupBookingReceipt extends BookingReceipt
{
    private int groupSize;

    public GroupBookingReceipt(
        String bookingId,
        String[] seatNumbers,
        int groupSize)
    {
        super(bookingId, seatNumbers);
        this.groupSize = groupSize;
    }
}

public class Main
{
    static String processNightlySettlement(
        BookingReceipt[] receipts)
    {
        int processed = 0;
        int nullCount = 0;
        int group = 0;
        int individual = 0;

        for(BookingReceipt r : receipts)
        {
            if(r == null)
            {
                nullCount++;
                continue;
            }

            processed++;

            if(r instanceof GroupBookingReceipt)
                group++;
            else
                individual++;
        }

        return processed + " processed | " +
               nullCount + " null skipped | " +
               group + " group | " +
               individual + " individual";
    }

    public static void main(String[] args)
    {
        BookingReceipt[] receipts =
        {
            new GroupBookingReceipt(
                "CH-2002",
                new String[]{"B1", "B2"}, 2),

            null,

            new BookingReceipt(
                "CH-3003",
                new String[]{"C1"})
        };

        System.out.println(
            processNightlySettlement(receipts));
    }
}

//


