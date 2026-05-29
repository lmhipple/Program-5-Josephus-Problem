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
            int count = 1;
            do {
               result += count + "-" + current.name;
               current = current.next;
               count++;
               if (current != circle) {
                  result += ", ";
               }
         } while(current != circle);
         return result;
         }
      }

}

/*

# PROGRAM OUTPUT

  ----jGRASP exec: java JosephusDriver
 === Elimination count is 9 ===
 Remaining survivors: 1-Marcelle, 2-Hashir, 3-Boubacar, 4-edgar, 5-Nelson, 6-Sarinya, 7-Dario, 8-Joaquin, 9-Aurel, 10-Crystal, 11-Michael, 12-Jesse, 13-Joshua, 14-Thomas, 15-Angelina, 16-Visal, 17-Zoheb, 18-Owen, 19-Marc, 20-Grace, 21-Milady, 22-Lily
 
 Continue elimination? <press enter>
 
 Aurel eliminated!
 Remaining survivors: 1-Crystal, 2-Michael, 3-Jesse, 4-Joshua, 5-Thomas, 6-Angelina, 7-Visal, 8-Zoheb, 9-Owen, 10-Marc, 11-Grace, 12-Milady, 13-Lily, 14-Marcelle, 15-Hashir, 16-Boubacar, 17-edgar, 18-Nelson, 19-Sarinya, 20-Dario, 21-Joaquin
 
 Continue elimination? <press enter>
 
 Owen eliminated!
 Remaining survivors: 1-Marc, 2-Grace, 3-Milady, 4-Lily, 5-Marcelle, 6-Hashir, 7-Boubacar, 8-edgar, 9-Nelson, 10-Sarinya, 11-Dario, 12-Joaquin, 13-Crystal, 14-Michael, 15-Jesse, 16-Joshua, 17-Thomas, 18-Angelina, 19-Visal, 20-Zoheb
 
 Continue elimination? <press enter>
 
 Nelson eliminated!
 Remaining survivors: 1-Sarinya, 2-Dario, 3-Joaquin, 4-Crystal, 5-Michael, 6-Jesse, 7-Joshua, 8-Thomas, 9-Angelina, 10-Visal, 11-Zoheb, 12-Marc, 13-Grace, 14-Milady, 15-Lily, 16-Marcelle, 17-Hashir, 18-Boubacar, 19-edgar
 
 Continue elimination? <press enter>
 
 Angelina eliminated!
 Remaining survivors: 1-Visal, 2-Zoheb, 3-Marc, 4-Grace, 5-Milady, 6-Lily, 7-Marcelle, 8-Hashir, 9-Boubacar, 10-edgar, 11-Sarinya, 12-Dario, 13-Joaquin, 14-Crystal, 15-Michael, 16-Jesse, 17-Joshua, 18-Thomas
 
 Continue elimination? <press enter>
 
 Boubacar eliminated!
 Remaining survivors: 1-edgar, 2-Sarinya, 3-Dario, 4-Joaquin, 5-Crystal, 6-Michael, 7-Jesse, 8-Joshua, 9-Thomas, 10-Visal, 11-Zoheb, 12-Marc, 13-Grace, 14-Milady, 15-Lily, 16-Marcelle, 17-Hashir
 
 Continue elimination? <press enter>
 
 Thomas eliminated!
 Remaining survivors: 1-Visal, 2-Zoheb, 3-Marc, 4-Grace, 5-Milady, 6-Lily, 7-Marcelle, 8-Hashir, 9-edgar, 10-Sarinya, 11-Dario, 12-Joaquin, 13-Crystal, 14-Michael, 15-Jesse, 16-Joshua
 
 Continue elimination? <press enter>
 
 edgar eliminated!
 Remaining survivors: 1-Sarinya, 2-Dario, 3-Joaquin, 4-Crystal, 5-Michael, 6-Jesse, 7-Joshua, 8-Visal, 9-Zoheb, 10-Marc, 11-Grace, 12-Milady, 13-Lily, 14-Marcelle, 15-Hashir
 
 Continue elimination? <press enter>
 
 Zoheb eliminated!
 Remaining survivors: 1-Marc, 2-Grace, 3-Milady, 4-Lily, 5-Marcelle, 6-Hashir, 7-Sarinya, 8-Dario, 9-Joaquin, 10-Crystal, 11-Michael, 12-Jesse, 13-Joshua, 14-Visal
 
 Continue elimination? <press enter>
 
 Joaquin eliminated!
 Remaining survivors: 1-Crystal, 2-Michael, 3-Jesse, 4-Joshua, 5-Visal, 6-Marc, 7-Grace, 8-Milady, 9-Lily, 10-Marcelle, 11-Hashir, 12-Sarinya, 13-Dario
 
 Continue elimination? <press enter>
 
 Lily eliminated!
 Remaining survivors: 1-Marcelle, 2-Hashir, 3-Sarinya, 4-Dario, 5-Crystal, 6-Michael, 7-Jesse, 8-Joshua, 9-Visal, 10-Marc, 11-Grace, 12-Milady
 
 Continue elimination? <press enter>
 
 Visal eliminated!
 Remaining survivors: 1-Marc, 2-Grace, 3-Milady, 4-Marcelle, 5-Hashir, 6-Sarinya, 7-Dario, 8-Crystal, 9-Michael, 10-Jesse, 11-Joshua
 
 Continue elimination? <press enter>
 
 Michael eliminated!
 Remaining survivors: 1-Jesse, 2-Joshua, 3-Marc, 4-Grace, 5-Milady, 6-Marcelle, 7-Hashir, 8-Sarinya, 9-Dario, 10-Crystal
 
 Continue elimination? <press enter>
 
 Dario eliminated!
 Remaining survivors: 1-Crystal, 2-Jesse, 3-Joshua, 4-Marc, 5-Grace, 6-Milady, 7-Marcelle, 8-Hashir, 9-Sarinya
 
 Continue elimination? <press enter>
 
 Sarinya eliminated!
 Remaining survivors: 1-Crystal, 2-Jesse, 3-Joshua, 4-Marc, 5-Grace, 6-Milady, 7-Marcelle, 8-Hashir
 
 Continue elimination? <press enter>
 
 Crystal eliminated!
 Remaining survivors: 1-Jesse, 2-Joshua, 3-Marc, 4-Grace, 5-Milady, 6-Marcelle, 7-Hashir
 
 Continue elimination? <press enter>
 
 Joshua eliminated!
 Remaining survivors: 1-Marc, 2-Grace, 3-Milady, 4-Marcelle, 5-Hashir, 6-Jesse
 
 Continue elimination? <press enter>
 
 Milady eliminated!
 Remaining survivors: 1-Marcelle, 2-Hashir, 3-Jesse, 4-Marc, 5-Grace
 
 Continue elimination? <press enter>
 
 Marc eliminated!
 Remaining survivors: 1-Grace, 2-Marcelle, 3-Hashir, 4-Jesse
 
 Continue elimination? <press enter>
 
 Grace eliminated!
 Remaining survivors: 1-Marcelle, 2-Hashir, 3-Jesse
 
 Continue elimination? <press enter>
 
 Jesse eliminated!
 Remaining survivors: 1-Marcelle, 2-Hashir
 
 Continue elimination? <press enter>
 
 Marcelle eliminated!
 Hashir is the last survivor!
 
  ----jGRASP: Operation complete.

*/
