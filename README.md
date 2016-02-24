To compile:
cd src
javac *.java
java Driver puzzle<1/2>.txt <true/false> <euclidean/manhattan>
first argument is puzzle name
second argument is debug information (false so it doesn't spit out a ton of stuff.
third argument is the heuristic. Two are implemented, one for bonus.
The solution is saved to puzzle<1/2/3/4/5>_<euclidean/manhattan>_solution.txt