import java.util.*;
// import java.io.*;
class lru_PAGE_Replacement 
{ 
    // Method to find page faults using indexes 
    static int pageFaults(int pages[], int n, int capacity) 
    { 
        HashSet<Integer> s = new HashSet<>(capacity); 
        HashMap<Integer, Integer> indexes = new HashMap<>(); 
       
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            if (s.size() < capacity) 
            { 
                if (!s.contains(pages[i])) 
                { 
                    s.add(pages[i]); 
                    page_faults++; 
                } 
                indexes.put(pages[i], i); 
            } 
            else
            { 
                if (!s.contains(pages[i])) 
                { 
                    int lru = Integer.MAX_VALUE, val=Integer.MIN_VALUE; 
                    Iterator<Integer> itr = s.iterator(); 
                      
                    while (itr.hasNext()) { 
                        int temp = itr.next(); 
                        if (indexes.get(temp) < lru) 
                        { 
                            lru = indexes.get(temp); 
                            val = temp; 
                        } 
                    } 
                  
                    s.remove(val); 
                    indexes.remove(val); 
                    s.add(pages[i]); 
                    page_faults++; 
                } 
       
                indexes.put(pages[i], i); 
            }

            // Display the queue and pages at each iteration
            System.out.print("Iteration " + (i + 1) + ": ");
            System.out.print("Pages: {");
            for (int page : s) {
                System.out.print(page + " ");
            }
            System.out.print("}\n");
        } 

        return page_faults; 
    }
}


class PageReplacementFIFO {
    private int pageSize;
    private ArrayList<Integer> pageRequests;
    private ArrayList<Integer> pageFrames;
    private Queue<Integer> fifoQueue;

    public PageReplacementFIFO(int pageSize, ArrayList<Integer> pageRequests) {
        this.pageSize = pageSize;
        this.pageRequests = pageRequests;
        this.pageFrames = new ArrayList<>();
        this.fifoQueue = new LinkedList<>();
    }

    public void simulateFIFO() {
        int pageFaults = 0;
        for (int i = 0; i < pageRequests.size(); i++) {
            int currentPage = pageRequests.get(i);

            // Check if the page is already in a page frame
            if (pageFrames.contains(currentPage)) {
                System.out.println("Page " + currentPage + " is already in memory.");
            } else {
                pageFaults++;
                if (pageFrames.size() < pageSize) {
                    pageFrames.add(currentPage);
                    fifoQueue.add(currentPage);
                } else {
                    int removedPage = fifoQueue.poll();
                    pageFrames.remove(Integer.valueOf(removedPage));
                    pageFrames.add(currentPage);
                    fifoQueue.add(currentPage);
                }
                System.out.println("Page " + currentPage + " caused a page fault.");
            }

            System.out.println("Current Page Frames: " + pageFrames);
        }
        double hits=(pageRequests.size()-pageFaults);
        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits are : " + hits);
        System.out.println("Total Page Hits ratio is : " + (hits)/(pageRequests.size()));
    }
}
class OptimalPageReplacement {
    public void simulateOptimalPageReplacement(int numFrames, int[] pageReferences) {
        List<Integer> frames = new ArrayList<>();
        Map<Integer, Integer> nextOccurrence = new HashMap<>();

        int pageFaults = 0;
        int pageHits = 0;

        for (int i = 0; i < pageReferences.length; i++) {
            int page = pageReferences[i];

            if (frames.contains(page)) {
                pageHits++;
            } else {
                pageFaults++;
                if (frames.size() < numFrames) {
                    frames.add(page);
                } else {
                    int pageIndexToReplace = findPageToReplace(pageReferences, i, frames, nextOccurrence);
                    frames.set(pageIndexToReplace, page);
                }
            }

            nextOccurrence.put(page, findNextOccurrence(pageReferences, i, page));
            displayQueue(frames);
        }

        System.out.println("Total Page Faults: " + pageFaults);
        System.out.println("Total Page Hits: " + pageHits);
    }

    private static int findPageToReplace(int[] pageReferences, int currentIndex, List<Integer> frames, Map<Integer, Integer> nextOccurrence) {
        int indexToReplace = 0;
        int farthestNextOccurrence = nextOccurrence.get(frames.get(0));

        for (int i = 1; i < frames.size(); i++) {
            int nextPage = frames.get(i);
            int nextOccurrenceIndex = nextOccurrence.get(nextPage);
            if (nextOccurrenceIndex > farthestNextOccurrence) {
                indexToReplace = i;
                farthestNextOccurrence = nextOccurrenceIndex;
            }
        }

        return indexToReplace;
    }

    private static int findNextOccurrence(int[] pageReferences, int currentIndex, int page) {
        for (int i = currentIndex + 1; i < pageReferences.length; i++) {
            if (pageReferences[i] == page) {
                return i;
            }
        }
        return Integer.MAX_VALUE;
    }

    private static void displayQueue(List<Integer> frames) {
        System.out.print("Queue: ");
        for (Integer frame : frames) {
            System.out.print(frame + " ");
        }
        System.out.println();
    }
}


class PageReplacementAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1)FIFO\n2)Optimal\n3)LRU \t ");
        int a=scanner.nextInt();
        if(a==1){
            int pageSize = 3;
            ArrayList<Integer> pageRequests = new ArrayList<>();
            pageRequests.add(3);
            pageRequests.add(1);
            pageRequests.add(2);
            pageRequests.add(1);
            pageRequests.add(6);
            pageRequests.add(5);
            pageRequests.add(1);
            pageRequests.add(3);
            
            PageReplacementFIFO fifo = new PageReplacementFIFO(pageSize, pageRequests);
            fifo.simulateFIFO();
        }
        else if(a==2){
            System.out.print("Enter the number of frames: ");
        int numFrames = scanner.nextInt();
        System.out.print("Enter the page reference string (comma separated): ");
        scanner.nextLine(); // Consume the newline character
        String input = scanner.nextLine();
        String[] pageReferencesStr = input.split(",");
        int[] pageReferences = Arrays.stream(pageReferencesStr)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .toArray();

        OptimalPageReplacement obj=new OptimalPageReplacement();
            obj.simulateOptimalPageReplacement(numFrames, pageReferences);
        }
        else if(a==3){
            lru_PAGE_Replacement obj=new lru_PAGE_Replacement();
             int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2}; 
            int capacity = 3; 
        System.out.println("Total page faults: " + obj.pageFaults(pages, pages.length, capacity)); 
        }
    }
}
//fifo done
//lru done
//optimal done
