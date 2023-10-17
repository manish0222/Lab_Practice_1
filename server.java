// Kaustubh Joshi 31233

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.DataOutputStream;


public class server{
    public static void main(String[] args){
        try{
            Scanner sc1 = new Scanner (System. in) ;
         server s = new server ("127.0.0.1",8080);
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            String line = "";
            while (!line.equals("over")){
                try{
                    System.out.println("write line");
                    line = sc1.nextLine();
                    out.writeUTF(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }
            out.flush();
            out.close();
            s.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
/*got to terminal
type javac server.java
java server
*/
