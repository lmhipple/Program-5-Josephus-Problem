import java.util.*;
import java.io.*;

public class JosephusSim {
   private PersonNode circle;     // a PersonNode pointer that tracks first node
   private int size;              // the number of people in the circle
   private int eliminationCount;  // the number to count to for elimination       
   private PersonNode track;      // a PersonNode pointer to help with elimination

   public JosephusSim(String fileName) {
      try {
         // load names from the file in order, generating a singly linked list of PersonNodes
         Scanner file = new Scanner(new File(fileName));
         
         // using add() helper
         while(file.hasNext()) {
            add(file.next());
         }
         
         
         // make the ring circular by attaching last node's next to front
         PersonNode last = circle;
         while (last.next != null) {
            last = last.next;
         }
         last.next = circle;

         // remember the last node as the one in front of the next to get eliminated
         track = last;

         Random rand = new Random();
         // remember rand.nextInt(n) is 0 to n-1
         eliminationCount = rand.nextInt(size/2) + 1;
         System.out.println("=== Elimination count is " + eliminationCount + " ===");
         // generate, print, and save the random elimination count

      } catch(FileNotFoundException e) {
         System.out.println("Something went wrong with " + fileName);
      }
   }
   
   
   // optional helper method for constructing the circle
   private void add(String val) {
      PersonNode newNode = new PersonNode(val);
      
      if (circle == null) {
         circle = newNode;
      } else {
         PersonNode last = circle;
         while (last.next != null) {
            last = last.next;
         }
         last.next = newNode;
      }
      size++;   
   }

   
   public void eliminate() {
      // count to the elimination count
      
      // print who will be eliminated
      
      // eliminate the person and update "front" of the circle and size
      for (int i = 0; i < eliminationCount - 1; i++) {
         track = track.next;
      }
      PersonNode eliminated = track.next;
      System.out.println(eliminated.name + " eliminated!");
      track.next = eliminated.next;
      size--;
      circle = track.next;
   }
   
   public boolean isOver() {
      // check if there's only one person left in the circle
      return size == 1;
   }
   
   public String toString() {
      // if there's only one person left, print them as the last survivor
      if(size == 1) {
         return circle.name + " is the last survivor!";
         } else {
            String result = "Remaining survivors: ";
            PersonNode current = circle;
            do {
               result += current.name + " ";
               current = current.next;
         } while(current != circle);
         return result;
         }
      }


}
