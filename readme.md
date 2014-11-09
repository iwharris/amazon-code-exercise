###Description

This was a 90-minute exercise done over e-mail as a technical screen in the Amazon hiring process. This is my response, in the state it was in when submitted (typos and mistakes included).

This solution was accepted by the reviewer.

###Exercise

A binary search tree is a binary tree with the nodes sorted such that for each node N in the tree: 

- The left subtree contains only nodes with values less than the value in N 

- The right subtree contains only nodes with values greater than the value in N

This is a valid binary search tree.

![Binary Search Tree](https://github.com/iwharris/amazon-code-exercise/blob/master/img/bst.jpg?raw=true "Binary Search Tree")

A B-tree is a generalization of a binary search tree, where each node has *n* keys and *n+1* children and n can be different for each node. The keys are sorted the same as in a binary search tree. For each key *k* in the tree, all children to the left must have keys less than *k*, and all children to the right must have keys greater than *k*.

![B-tree](https://github.com/iwharris/amazon-code-exercise/blob/master/img/btree.jpg?raw=true "B-Tree")

**QUESTION:**

Write a method that validates whether a B-tree is correctly sorted. You do NOT need to validate whether the tree is balanced. Use the following model for a node in the B-tree.

*Java:*

    class Node { 

        List<Integer> keys; 	
        List<Node> children; 
    } 

*C: *

    struct node { 
	
        int* values; 
        struct node** children;

    };
	
	
- Please provide a single file, formatted as a Word document, plain text file, or PDF. **IMPORTANT** Other formats will not be accepted and cannot be evaluated! 

- Provide one solution written in Java or C++ (C# acceptable.) 

- Code should be production quality – clearly written, runnable, and documented. 

- Provide several examples that call your function and demonstrate that it works. 

- You may use any functions or classes from the JDK, STL, or .Net Framework. Do not include extraneous code that is not relevant to your solution. 

- Provide the average runtime and space complexity (memory usage), and worst-case runtime and space complexity for your solution, and a short explanation as to why. 

- State any assumptions you make for your solution.	