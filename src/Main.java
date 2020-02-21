/* Class: CISC 3130
 * Section: TY9
 * EmplId: 23975673
 * Name: Jonathan Scarpelli
 */

import java.io.*;
import java.util.*;

/* The linked list implementation */
class TopStreamingArtists {
  private Node head;

/* A node represents an artist */
  class Node {
    private String data;
    private Node next;
    public Node(String artist) {
      data = artist;
      next = null;
    }
  }

    void sortedInsert(Node newNode) {
      Node current;

      /* Special case for head node */
      if (head == null || head.data.compareToIgnoreCase(newNode.data) > 0) {
        newNode.next = head;
        head = newNode;
      } else {

      /* Locates the node before point of insertion */
      current = head;

      while (current.next != null &&
             current.next.data.compareToIgnoreCase(newNode.data) < 0)
        current = current.next;
        newNode.next = current.next;
        current.next = newNode;
      }
    }

    /* Creates a new artist */
    Node newNode(String data) {
      Node s = new Node(data);
      return s;
    }

    /* Prints the linked list */
    void printList() {
      Node temp = head;
      File csvOutputFile = new File("../data/Artists in Alphabetical Order.csv");
      StringBuilder sb = new StringBuilder();
      sb.append("Artist");
      sb.append('\n');

      while (temp != null) {
        System.out.print(temp.data + ", ");
        sb.append(temp.data);
        sb.append('\n');
        temp = temp.next;
      }

      try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
        writer.write(sb.toString());
      } catch (FileNotFoundException error) {
        System.out.println(error);
      }
    }
}

/* The List TopStreamingArtists is composed of a series of artist names */
class Hash {
  Map<String, Integer> map = new HashMap<String, Integer>();

  // Sort hash map by values
  public void sortByValue() {
      // Create as list from elements of HashMap
      List<Map.Entry<String, Integer> > list =
        new LinkedList<Map.Entry<String, Integer> >(map.entrySet());

      // Sorts the list
      Collections.sort(list, new Comparator<Map.Entry<String, Integer> >() {
        public int compare(Map.Entry<String, Integer> o1,
                           Map.Entry<String, Integer> o2) {
          return (o2.getValue()).compareTo(o1.getValue());
        }
      });

      // Reverts data back to hash map
      HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
      for (Map.Entry<String, Integer> element : list) {
        temp.put(element.getKey(), element.getValue());
      }
      map = temp;
  }

  /* Hash map to store the frequency of element */
  public void countFrequencies(List<String> list) {
    for (String i : list) {
      Integer j = map.get(i);
      map.put(i, (j == null) ? 1 : j + 1);
    }
  }

  /* Prints the hash map */
  public void printMap() {
    List<String> artistList = new ArrayList<String>();;
    StringBuilder sb = new StringBuilder();
    sb.append("Artist");
    sb.append(',');
    sb.append("Count");
    sb.append('\n');

    TopStreamingArtists topStreamingArtists = new TopStreamingArtists();
    /* Displays the occurrence of elements in the array list */
    for (Map.Entry<String, Integer> val : map.entrySet()) {
      artistList.add(val.getKey());
      sb.append(val.getKey());
      sb.append(',');
      sb.append(val.getValue());
      sb.append('\n');
    }

    File csvOutputFile = new File("../data/Artist Frequency.csv");
    try (PrintWriter  writer= new PrintWriter(csvOutputFile)) {
      writer.write(sb.toString());
    } catch (FileNotFoundException error) {
      System.out.println(error);
    }

    /* Inserts into linked list then prints the results */
    for (String s : artistList) {
      topStreamingArtists.sortedInsert(topStreamingArtists.newNode(s));
    }
    System.out.println("List of Artists in Alphabetical Order");
    topStreamingArtists.printList();
  }
}
/* Test class */
public class Main {
  public static void main(String[] args) {
    String csvFile = "../data/regional-global-daily-latest.csv";
    BufferedReader br = null;
    String line = "";
    String cvsSplitBy = ",";
    List<String> values = new ArrayList<String>();
    try {
      br = new BufferedReader(new FileReader(csvFile));
      while ((line = br.readLine()) != null) {
        /* Uses comma as delimiter */
        String[] songInfo = line.split(cvsSplitBy);

        /* Edge case handling */
        if (songInfo[2].replace("\"", "").trim().equals("000 Hours (with Justin Bieber)")) {
          values.add("Dan + Shay");
        } else if (songInfo[2].replace("\"", "").trim().equals("next")) {
          values.add("Ariana Grande");
        } else if (songInfo[2].replace("\"", "").trim().equals("")) {
        } else if (!songInfo[2].replace("\"", "").trim().equals("Artist")) {
          values.add(songInfo[2].replace("\"", "").trim());
        }
      }

      Hash count = new Hash();
      count.countFrequencies(values);
      count.sortByValue();
      count.printMap();
    } catch (FileNotFoundException error) {
      error.printStackTrace();
    } catch (IOException error) {
      error.printStackTrace();
    } finally {
      if (br != null) {
        try {
          br.close();
        } catch (IOException error) {
          error.printStackTrace();
        }
      }
    }
  }
}
