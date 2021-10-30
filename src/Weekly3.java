import java.util.Arrays;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;

public class Weekly3 {

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your stuff");
        String a = scan.nextLine();
        String b = scan.nextLine();
        System.out.println(helper(a, b));
        while(a!="" || b!="")
        {
            a = scan.nextLine();
            b = scan.nextLine();
            if(!scan.hasNext())
                break;
            System.out.println(helper(a, b));
        }
    }

    public static String helper(String a,String b)
    {
        String ans = "";
        int[] arr = new int[26];
        int[] arr1 = new int[26];
        a = a.toLowerCase();
        b = b.toLowerCase();
        for(int i = 0;i<a.length();i++)
        {
            if(a.charAt(i)-97>0 && a.charAt(i)-97<27)
            {
                arr[a.charAt(i)-97] = arr[a.charAt(i)-97]+1;
            }
        }
        for(int i = 0;i<b.length();i++)
        {
            if(b.charAt(i)-97>0 && b.charAt(i)-97<27)
            {
                arr1[b.charAt(i)-97] = arr1[b.charAt(i)-97]+1;
            }
        }
        for(int i = 0;i<26;i++) {
            for(int j =0;j<Math.min(arr[i],arr1[i]);j++) {
                ans = ans +""+((char)(i+97))+"";
            }
        }
        return ans;
    }

}
