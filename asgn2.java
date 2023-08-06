import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.plaf.synth.SynthStyle;
public class asgn2 {
    public static void main(String[] args) {
        String filename = "input.txt"; // Replace 'input.txt' with the actual file path
        HashMap<String,Integer> ImpSt=new HashMap<>();
        {
            ImpSt.put("stop", 1);
            ImpSt.put("add", 2);
            ImpSt.put("sub", 3);
            ImpSt.put("mult", 4);
            ImpSt.put("mover",5);
            ImpSt.put("movem",6);
            ImpSt.put("comp", 7);
            ImpSt.put("bc", 8);
            ImpSt.put("div", 9);
            ImpSt.put("read", 10);
            ImpSt.put("print", 11);
        };
        HashMap<String,Integer> AssDir=new HashMap<>();
        {
            AssDir.put("start", 1);
            AssDir.put("end", 2);
            AssDir.put("origin", 3);
            AssDir.put("equ", 4);
            AssDir.put("ltorg",5);
        };
        HashMap<String,Integer> DecSt=new HashMap<>();
        {
            DecSt.put("dc", 1);
            DecSt.put("ds", 2);
        };
        HashMap<String,Integer> Reg=new HashMap<>();
        {
            Reg.put("areg", 1);
            Reg.put("breg", 2);
        };
        ArrayList<ArrayList<String>> PassOne = new ArrayList<>();
        HashMap<String,Integer> SymbolTab=new HashMap<>();
        HashMap<String,Integer> LitTab=new HashMap<>();
        int loc=0;
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder sentence = new StringBuilder();
            while ((line = br.readLine()) != null) {
                // System.out.print(loc+" ");
                String[] words = line.split("\\s+");
                ArrayList<String> lines = new ArrayList<>();
                for (int i=0;i< words.length;i++) {
                    if(words[i].contains(":")){
                        String[] wordsep = words[i].split(":");
                        String firstWord = wordsep[0].trim();
                        // String secondWord = wordsep[1].trim();
                        lines.add(firstWord);
                        if(!SymbolTab.containsKey(firstWord))SymbolTab.put(firstWord, SymbolTab.size()+1);
                    }
                    else if(words[i].contains(",")){
                        String[] wordsep = words[i].split(",");
                        String firstWord = wordsep[0].trim();
                        String secondWord = wordsep[1].trim();
                        if(firstWord!="")lines.add(firstWord);
                        if(secondWord!="")lines.add(secondWord);
                        if(secondWord.contains("=")){
                            LitTab.put(secondWord, LitTab.size()+1);
                        }
                        else{
                            try{
                            if(Integer.parseInt(secondWord)>0  ){
                                // System.out.print("\n Int found\n");
                            }
                            }  
                            catch (Exception e) {
                            if(!secondWord.contains("=")){SymbolTab.put(secondWord,SymbolTab.size()+1);}
                            }
                        }      
                        
                        if(!Reg.containsKey(firstWord) && !DecSt.containsKey(firstWord) && !SymbolTab.containsKey(firstWord)){
                             SymbolTab.put(firstWord, SymbolTab.size()+1);
                        }
                        
                    }
                    else if(words[i].equals("start")){
                        lines.add(words[i]);
                        loc=loc+(Integer.parseInt(words[i+1]));
                        // break;
                    }
                    else{
                        if(words[i]!="")lines.add(words[i]);
                    }
                }
                PassOne.add(lines);
                loc++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String Passfile="passone.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(Passfile))) {
            loc=0;
        for (ArrayList<String> innerList : PassOne) {
            // System.out.print(loc);
            for (int i=0;i< innerList.size();i++) {
                String word=innerList.get(i);
                if(ImpSt.containsKey(word)){
                    bufferedWriter.write("IS,"+ImpSt.get(word)+" ");
                    // bufferedWriter.write(line1);
                }


                else if(AssDir.containsKey(word)){
                    bufferedWriter.write("AS,"+AssDir.get(word)+" ");
                    if(word.equals("start")){
                        String lc=innerList.get(i+1);
                        int k=Integer.parseInt(lc);
                        loc=--k;
                        break;
                    }
                    else if(word.equals("end")){
                        // loc=Integer.parseInt(innerList.get(i+1));
                    }
                }

                else if(DecSt.containsKey(word)){
                    bufferedWriter.write("DS,"+DecSt.get(word)+" ");
                    if(word.equals("ds")){
                        String lc=innerList.get(i+1);
                        int k=Integer.parseInt(lc);
                        loc+=k;
                        loc--;
                        // break;
                    }
                }
                else if(LitTab.containsKey(word)){
                    bufferedWriter.write("LT,"+LitTab.get(word)+" ");
                }
                else if(SymbolTab.containsKey(word) && i!=0){
                    bufferedWriter.write("ST,"+SymbolTab.get(word)+" ");
                }
                else if(SymbolTab.containsKey(word) && i==0){
                    // System.out.print("   ST,"+SymbolTab.get(word));
                    SymbolTab.put(word,loc);
                }
                else if(Reg.containsKey(word)){
                    bufferedWriter.write(""+word+","+Reg.get(word)+" ");
                }

                else{
                    bufferedWriter.write(" "+word+" ");
                }
            }
            loc++;
            // System.out.println();
            bufferedWriter.newLine();
        }
        
        // System.out.println("\nLiteral Table");
        // for (String key : LitTab.keySet()) {
        //     int value = LitTab.get(key);
        //     System.out.println(key + " -> " + value);
        // }

        System.out.println("Data has been written to the file successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
        String symfile="symbol.txt";
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(symfile))) {
            // System.out.println("\nSymbol Table");
            for (String key : SymbolTab.keySet()) {
                int value = SymbolTab.get(key);
                bufferedWriter.write(key + " " + value);
                bufferedWriter.newLine();
            }
    
            System.out.println("Data has been written to the Symbol Table successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }

}
/** testcase
JOHN:   start     200
        mover     l1,100    
        movem     l1,X       
l1:     mover     l2,='2'                    
X:     ds,2           
       end  
 */
