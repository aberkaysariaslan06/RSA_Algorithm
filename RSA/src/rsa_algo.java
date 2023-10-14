/*
    Ahmet Berkay Sariaslan - 54490639066
    CMPE 325 - Extra Assignment

 */

import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class rsa_algo {
    public static void main(String[] args) {
        // p,q n = p*q Phi(n) = p-1 * q-1 1<e<Phi(n) obeb(e,phi(n)=1  (n,e)
        //int ust = UstelMod(69,47,73);
        //System.out.println("Ust : " + ust);
        //UstelMod(49,47,43);

        Scanner key = new Scanner(System.in);
        int p,q,n;
        int e=0;
        System.out.print("Asal Sayi 1  : ");
        p = key.nextInt();
        System.out.print("Asal Sayi 2  : ");
        q = key.nextInt();
        n = p*q;
        int phi = Totient(n);
        int rand;
        System.out.println("Key Generation : ");
        for(int i=2; i<phi;i++){
             if(Obeb(phi,i)==1)
                 System.out.println( " " + i);

        }
        System.out.print("number of e   : " );
        e= key.nextInt();

        /*while(true){
            rand = new Random().nextInt(1,phi);
            if (Obeb(rand,phi)==1)
                e=rand;
                break;
        }
         */
        System.out.print("(e,n) -> " + (e) + "," + (n));
        System.out.print("\n Text Here : ");
        String metin = key.next();
        metin = RSA(metin,n,e);
        System.out.println("Ciphertext : " + metin);
        /*int d;
        String decryptedMessage = DecryptRSA(metin, n, d);
        System.out.println("Decrypted message: " + decryptedMessage);
    */


    /*
        int totient = Totient(37);
        System.out.println("Totient :" + totient);
*/

    }
    static int Obeb(int x, int y ){ //GCD Function
        int min = Math.min(x,y);
        int obeb = 1;
        for (int i=2; i<=min;i++){
            if(x%i==0 && y%i==0){
                obeb = i ;
            }
        }
        return obeb;
    }
    static int Totient(int n){ //Totient Function, p-1 * q-1
        int adet = 0;
        for (int i=1;i<n;i++){
            if(Obeb(n,i)==1)
                adet++;
        }

        return adet;
    }
    static int UstelMod(int a, int b, int n) {
        // I can't do BigInteger, but I try this algorithm for big numbers
        // a^b mod(n) a*a*a...a*a a^(n-m) m : a.length
        //69^47 mod 73
        int _a = a % n ; //_a  : a remaining
        int _b = b;      //_b  : b remaining
        if(b==0){
            return  1;
        }
        while(_b > 1 ){
            _a = _a * a;
            _a = _a % n;
            _b--;
        }
        return  _a;
    }
    static String RSA(String text, int n, int e) { //m ^e mod n
        char [] chars = text.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            builder.append(UstelMod(chars[i],e,n));
        }
        return builder.toString();
    }
    static String DecryptRSA(String ciphertext, int n, int d) {
        // ciphertext ^d mod n
        char [] chars = ciphertext.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            builder.append((char) UstelMod(chars[i], d, n));
        }
        return builder.toString();
    }


}
//353 and 173 big prime numb