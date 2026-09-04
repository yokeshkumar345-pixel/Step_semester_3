class Account
{
    String regNo;
    double totalFee;

    Account(String regNo, double totalFee)
    {
        this.regNo = regNo;
        this.totalFee = totalFee;
    }

    final double calculateLateFee(int daysLate)
    {
        return totalFee * daysLate / 100;
    }

    final void printSummary(int daysLate)
    {
        if(daysLate <= 0)
        {
            System.out.println(
                regNo + " - On time, no late fee");
        }
        else
        {
            System.out.println(
                regNo + " | Total Fee: Rs " +
                totalFee + " | Late Fee: Rs " +
                calculateLateFee(daysLate));
        }
    }
}

public class Latefee
{
    public static void main(String[] args)
    {
        String[] regNos =
            {"RA001", "RA002", "RA003", "RA004"};

        double[] fees =
            {200000, 150000, 180000, 220000};

        int[] days =
            {10, 0, -2, 5};

        for(int i = 0; i < regNos.length; i++)
        {
            Account a =
                new Account(regNos[i], fees[i]);

            a.printSummary(days[i]);
        }
    }
}