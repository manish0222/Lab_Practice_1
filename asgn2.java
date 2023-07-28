import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
public class asgn2 {
    public static void main(String[] args) {
        String filename = "input.txt"; // Replace 'input.txt' with the actual file path

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            StringBuilder sentence = new StringBuilder();

            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                for (String word : words) {
                    if(word!="")System.out.println(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
