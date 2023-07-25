import java.util.*;
public class Main{
    public static void main(String args[]){
      System.out.print("   Round Robin running Without Considering the Arrival time PLZ NOTE ");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the wait time per task ");
        int t=sc.nextInt();
        System.out.print("Enter the total tasks  ");
        int n=sc.nextInt();
        System.out.println("Time per Round "+n);
        int BurstTime[]=new int[n];
        int CompletionTime[]=new int[n];
        int TAT[]=new int[n];
        int WT[]=new int[n];
        int RemBurstTime[]=new int[n];
        for(int i=0;i<n;i++){
            System.out.print("Enter Burst Time of task "+(i+1)+" ");
            BurstTime[i]=sc.nextInt();
            RemBurstTime[i]=BurstTime[i];
        }
        int count=0,time=0;
        float tat=0,wt=0;
        System.out.println("-----  Time Chart ------");
        while(count<n){
            for(int i=0;i<n;i++){
                if(RemBurstTime[i]==0){
                    count++;
                    RemBurstTime[i]=-1;
                }
                else if(RemBurstTime[i]==-1) continue;
                else if(RemBurstTime[i]>=t){
                    System.out.print("[P"+(i+1)+"->"+t+"] ");
                    time+=t;
                    RemBurstTime[i]-=t;
                    if(RemBurstTime[i]==0){
                        CompletionTime[i]=time;
                    }
                }
                else{
                    System.out.print("[P"+(i+1)+"->"+RemBurstTime[i]+"] ");
                    time+=RemBurstTime[i];
                    RemBurstTime[i]=0;
                    if(RemBurstTime[i]==0){
                        CompletionTime[i]=time;
                    }
                }
            }
        }
        System.out.println();for(int i=0;i<n;i++){
            TAT[i]=CompletionTime[i];
            tat+=TAT[i];
            WT[i]=TAT[i]-BurstTime[i];
            wt+=WT[i];
        }
        
        System.out.println("BT CT  TAT WT ");
        for(int i=0;i<n;i++){
            System.out.println(BurstTime[i]+" "+CompletionTime[i]+"  "+TAT[i]+"  "+WT[i]);
        }
        System.out.println(" Total Time is "+time);
        System.out.println(" Avearage Turn Around Time is "+(tat/n));
        System.out.println(" Average Waiting Time is "+(wt/n));
    }
}
