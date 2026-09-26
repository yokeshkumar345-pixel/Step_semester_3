class LibraryMember
{
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;
}

public class Main
{
    static String classifyAccess(String modifier, String context)
    {
        if(modifier.equals("public"))
            return "ALLOWED";

        if(modifier.equals("private"))
            return context.equals("SAME_CLASS")
                   ? "ALLOWED" : "DENIED";

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

    static String summarizeByModifier(String[][] attempts)
    {
        String[] modifiers =
            {"private", "default", "protected", "public"};

        String result = "";

        for(String modifier : modifiers)
        {
            int allowed = 0;
            int denied = 0;

            for(String[] a : attempts)
            {
                if(a[0].equals(modifier))
                {
                    if(classifyAccess(a[0], a[1])
                       .equals("ALLOWED"))
                        allowed++;
                    else
                        denied++;
                }
            }

            if(!result.equals(""))
                result += " | ";

            result += modifier + ": " +
                      allowed + " allowed / " +
                      denied + " denied";
        }

        return result;
    }

    public static void main(String[] args)
    {
        String[][] attempts =
        {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
            summarizeByModifier(attempts));
    }
}

//

public class Main
{
    static String classifyAccess(String modifier, String context)
    {
        if(modifier.equals("public"))
            return "ALLOWED";

        if(modifier.equals("private"))
            return context.equals("SAME_CLASS")
                   ? "ALLOWED" : "DENIED";

        if(modifier.equals("default"))
            return (context.equals("SAME_CLASS") ||
                    context.equals("SAME_PACKAGE"))
                    ? "ALLOWED" : "DENIED";

        if(modifier.equals("protected"))
        {
            if(context.equals("SAME_CLASS") ||
               context.equals("SAME_PACKAGE") ||
               context.equals(
               "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"))
                return "ALLOWED";

            return "DENIED";
        }

        return "DENIED";
    }

    static String firstDeniedAttempt(String[][] attempts)
    {
        for(int i = 0; i < attempts.length; i++)
        {
            String result =
                classifyAccess(attempts[i][0], attempts[i][1]);

            if(result.equals("DENIED"))
            {
                return attempts[i][0] +
                    " via " + attempts[i][1] +
                    " (attempt #" + (i + 1) + ")";
            }
        }

        return "None Denied";
    }

    public static void main(String[] args)
    {
        String[][] attempts =
        {
            {"public",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"},

            {"protected",
             "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"}
        };

        System.out.println(
            firstDeniedAttempt(attempts));
    }
}

//

class BookInventory
{
    private int copiesTotal;
    private int copiesAvailable;

    BookInventory(int copiesTotal)
    {
        if(copiesTotal <= 0)
            throw new IllegalArgumentException();

        this.copiesTotal = copiesTotal;
        copiesAvailable = copiesTotal;
    }

    void checkOut()
    {
        if(copiesAvailable > 0)
            copiesAvailable--;
    }

    void checkIn()
    {
        if(copiesAvailable < copiesTotal)
            copiesAvailable++;
    }

    int getCopiesAvailable()
    {
        return copiesAvailable;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        BookInventory b = new BookInventory(3);

        b.checkOut();
        b.checkOut();
        b.checkOut();
        b.checkOut();

        System.out.println(
            b.getCopiesAvailable());

        b.checkIn();
        b.checkIn();
        b.checkIn();
        b.checkIn();

        System.out.println(
            b.getCopiesAvailable());
    }
}

//

class LibraryMember
{
    private String membershipId;
    private String name;
    private boolean premiumMember;
    private String securityAnswer;

    public LibraryMember()
    {
    }

    String getMembershipId()
    {
        return membershipId;
    }

    void setMembershipId(String id)
    {
        if(membershipId == null)
            membershipId = id;
    }

    String getName()
    {
        return name;
    }

    void setName(String name)
    {
        this.name = name;
    }

    boolean isPremiumMember()
    {
        return premiumMember;
    }

    void setPremiumMember(boolean premium)
    {
        premiumMember = premium;
    }

    void setSecurityAnswer(String answer)
    {
        securityAnswer = Integer.toHexString(
            answer.hashCode());
    }
}

public class Main
{
    public static void main(String[] args)
    {
        LibraryMember m =
            new LibraryMember();

        m.setMembershipId("LIB-8841");
        m.setName("Priya Nair");
        m.setPremiumMember(true);

        System.out.println(m.getMembershipId());
        System.out.println(m.getName());
        System.out.println(m.isPremiumMember());

        m.setMembershipId("FAKE-0000");

        System.out.println(m.getMembershipId());

        m.setSecurityAnswer("BlueMountain");
    }
}

//

final class LoanReceipt
{
    private final String memberId;
    private final String[] bookIds;

    public LoanReceipt(
        String memberId, String[] bookIds)
    {
        this.memberId = memberId;
        this.bookIds = bookIds.clone();
    }

    String[] getBookIds()
    {
        return bookIds.clone();
    }

    LoanReceipt withCorrectedBookId(
        int index, String newId)
    {
        String[] books = bookIds.clone();

        books[index] = newId;

        return new LoanReceipt(memberId, books);
    }
}

class ReferenceOnlyLoanReceipt
    extends LoanReceipt
{
    private String roomNumber;

    public ReferenceOnlyLoanReceipt(
        String memberId,
        String[] bookIds,
        String roomNumber)
    {
        super(memberId, bookIds);
        this.roomNumber = roomNumber;
    }
}

public class Main
{
    static String processNightlyCirculation(
        LoanReceipt[] receipts)
    {
        int processed = 0;
        int nullCount = 0;
        int reference = 0;
        int regular = 0;

        for(LoanReceipt r : receipts)
        {
            if(r == null)
            {
                nullCount++;
                continue;
            }

            processed++;

            if(r instanceof ReferenceOnlyLoanReceipt)
                reference++;
            else
                regular++;
        }

        return processed + " processed | " +
               nullCount + " null skipped | " +
               reference + " reference-only | " +
               regular + " regular";
    }

    public static void main(String[] args)
    {
        LoanReceipt r =
            new LoanReceipt(
                "LIB-8841",
                new String[]{"BK-100", "BK-101"});

        String[] ids = r.getBookIds();
        ids[0] = "HACKED";

        System.out.println(
            r.getBookIds()[0]);

        LoanReceipt corrected =
            r.withCorrectedBookId(
                1, "BK-102");

        System.out.println(
            r.getBookIds()[0] + " " +
            r.getBookIds()[1]);

        System.out.println(
            corrected.getBookIds()[0] + " " +
            corrected.getBookIds()[1]);

        LoanReceipt[] receipts =
        {
            new ReferenceOnlyLoanReceipt(
                "LIB-001",
                new String[]{"BK-200"},
                "Reading Room 3"),

            null,

            new LoanReceipt(
                "LIB-002",
                new String[]{"BK-201"})
        };

        System.out.println(
            processNightlyCirculation(receipts));
    }
}
