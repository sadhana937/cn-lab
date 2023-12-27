// Program for error detecting code using CRC-CCITT(16 - bits)

import java.util.Scanner;

public class CRC {
    
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("At sender side:");
        System.out.println("Enter message bits: ");
        String message = sc.nextLine();
        System.out.println("Enter generator: ");
        String generator = sc.nextLine();

        int data[] = new int[message.length() + generator.length() - 1];
        int divisor[] = new int[generator.length()];

        for(int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for(int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // Calculation
        for (int i = 0; i < message.length(); i++){
            if(data[i] == 1)
                for(int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        System.out.println("The checksum code is:");
        for (int i = 0; i < message.length(); i++)
            System.out.print(data[i]);
        System.out.println();

        System.out.println("At receiver's side:");
        System.out.println("Enter checksum code: ");
        message = sc.nextLine();
        System.out.println("Enter generator: ");
        generator = sc.nextLine();

        data = new int[message.length() + generator.length() - 1];
        divisor = new int[generator.length()];

        for(int i = 0; i < message.length(); i++)
            data[i] = Integer.parseInt(message.charAt(i) + "");

        for(int i = 0; i < generator.length(); i++)
            divisor[i] = Integer.parseInt(generator.charAt(i) + "");

        // calculating remainder
        for(int i = 0; i < message.length(); i++){
            if (data[i] == 1)
                for(int j = 0; j < divisor.length; j++)
                    data[i + j] ^= divisor[j];
        }

        boolean valid = true;
        for( int i = 0; i < data.length; i++)
            if(data[i] == 1){
                valid = false;
                break;
            }

        if(valid)
            System.out.println("Data stream is valid");
        else
            System.out.println("Data stream is invalid. CRC error occurred");
        sc.close();
    }
}
/* 
OUTPUT:
At sender side:
Enter message bits:
11001100
Enter generator:
1111
The checksum code is:
00000000
At receiver's side:
Enter checksum code:
10000000
Enter generator:
1111
Data stream is invalid. CRC error occurred


At sender side:
Enter message bits:
11001100
Enter generator: 
1010
The checksum code is:
00000000
At receiver's side:
Enter checksum code: 
00000000
Enter generator: 
1010
Data stream is valid
*/
