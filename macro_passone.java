import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
public class Passone {
    public static void main(String[] args) {
        String filename = "input.txt";
        HashMap<String,Integer> PosPar=new HashMap<>();  //position parameters
        HashMap<String,Integer> KeywordPar=new HashMap<>();//key word parameters
        HashMap<String,Integer> MNT=new HashMap<>();  //mnt
        int loc=0;
        String Macroname;
        boolean macroFound=false,processing=false,endFound=false;
        // System.out.println("  Macro Definition Table");

                

        String symfile="passtwo.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(symfile))) {
            try {
            FileReader fileReader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                // Split the line into words using spaces as the delimiter
                String[] words = line.split("\\s+");
                if(words.length==1 && words[0].equals("MEND")){
                    bufferedWriter.write("MEND");
                    endFound=false;   //begin to find macro;
                    processing=false;  //for next processing
                    macroFound=false;
                    continue;
                }
                if(words.length==1 && words[0].equals("MACRO")){
                    macroFound=true;   //begin to find macro;
                }
                else if(macroFound==true && processing==false && endFound==false){
                    System.out.print(words[0]+" ");
                    Macroname=words[0];
                    MNT.put(words[0],loc);
                    String[] parameters = words[1].split(",");
                    for(int i=0;i<parameters.length;i++){
                        System.out.print(parameters[i]+",");
                        if(parameters[i].contains("=")){
                            KeywordPar.put(parameters[i],KeywordPar.size()+1);
                        }
                        else{
                            PosPar.put(parameters[i],PosPar.size()+1);
                        }
                    }bufferedWriter.newLine();
                    processing=true;
                }
                else if(processing==true && endFound==false){
                    for(int i=0;i<words.length;i++){
                        if(PosPar.containsKey(words[i])==true){
                            bufferedWriter.write(words[i]+","+PosPar.get(words[i])+" ");
                        }
                        else if(KeywordPar.containsKey(words[i])==true){
                            bufferedWriter.write(words[i]+","+KeywordPar.get(words[i])+" ");
                        }
                        else{
                            bufferedWriter.write(words[i]+" ");
                        }
                    }bufferedWriter.newLine();
                }
                loc++;
            }
            bufferedReader.close();
            } catch (IOException e) {
                System.err.println("Error reading the file: " + e.getMessage());
            }
    
            System.out.println("Data has been written to the File successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        
        
        // System.out.println("\nPosition parameters");
        // for (Entry<String, Integer> entry : PosPar.entrySet()) {
        //         String key = entry.getKey();
        //         Integer value = entry.getValue();
        //         System.out.println("" + key + " -> " + value);
        // }System.out.println();

        // System.out.println("\nKeyWord parameters");
        // for (Entry<String, Integer> entry : KeywordPar.entrySet()) {
        //         String key = entry.getKey();
        //         Integer value = entry.getValue();
        //         System.out.println(" " + key + " -> " + value);
        // }System.out.println();

        // System.out.println("\nMacro definiton table");
        // for (Entry<String, Integer> entry : MNT.entrySet()) {
        //         String key = entry.getKey();
        //         Integer value = entry.getValue();
        //         System.out.println("Key: " + key + ", Value: " + value);
        // }System.out.println();
    }
}

/*
MACRO
DCRE &AREG1,&AREG2,&ARG3=
MOVR AREG,&AREG1
MOVR BREG,&AREG2
MOVR CREG,&AREG3=
DCR AREG,&AREG3
DCR BREG,CREG
MEND
MACRO
INCR &AREG3,&AREG4,&ARG2=
MOVR AREG,&AREG1
MOVR BREG,&AREG2
MOVR CREG,&AREG3=
DCR AREG,&AREG3
DCR BREG,CREG
MEND
START
ADD B,A
MOVER BREG,CREG
MOVEM LS,GS
INCR DATA1,DATA2
SUB C,D
STOP
END
*/
