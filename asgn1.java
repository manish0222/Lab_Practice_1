import java.util.*;
class HelloWorld {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the total entries");
        int n=sc.nextInt();
        System.out.println("n is "+n);

        int arrivalTime[]=new int[n]; 
        int burstTime[]=new int[n]; 
        int completionTime[]=new int[n]; 
        int TAT[]=new int[n]; 
        int workTime[]=new int[n]; 
        // bool use=false;
        int completion=0;
        float tat=0,wt=0;
        for(int i=0;i<n;i++){
            System.out.print("Enter arr time and burst time");
            arrivalTime[i]=sc.nextInt();
            burstTime[i]=sc.nextInt();
            if(i==0){
                completion=burstTime[i]+arrivalTime[i];
            }
            else{
                completion+=burstTime[i];
            }
            completionTime[i]=completion;
            TAT[i]=completionTime[i]-arrivalTime[i];
            tat+=TAT[i];
            workTime[i]=TAT[i]-burstTime[i];
            wt+=workTime[i];
        }
        System.out.println("AT  BT  CT TAT  WT");
        for(int i=0;i<n;i++){
            System.out.println(arrivalTime[i]+"\t"+burstTime[i]+"\t"+completionTime[i]+"\t"+TAT[i]+"\t"+workTime[i]);
        }
        float x=(tat)/n;
        float y=(wt)/n;
        System.out.println("Average TAT is "+x);
        System.out.println("Average WT is "+y);
    System.out.println(" GANTT CHART IS AS FOLLOWS ");
    int count=0;
    // for(int i=0;i<n;i++){
        for(int j=0;j<completion;j++){
            System.out.print('_');
        }
        count=0;
        System.out.print("\n|");
        for(int j=0;j<completion;j++){
            if(j==completionTime[count]){
                count++;
                System.out.print('|');
            }
            else{
                System.out.print(' ');
            }
        }
        System.out.print("|\n");
        for(int j=0;j<completion;j++){
            System.out.print('-');
        }System.out.print("\n");
    // }
    }
}

