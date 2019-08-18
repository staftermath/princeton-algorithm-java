# Princeton Algorithm

## Overview
This repo contains implementation of all (hopefully) practices and examples in
Princeton's Algorithm class [Part I](https://www.coursera.org/learn/algorithms-part1/home/welcome). 
and [Part II](https://www.coursera.org/learn/algorithms-part2).
I have python version in [here](https://github.com/staftermath/princeton-algorithm).

### Percolation
Averagely how many block do we need to fill in a `n` by `m` matrix in order to 
make it percolated: there exists a path from top to bottom consists of filled 
blocks. The number is approximately 59.08%. This is an exercise of `UnionFind`.

```$java
java -cp princeton-algorithm-java-1.0-SNAPSHOT.jar Percolation 200 200 10000
# Percolation probability: 59.08 % 
# Execution time in Seconds: 18.081.
```
Some helper classes include: `Coordinate`, `MonteCarlo`, `UnionFind`.