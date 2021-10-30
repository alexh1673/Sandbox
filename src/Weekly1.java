import java.util.Scanner;

public class Weekly1 {

    public static void main(String args[])
    {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter first number");
        int a = scan.nextInt();
        System.out.println("Enter second number");
        int b = scan.nextInt();
        int c = Math.max(a,b);
        int d = Math.min(a,b);
        int ans = 1;
        for(int i = d;i<=c;i++)
        {
            if(solveit(i)>ans)
                ans = solveit(i);
        }
        if(b>a)
        System.out.println(a+" "+b+" "+ans);
        else
            System.out.println(b+" "+a+" "+ans);
    }

    public static int solveit(int n)
    {
        if(n == 1)
        return 1;
        else if(n%2 == 0)
            return 1+solveit(n/2);
        else
            return 1+solveit((3*n+1));
    }
}
