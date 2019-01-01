## Problem Statement
Move the red block to the exit point in as few moves as possible.

### Model of the problem
- The box is represented with a tuple (n, m). n is the number of rows, and m is the number of columns. The box is a
 matrix.
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

Let's look at an example, given a game that looks like this
```
________
|300200|
|300204|
|300114
|556664|
|007000|
|007888|
--------
```
the matrix represents a box. 0 represents an empty space, and a positive number represents a space occupied by a block.
 Given a positive number, locations of the positive number are locations occupied by a block with id = the number. 
 The block with id 1 is the red block. The goal is move the red block to the exit (right most location). following are 
 game elements:
- The box is a 6 by 6 matrix.
- The red block with id=1 is ((2,3),2,E).
- The block with id=2 is ((0,3),2,S)
- The block with id=3 is ((0,0),3,S)
- The block with id=4 is ((1,5),3,S)
- The block with id=5 is ((3,0),2,E)
- The block with id=6 is ((3,2),3,E)
- The block with id=7 is ((4,2),2,S)
- The block with id=8 is ((5,3),3,E)
- The exit is 2

### Approach One: Breadth First
```
def df_find_path():
    initialize a queue Q
    initialize a visited set V
    add the path consisting of only the current state to Q
    while queue is not empty
        path P = deque the queue Q
        for each possible next state A:
            if state A not in visited set V:
                append the state A to path P
                if in the state A, the red block is at exit point:
                    the path P is the solution
                    done
                add path P to queue Q
            add state A to visited set V
```
We need a way to encode the game into states. The criteria for the encode is following:<br/> 
let A be game when normal blocks 
are such and such, red block is such, and empty space is such. let B be game when normal blocks are such and such, red
block is such, and empty space is such. A = B iif encode(A) = encode(B), in other words, encode is a bijection function.


### Approach Two: Deep First Search
To find minimum steps to solve this problem, we ask the question: can we solve it under k steps. If this question 
can be answer. Then we can start k=1, test if it is solvable. If it is not solvable, then we increment k by 1. Repeat 
until we find a k that can solve the game. 

So the problem becomes, is it solvable under k steps?

We don't know steps to solve it if any, but we do know a solvable sequence of steps has to start from some action out
of all possible moves. So we try them all, and reduce the problem to can we solve it under k - 1 steps given the state
after a action has been apply. The sequence of states changed by action can be cyclic, so during the exploration of 
states, we keep a list of ancestor, to make sure we don't explore it again.  

```
dfs(prevStates, curState, level, maxLevel, actionsSofar, result):
    if (level > maxLevel)
        return false
     
    if curState is end game and size(actionSofar) < result:
        set result to be actionSofar
        return true
        
    if curState in prevState:
        return false
        
    add curState to prevStates
    for all possible action given curState:
        nextState = curState applied action
        add action to actionsSofar 
        if dfs(prevState, nextState, leve+1, maxLevle, actionSofar, result)
            return true
        remove action from actionsSofar
    remove curState from prevStates
    return false
``` 


### Approach Two: Deep First Search With Max Possible Step 
There is a minimal sequence of actions that gives us a wining game. The sequence has to start with some actions, we 
pick one action, then recursively picking actions after it. The space can be quite big. To speed up, we can set a 
maximum steps k that we can have on sequence fo actions.

```
dfs(prevStates, curState, level, maxLevel, actionsSofar, result):
    if (level > maxLevel)
        return 
     
    if curState is end game and size(actionSofar) < result:
        set result to be actionSofar
        
    if curState in prevState:
        return
        
    add curState to prevStates
    for all possible action given curState:
        nextState = curState applied action
        add action to actionsSofar 
        dfs(prevState, nextState, leve+1, maxLevle, actionSofar, result)
        remove action from actionsSofar
    remove curState from prevStates
    
dfs([], curState, 0, k, [], [])   
```


