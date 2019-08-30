import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean presetDefined = false;

        //  prompt for the user's name
        System.out.print("Welcome to Lewis' Income/Weighted-payment program\nMENU\n1:Begin\n2:Help text\n3:Begin with Lewis/Helene current preset:\n4:exit\n");

        // get the age as an int
        int menu = scanner.nextInt();
        switch(menu) {
            case 1:
                startCalculation(presetDefined, scanner);
                break;
            case 2:
                System.out.print("This gets an amount of people and their salaries from user input,\nthen the user inputs their bills,\n" +
                        "then the user can see what percentages of the total monthly expenses each person must pay\n");
                main(new String[] {});
                break;
            case 3:
                presetDefined = true;
                startCalculation(presetDefined, scanner);
                break;
        }
    }

    public static void startCalculation(boolean presetDefined, Scanner scanner){
        Map<String, Double> people = new HashMap<>();
        double bills = 0;
        double totalIncome = 0;
        int numberOfPeople = 0;
        int numberOfBills = 0;
        if(presetDefined){
            people.put("Lewis", (double) 2750);
            people.put("Helene", (double) 895);
        }else{
            do{
                System.out.print("Input a user, or '0' to go to next step:");
                String name = scanner.next();
                if(name.equals("0"))break;
                System.out.print("Enter " + name + "'s income in € per month:");
                double income = scanner.nextDouble();
                people.put(name, income);
                numberOfPeople++;
            }while(numberOfPeople <= 10);
        }
        System.out.println("Okay");
        for (Map.Entry<String, Double> entry : people.entrySet()) {
            System.out.println(entry.getKey() + ", €" + entry.getValue());
            totalIncome += entry.getValue();
        }

        System.out.print("Input all your bills, or '0' to go to next step:");
        do{
            Double bill = scanner.nextDouble();
            if(bill == 0)break;
            bills += bill;
            numberOfBills++;
        }while(numberOfBills <= 30);

        System.out.print("The total expenses for this month are: €" + round(bills) + "\n\n");
        if(bills > totalIncome) System.out.print("WARNING: the bills add up to be MORE than the total income for the month! That is a BIG problem.\n\n");
        double mustPay;
        double percentage;
        for (Map.Entry<String, Double> entry : people.entrySet()) {
            percentage = entry.getValue()/totalIncome;
            mustPay = bills * percentage;
            System.out.println(entry.getKey() + ", with €" + entry.getValue() + " per month, must pay €" + round(mustPay));
            System.out.println("(This is " + round(percentage*100) + "% of all the bills)");
            System.out.println(entry.getKey() + " has €" + round(entry.getValue() - mustPay) + " left over.\n");
        }

    }

    public static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }
}


