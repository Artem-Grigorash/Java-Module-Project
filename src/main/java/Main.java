import java.io.PrintStream;
import java.util.Scanner;
public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args) {

        out.println("How many people should the bill be divided into?");
        String enter = in.next();
        int persons = 0, flag = 0, flag2 = 0;
        while (flag == 0) {
            for (int t = 0; t < enter.length(); t++) {
                if (enter.charAt(t) < '0' || enter.charAt(t) > '9') {
                    flag2 += 1;
                }
            }
            if (flag2==0) {
                int st = 1;
                for (int r = enter.length()-1; r >= 0; r--) {
                    persons += ((int) enter.charAt(r) - 48) * st;
                    st *= 10;
                }
                if (persons >= 1)
                    flag = 1;
            }
            if (flag == 0) {
                out.println("Non-correct value for counting\nPlease enter the correct value");
                enter = in.next();
            }
            flag2 = 0;
        }

        Positions[] pos = new Positions[100];

        out.println("Enter the name of the dish");
        String nextPosition = in.next();
        out.println("Enter the price of the dish in the format rub,kop");
        double nextPrice = in.nextDouble();
        int i = 0;
        while (!(nextPosition.equalsIgnoreCase("Завершить") || nextPosition.equalsIgnoreCase("end"))) {
            out.println("The dish has been added successfully!\nDo you want to add one more?");
            pos[i] = new Positions(nextPosition, nextPrice);
            out.println("Enter the name of the dish");
            nextPosition = in.next();
            if(!(nextPosition.equalsIgnoreCase("Завершить") || nextPosition.equalsIgnoreCase("end"))) {
                out.println("Enter the price of the dish in the format rub.kop");
                nextPrice = in.nextDouble();
            }
            i+=1;
        }

        out.println("Added dishes:");
        for (int j=0; j<i; j++)
        out.println(j+1 +") " + pos[j].name + " --- "+ pos[j].price + " rubles");
        double sum = 0;
        for (int j=0; j<i; j++)
            sum+=pos[j].price;
        out.println("Total sum: " + String.format("%.2f", sum) + " rubles");
        out.println(("Everyone has to pay: ") + String.format("%.2f", sum/persons)+ " rubles\nDo you want to correct the list?");

        String choice = in.next();
        if (choice.equalsIgnoreCase("Да") || choice.equalsIgnoreCase("Yes")){
            int del = 1;
            while (del != 0) {
                out.println("Enter the number of the dish you want to delete, If you have finished editing, enter 0");
                del = in.nextInt();
                if (del>0) {
                    for (int y = del - 1; y < i - 1; y++) {
                        pos[y] = pos[y + 1];
                    }
                    pos[i] = null;
                    i--;
                    for (int j=0; j<i; j++)
                        out.println(j+1 +") " + pos[j].name + " --- "+ pos[j].price + " rubles");
                    double sum1 = 0;
                    for (int j=0; j<i; j++)
                        sum1+=pos[j].price;
                    out.println("Total sum: " + String.format("%.2f", sum1) + " rubles");
                    out.println(("Everyone has to pay: ") + String.format("%.2f", sum1/persons)+ " rubles");
                }
                else if (del < 0)
                    out.println("The number cannot be less than zero");
            }
            out.println("Great! See you later");
        }
        else if (choice.equalsIgnoreCase("Нет") || choice.equalsIgnoreCase("No")){
            out.println("Great! See you later");
        }
        else
            out.println("Please, enter Yes/No");
    }
}

