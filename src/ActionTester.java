import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class ActionTester {
    static List<List<Integer>> sets = new ArrayList<>();
    static int ans = 1000;
    static List<Integer> needed;
    static int max;

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        try {
            File f = new File("C:\\Users\\Alexh\\IdeaProjects\\Sandbox\\src\\cse373hw4.txt");
            Scanner scan = new Scanner(f);
            max = Integer.parseInt(scan.nextLine());
            scan.nextLine();
            needed = new ArrayList<>();
            for (int i = 0; i < max; i++)
                needed.add(i + 1);
            while (scan.hasNextLine()) {
                String set = scan.nextLine().trim();
                List<Integer> newSet = new ArrayList<>();
                while (true) {
                    if (set.indexOf(" ") < 0) {
                        newSet.add(Integer.parseInt(set));
                        break;
                    }
                    newSet.add(Integer.parseInt(set.substring(0, set.indexOf(" "))));
                    set = set.substring(set.indexOf(" ") + 1);
                }
                if (sets.size() == 0)
                    sets.add(newSet);
                List sets1 = new ArrayList(newSet);
                for (int i = 0; i < sets.size(); i++) {
                    sets1 = new ArrayList(newSet);
                    int ape = newSet.size();
                    for (int k = 0; k < ape; k++) {
                        if (sets.get(i).contains(sets1.get(k))) {
                            sets1.remove(k--);
                            ape--;
                        }
                    }
                    if (sets1.size() == 0) {
                        break;
                    }
                }

                if (sets1.size() != 0) {
                    for (int i = 0; i < sets.size(); i++) {
                        if (newSet.size() >= sets.get(i).size()) {
                            sets.add(i, newSet);
                            break;
                        }
                        if (i == sets.size() - 1)
                            sets.add(newSet);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("ERROR");
            e.printStackTrace();
        }
        List sets1 = new ArrayList(sets);
        List needed1 = new ArrayList(needed);
        dfs(sets1, needed1, 0);
        long endTime = System.nanoTime();
        System.out.println(ans);
        long duration = (endTime - startTime);
        System.out.println(duration);
    }

    public static void forceRemove(List<List<Integer>> myList, List<Integer> needed, int setsUsed, int removeAt) {
        for (Integer i : myList.get(removeAt)) {
            if (needed.contains(i))
                needed.remove(i);
        }
        myList.remove(removeAt);
        dfs(myList, needed, ++setsUsed);
    }


    public static void dfs(List<List<Integer>> myList, List<Integer> needed, int setsUsed) {
        if (setsUsed >= ans)
            return;
        if (needed.size() <= 0) {
            if (setsUsed < ans)
                ans = setsUsed;
            return;
        }
        int target = needed.get(0);
        List<Integer> remover = targetFinder(target, myList);
        int removerLen = remover.size();
        for (int i = 0; i < removerLen; i++) {
            List sets1 = new ArrayList(myList);
            List needed1 = new ArrayList(needed);
            forceRemove(sets1, needed1, setsUsed, remover.get(0));
            remover.remove(0);
            remover.stream().map(j -> j - 1).collect(Collectors.toList());
        }
        return;
    }

    public static List<Integer> targetFinder(int target,List<List<Integer>> myList)
    {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0;i<myList.size();i++)
        {
            if(myList.get(i).contains(target))
                ans.add(i);
        }
        return ans;
    }

}