import java.util.*;
import java.io.*;
class fcfs{
    int n;
    int []tat;
    int []wt;
    int []ct;
    int []at;
    int []bt;
    double avgWaitTime=0,avgTat=0;
    String []id;
    fcfs(int n){
        this.n=n;
        tat=new int[n];
        wt=new int[n];
        ct=new int[n];
        at=new int[n];
        bt=new int[n];
        id=new String[n];
    }
    void insert(){
        Scanner sc=new Scanner(System.in);
        for(int i=0;i<n;i++){
            System.out.println("enter id,at,bt");
            id[i]=sc.next();
            at[i]=sc.nextInt();
            bt[i]=sc.nextInt();
        }
        sortData();
    }
    void sortData(){
        for(int i=0;i<n-1;i++){
            for(int j=i+1;j<n;j++){
                if(at[i]>at[j]){
                    int a=at[i];
                    at[i]=at[j];
                    at[j]=a;
                    
                    a=bt[i];
                    bt[i]=bt[j];
                    bt[j]=a;
                    
                    String p=id[i];
                    id[i]=id[j];
                    id[j]=p;
                }
            }
        }
        Calculate();
    }
    void display(){
        for(int i=0;i<n;i++){
            System.out.println(id[i]+" "+at[i]+" "+bt[i]+" "+ct[i]+" "+tat[i]+" "+wt[i]);
        }
        System.out.println("Avg wt "+avgWaitTime);
        System.out.println("Avg tat "+avgTat);
    }
    /*p1 1 2 
    p2 2 4
    p3 2 5
    p4 5 6
    p5 5 5
    */
    void Calculate(){
        for(int i=0;i<n;i++){
            if(i==0){
                ct[i]=at[i]+bt[i];
            }
            else{
                if(at[i]>ct[i-1]){
                    ct[i]=at[i]+bt[i];
                }
                else{
                    ct[i]=ct[i-1]+bt[i];
                }
            }
            tat[i]=ct[i]-at[i];
            wt[i]=tat[i]-bt[i];
            avgWaitTime+=(wt[i]);
            avgTat+=(tat[i]);
        }
        avgWaitTime=avgWaitTime/n;
        avgTat=avgTat/n; 
        display();
    }
}
class HelloWorld {
    public static void main(String[] args) {
        fcfs obj=new fcfs(5);
        obj.insert();
    }
}
/*
enter id,at,bt
p1 1 2
enter id,at,bt
p2 2 4
enter id,at,bt
p3 2 5

enter id,at,bt
p4 5 6
enter id,at,btp5 5 5
p1 1 2 3 2 0
p2 2 4 7 5 1
p3 2 5 12 10 5
p4 5 6 18 13 7
p5 5 5 23 18 13
Avg wt 5.2
Avg tat 9.6
*/
