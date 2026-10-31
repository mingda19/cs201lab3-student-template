import java.util.Arrays;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          String invalid = "Invalid traversals";
          if (!(traversal1.length() == traversal2.length() && traversal2.length() == traversal3.length() && traversal3.length() > 0)) {
               return invalid;
          }
          String s1 = "Traversal 1";
          String s2 = "Traversal 2";
          String s3 = "Traversal 3";
          
          int[] trav1 = Arrays.stream(traversal1.split("-"))
                               .mapToInt(Integer::parseInt)
                               .toArray();
          int[] trav2 = Arrays.stream(traversal2.split("-"))
                               .mapToInt(Integer::parseInt)
                               .toArray();
          int[] trav3 = Arrays.stream(traversal3.split("-"))
                               .mapToInt(Integer::parseInt)
                               .toArray();
          if (!(trav1.length == trav2.length && trav2.length == trav3.length)) {
               return invalid;
          }
          // Find Inorder
          boolean x1 = true, x2 = true, x3 = true;
          for (int i = 0; i < trav1.length - 1; i++) {
               if (trav1[i] > trav1[i+ 1]) {
                    x1 = false;
               } if (trav2[i] > trav2[i+ 1]) {
                    x2 = false;
               } if (trav3[i] > trav3[i+ 1]) {
                    x3 = false;
               }
          }

          if (!(x1 || x2 || x3)) {
               return invalid;
          }

          String inOrder = " - Inorder";
          String preOrder = " - Preorder";
          String postOrder = " - Postorder";
          try {
               if (x1) {
                    s1 += inOrder;
                    x2 = trueIfFirstTravesalIsPreOrder(trav2, trav3);
                    if (x2) {
                         s2 += preOrder;
                         s3 += postOrder;
                    } else {
                         s3 += preOrder;
                         s2 += postOrder;
                    }
               } else if (x2) {
                    s2 += inOrder;
                    x1 = trueIfFirstTravesalIsPreOrder(trav1, trav3);
                    if (x1) {
                         s1 += preOrder;
                         s3 += postOrder;
                    } else {
                         s3 += preOrder;
                         s1 += postOrder;
                    }
               } else if (x3) {
                    s3 += inOrder;
                    x1 = trueIfFirstTravesalIsPreOrder(trav1, trav2);
                    if (x1) {
                         s1 += preOrder;
                         s2 += postOrder;
                    } else {
                         s2 += preOrder;
                         s1 += postOrder;
                    }
               }
          } catch (RuntimeException e) {
               return invalid;
          }

          return s1 + ", " + s2 + ", " + s3;
     } 

     public static boolean trueIfFirstTravesalIsPreOrder(int[] t1, int[] t2) {
          if (t2[t2.length - 1] == t1[0]) {
               return true;
          } else if (t1[t1.length - 1] == t2[0]) {
               return false;
          } else {
               throw new RuntimeException();
          }
     }
}