import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
public class passtwo {
    public static void main(String[] args) {
        String Symfile="symbol.txt";
        String Litfile="literal.txt";
        String passone="passone.txt";
        HashMap<String,String> SymbolTab=new HashMap<>();
        HashMap<String,String> LitTab=new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Symfile))) {
            System.out.println("\tSymbol Table is ");
            String line;
            StringBuilder sentence = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String words[]=line.split("\\s+");
                SymbolTab.put(words[0], words[1]);
            }
            // for (Map.Entry<String, String> entry : SymbolTab.entrySet()) {
            //     String key = entry.getKey();
            //     String value = entry.getValue();
            //     System.out.println("Key: " + key + ", Value: " + value);
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }


        try (BufferedReader br = new BufferedReader(new FileReader(Litfile))) {
            System.out.println("\tLietaral Table is ");
            String line;
            StringBuilder sentence = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String words[]=line.split("\\s+");
                LitTab.put(words[0], words[1]);
            }
            // for (Map.Entry<String, String> entry : LitTab.entrySet()) {
            //     String key = entry.getKey();
            //     String value = entry.getValue();
            //     System.out.println("Key: " + key + ", Value: " + value);
            // }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader br = new BufferedReader(new FileReader(passone))) {
            System.out.println("\tPassTwo is ");
            String line;
            boolean val=false;
            StringBuilder sentence = new StringBuilder();
            while ((line = br.readLine()) != null) {
                String words[]=line.split("\\s+");
                for(int i=0;i<words.length;i++){
                    if(words.length==1){
                        int lc=Integer.parseInt(words[0]);
                        for (Map.Entry<String, String> entry : LitTab.entrySet()) {
                            String key = entry.getKey();
                            // String value = entry.getValue();
                            System.out.println(lc+" "+ key);
                            lc++;
                        }
                    }
                    else if(words[i].equals('0') || words[i].contains("AS")){
                        // System.out.print(words[i]+" ");
                    }
                    else if(i==0){
                        System.out.print(words[i]+" ");
                    }
                    else if(words[i].contains(",")){
                        String[] wordsep = words[i].split(",");
                        if(wordsep[0].contains("IS") ){
                            System.out.print(wordsep[1]+" ");
                            val=true;
                        }
                        else if(wordsep[0].contains("ST")){
                            int k=Integer.parseInt(wordsep[1]);
                            System.out.print(k+" ");
                            val=true;
                        }
                        else if(wordsep[0].contains("LT")){
                            int k=Integer.parseInt(wordsep[1]);
                            System.out.print(k+" ");
                            val=true;
                        }
                        else if(wordsep[0].contains("DS")){
                            int k=Integer.parseInt(wordsep[1]);
                            System.out.print("- ");
                            val=true;
                        }
                        else if(wordsep[0].contains("AS")){
                            int k=Integer.parseInt(wordsep[1]);
                            System.out.print("- ");
                            val=true;
                        }
                    }
                    else{
                        int k=1;
                        try{
                            k=Integer.parseInt(words[i]);
                            System.out.print(k+" ");
                        }
                        catch(Exception e){}
                    }
                }System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
