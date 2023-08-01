import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
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
        ArrayList<ArrayList<String>> PassOne = new ArrayList<>();
        HashMap<String,Integer> SymbolTab=new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder sentence = new StringBuilder();
            while ((line = br.readLine()) != null) {
                int count=0;
                String[] words = line.split("\\s+");
                ArrayList<String> lines = new ArrayList<>();
                for (String word : words) {
                    if(word.contains(":")){
                        String[] wordsep = word.split(":");
                        String firstWord = wordsep[0].trim();
                        String secondWord = wordsep[1].trim();
                        lines.add(firstWord);
                        lines.add(secondWord);
                    }
                    else if(word.contains(",")){
                        String[] wordsep = word.split(",");
                        String firstWord = wordsep[0].trim();
                        String secondWord = wordsep[1].trim();
                        lines.add(firstWord);
                        lines.add(secondWord);
                    }
                    else{
                        lines.add(word);
                    }
                } 
                PassOne.add(lines);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int loc=0;
        for (ArrayList<String> innerList : PassOne) {
            System.out.print(loc+" ");
            for (int i=0;i< innerList.size();i++) {
                String word=innerList.get(i);
                if(ImpSt.containsKey(word)){

                }
                else if(AssDir.containsKey(word)){
                    if(word.toLowerCase()=="start"){
                        loc=Integer.parseInt(innerList.get(i+1));
                    }
                    else if(word.toLowerCase()=="origin"){
                        loc=Integer.parseInt(innerList.get(i+1));
                    }
                }
            }
            loc++;
            System.out.println();
        }

        
    }
}
