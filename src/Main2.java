/* Class: CISC 3130
 * Section: MY9, TY2, TY9
 * EmplId:
 * Name: Jonathan Scarpelli
 */

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* The Playlist implementation */
class TopStreamingArtists {
    private Node head;

/* A node represents an artist */
class Node {
    public String data;
    public Node next;
    public Node(String artist) {
        data = artist;
        next = null;
    }
}

    void sortedInsert(Node new_node)
    {
         Node current;

         /* Special case for head node */
         if (head == null || head.data.compareToIgnoreCase(new_node.data) > 0)
         {
            new_node.next = head;
            head = new_node;
         }
         else {

            /* Locate the node before point of insertion. */
            current = head;

            while (current.next != null &&
                   current.next.data.compareToIgnoreCase(new_node.data) < 0)
                  current = current.next;

            new_node.next = current.next;
            current.next = new_node;
         }
     }

                  /*Utility functions*/

    /* Function to create a node */
    Node newNode(String data)
    {
       Node x = new Node(data);
       return x;
    }

     /* Function to print linked list */
     void printList()
     {
         Node temp = head;
         while (temp != null)
         {
            System.out.print(temp.data+" ");
            temp = temp.next;
         }
     }



       //     if(prev.name.compareToIgnoreCase(current.name) > 0)

          /*  else if(word.wm.getWord().compareToIgnoreCase(current.wm.getWord()) > 0)
            {
                prev = current;
                current.next = word;
            }
          */  // No else condition is required

 }



/* The List TopStreamingArtists is composed of a series of artist names */

class Hash {

    public void countFrequencies(List<String> list)
    {
        // hashmap to store the frequency of element
        Map<String, Integer> hm = new HashMap<String, Integer>();

        for (String i : list) {
            Integer j = hm.get(i);
            hm.put(i, (j == null) ? 1 : j + 1);
        }

        //List<String[]> dataLines = new ArrayList<>();
       // dataLines.add(new String[]
        //    { "Artist" + "," + "Count" + "\n" });
       List<String> artistList = new ArrayList<String>();;
        StringBuilder sb = new StringBuilder();
        sb.append("Artist,");
        sb.append(',');
        sb.append("Count");
        sb.append('\n');

        TopStreamingArtists llist = new TopStreamingArtists();
        // displaying the occurrence of elements in the arraylist
        for (Map.Entry<String, Integer> val : hm.entrySet()) {
            artistList.add(val.getKey());
            sb.append(val.getKey());
            sb.append(',');
            sb.append(val.getValue());
            sb.append('\n');
           // dataLines.add(new String[]
            //{ val.getKey() + "," + val.getValue() + "\n" });
            //System.out.println(val.getKey() + " "
                              // + val.getValue());
        }

        File csvOutputFile = new File("../data/test.csv");
        try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
            writer.write(sb.toString());

            //dataLines.stream()
             // .map(this::convertToCSV)
             // .forEach(pw::println);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
        //assertTrue(csvOutputFile.exists());
        for(String s : artistList) {
            //System.out.println(s);
            llist.sortedInsert(llist.newNode(s));
        }
        System.out.println("Created Linked List");
        llist.printList();
    }


}
/* A node represents an artist */
public class Main2 {

    public static void main(String[] args) {

        String csvFile = "../data/regional-global-daily-latest.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        int cols = 1;  // arbitrary number represents columns to create
        int rows = 200; // arbitrary number represents rows to create;
        String[] myList = new String[rows];
        List<String> values = new ArrayList<String>();
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                System.out.println(country[2]);
                if (country[2].replace("\"", "").trim().equals("000 Hours (with Justin Bieber)")) {
                    values.add("10000 Hours (with Justin Bieber)");
                } else if (country[2].replace("\"", "").trim().equals("next")) {
                    values.add("Ariana Grande");
                }else
                if (!country[2].replace("\"", "").trim().equals("Artist")) {
               values.add(country[2].replace("\"", "").trim());
                }
                }

                //for (int i = 0; i < values.size(); i++) {
                 //   System.out.println("Artist: " + values.get(i));
                //  }
                  Hash count = new Hash();
                  count.countFrequencies(values);



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {


            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
