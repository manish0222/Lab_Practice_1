import java.io.*;
import java.net.*;
import java.util.*;

public class socket2{
    public static void main(String[] args){
        try{
            ServerSocket ss = new ServerSocket(8080);
            Socket s = ss.accept();
            DataInputStream in = new DataInputStream(s.getInputStream());
            System.out.println("Hello");
            String line = "";
            System.out.println("Connection is Successfully established");
            while(!line.equals("over")){
                try{
                    line = in.readUTF();
                    System.out.println(line);
                }
                catch(IOException i){
                    System.out.println(i);
                }
            }
            System.out.println("Connection is Successfully established");
            ss.close();
        }
        catch(Exception e){
             System.out.println(e);
        }
    }
}
/*
javac socket2.java
java socket2
*/
