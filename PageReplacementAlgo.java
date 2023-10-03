import java.util.*;
// import java.io.*;

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
    public static void simulateOptimalPageReplacement(int numFrames, int[] pageReferences) {
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


public class PageReplacementAlgo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("1)FIFO\n2)Optimal\t ");
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
    }
}
//fifo done
//lru not done
//optimal done
