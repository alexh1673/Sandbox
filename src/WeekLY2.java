import java.util.Scanner;

public class WeekLY2 {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your stuff");
        String a = scan.nextLine();
        String b = scan.nextLine();
        System.out.println();
        while(a!="" || b!="")
        {
            if(helper(a))
                System.out.println("Jolly");
            else
                System.out.println("Not Jolly");
            a = b;
            b = scan.nextLine();
        }
    }

    public static boolean helper(String s) {
        int j,k;
        j = Integer.parseInt(s.substring(0, s.indexOf(" ")));
        s = s.substring(s.indexOf(" ") + 1);
        while(s.length()>1) {
            k = Integer.parseInt(s.substring(0, s.indexOf(" ")));
            s = s.substring(s.indexOf(" ") + 1);
            if(!(Math.abs(j-k)>=Math.min(j,k) && Math.abs(j-k)<=Math.max(j,k)))
                return false;
            j = k;
        }
        return true;
    }

}
