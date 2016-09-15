//$Id: QueueTest.java,v 1.1 2001/06/04 11:42:30 sanjai Exp $
import java.lang.*;
import com.adventnet.util.Queue;

 public class QueueTest
 {
      public static void main(String args[])
      {
      Queue q = new Queue();
      String t1="test1";
      String t2="test2";
      String t3="test3";
      /*  The   method public synchronized void enQ(Object obj) is the 
  basic method to add an
       *object to the Queue . Obviosly adds at the end .In case the object 
  is null , the method
       * returns silently   without doing anything
       */
 
      q.enQ((Object)t1);
      q.enQ((Object)t2);
      q.enQ((Object)t3);
      /*
     * The method public synchronized Object firstElement() gets the first 
  element.
     *returns null if empty
     * Note this method gives the reference of the first Element to u . 
  That is all
     * It does not change the internal storage of the object
     */
      System.out.println("The first element is "+q.firstElement());
      /*
       * Use  public synchronized Object elementAt() in case u wanted to 
  check what are the
       *elementsin the Queue.Will throw an ArrayIndexOutOfBound if u 
  miscalculate
       */
      System.out.println("The second element is  "+q.elementAt(1));
      System.out.println("The last element is  "+q.elementAt(2));
 
      // The method  public int size() returns the size Que
      System.out.println("The size of queue before  deleting the first element is "+q.size());
 
      /*
       * The method public synchronized Object deQFirstElement() returns 
  the first Element
       * And along with that removes it internally . I repeat this removes 
  the first element
       * and gives it to you .I can vouch for the fact that it is atleast 
  10 times faster
       * than  removeElementAt(0) of a Vector when the sizes are considerable.
       */
 
 
      System.out.println("The object  "+q.deQFirstElement()+"   is removed from the queue");
 
      System.out.println("The size of queue after deleting the first element is "+q.size());
 
      //The method  public synchronized Vector getAllElements() returns a  vector of all the objects
      // in the queue
   /*
     * If u are fed up with the queue and want to reuse it afresh , then
     * feel free to use this method .public synchronized void 
  removeAllElements()
     */
      q.removeAllElements();
      System.out.println("The size of the queue after removing all elements is "+q.size());
 
 
      }
 }


