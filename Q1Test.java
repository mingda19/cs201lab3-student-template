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
          for (int attempt = 0; attempt < 2; attempt++) {
               int[] pre = (attempt == 0) ? t1 : t2;
               int[] post = (attempt == 0) ? t2 : t1;
               int n = pre.length;

               // Rebuild the BST. Node i holds pre[i]; left[i] / right[i] are child indices (-1 = none).
               int[] left = new int[n], right = new int[n];
               Arrays.fill(left, -1);
               Arrays.fill(right, -1);
               for (int i = 1; i < n; i++) {
                    int cur = 0;
                    while (true) {
                         if (pre[i] < pre[cur]) {
                              if (left[cur] == -1) { left[cur] = i; break; }
                              cur = left[cur];
                         } else {
                              if (right[cur] == -1) { right[cur] = i; break; }
                              cur = right[cur];
                         }
                    }
               }

               // The rebuilt tree's preorder must visit nodes 0, 1, 2, ... in order;
               // otherwise pre is not a valid BST preorder.
               int[] stack = new int[n];
               int top = 0, k = 0;
               boolean validPre = true;
               stack[top++] = 0;
               while (top > 0) {
                    int node = stack[--top];
                    if (node != k++) { validPre = false; break; }
                    if (right[node] != -1) stack[top++] = right[node];
                    if (left[node] != -1) stack[top++] = left[node];
               }
               if (!validPre) continue;

               // Postorder = reverse of a root-right-left traversal. Fill it from the back.
               int[] rebuiltPost = new int[n];
               top = 0;
               k = n - 1;
               stack[top++] = 0;
               while (top > 0) {
                    int node = stack[--top];
                    rebuiltPost[k--] = pre[node];
                    if (left[node] != -1) stack[top++] = left[node];
                    if (right[node] != -1) stack[top++] = right[node];
               }
               if (Arrays.equals(rebuiltPost, post)) {
                    return attempt == 0;
               }
          }
          throw new RuntimeException();
     }
}