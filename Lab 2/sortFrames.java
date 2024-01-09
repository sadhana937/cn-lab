// Program to sort frames using appropriate sorting techniques

import java.util.*;

public class sortFrames{
    public static void main(String[] args){
        List<int[]> frame = new ArrayList<>();
        System.out.println("Enter the number of frames: ");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Random random = new Random();

        for(int i = 0; i < n; i++){
            int seqNum = random.nextInt(1000) + 1;
            System.out.printf("Enter data for %dth frame:", i + 1);
            int data = sc.nextInt();
            frame.add(new int[] {seqNum, data});
        }
        System.out.println("\nBefore Sorting");
        for (int[] i : frame){
            System.out.printf("sequence number -> %d, Data -> %d\n", i[0], i[1]);
        }
        frame = sortFrame(frame);

        System.out.println("\n After sorting");
        for (int[] i : frame){
            System.out.printf("sequence number -> %d, Data -> %d\n", i[0], i[1]);

        }
        sc.close();
    }

    public static List<int []> sortFrame(List<int[]> frame){
        Collections.sort(frame, (a, b) -> Integer.compare(a[0], b[0]));
        return frame;
    }
}

/* OUTPUT:
Enter the number of frames: 
5
Enter data for 1th frame:1
Enter data for 2th frame:2
Enter data for 3th frame:3
Enter data for 4th frame:4
Enter data for 5th frame:5

Before Sorting
sequence number -> 775, Data -> 1
sequence number -> 153, Data -> 2
sequence number -> 821, Data -> 3
sequence number -> 131, Data -> 4
sequence number -> 534, Data -> 5

 After sorting
sequence number -> 131, Data -> 4
sequence number -> 153, Data -> 2
sequence number -> 534, Data -> 5
sequence number -> 775, Data -> 1
sequence number -> 821, Data -> 3
 */
