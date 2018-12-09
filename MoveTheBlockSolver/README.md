## Problem Statement
Move the red block to the exit point in as few moves as possible.

### Model of the problem
- The box is represented with a tuple (n, m). n is the number of rows, and m is the number of columns. The box is a
 matrix
- A block is represented with a tuple ((x,y), l, D). x is lowest row of the box, and y is lowest column of the box. l is
 the length of the block. D is either S or E, to indicate direction.  
- Empty space of the box is represented with a matrix of size n x m. An element of the matrix is either empty or 
 occupied 

There are one red blocks and one or more normal block. Let red block be ((x_r, y_r), l, E), then the goal is red block
 such that y_r+l = m - 1.
```
A possible move for a block ((x,y), l, D):
    if D = S
        y can be any 0...,n-l, with no overlapping violation  
    else 
        x can be any 0...,m-l, with no overlapping violation
        

Overlapping violation of a block ((x,y), l, D):
    if D = S
        any of (x,y) for all x in x,..x+l-1 where the empty space matrix at (x,y) is occupied.
    else 
        any of (x,y) for all y in y,..y+l-1 where the empty space matrix at (x,y) is occupied.
```  

    
### Approach 1
```
def move_red_to_exit():
    if path to exit is clear
        move red to exit
        done
    else 
        choose_a_block_to_move
        move_red_to_exit()
        
def choose_a_block_to_move():
    apply heuristic to pick a block to move
    move the block to destination.
    
move_red_to_exit()
    
```
