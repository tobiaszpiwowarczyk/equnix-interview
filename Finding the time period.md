##Finding time period and number visitors algorithm

### Table of contents
1. How it works?
2. Time and space complexity.
3. Where is algorithm implemented?
4. Examples.
5. How to run application?

---

### How it works? 
1. This algorithm gets each value from repository.
2. Saves temporary time period to `tempPeriod` field with the time period value using `Visitor#getPeriod()` method
3. Let's say, that `value` is the the visitor from current loop, and `value1` is the visitor value from nested loop. 
    So algorithm iterates through repository and check if `value` is not equal to `value1`
4. Checks, that `tempPeriod` and `value1` has common period. If so, `tempCount` value is incremented
5. Next, the algorithm get common time period for `tempPeriod` and `value1`
6. If common time period doesn't have equal time (for example 10:00,10:00), 
    replace the value of `tempPeriod` with new common time period
7. After nested loop we need to check, if `tempCount` is greater than `count`. 
If so, replace the current value of `count` with value of `tempCount`. Also we need to replace value  of `period` with value of `tempPeriod`
8. After all, restart `tempPeriod` value. Just set the value to `0`

---

### Time and space complexity

Time complexity: **O(n^2)**

Space complexity: **O(n)**

---

### Where is algorithm implemented?

The algorithm is implemented in `pl.equinix.visitor.VisitorAlgorithm` class. You can find
this file in `src/main/java/pl/equinix/visitor/` directory

---

### Examples

1. Let's say, that we have this time periods in repository:
    * `10:15,14:20`
    
    So the output of this method should be `10:15 - 14:20; 1`
     
2. Let's say, that we have this times periods in repository:
    * `10:15,14:20`
    * `11:10,15:22`
    
    So the output of this method should be `11:10 - 14:20; 2`
        
3. Let's say, that we have this times periods in repository:
    * `10:15,14:20`
    * `11:10,15:22`
    * `12:00,16:00`

    So the output of this method should be `12:00 - 14:20; 3`
    
4. Let's say, that we have this times periods in repository:
    * `10:15,14:20`
    * `11:10,15:22`
    * `12:00,16:00`
    * `16:00,18:00`
    
    So the output of this method should be `12:00 - 14:20; 3`
    
---
### How to run application?
1. Make sure if you have already installed `gradle` on your computer.
2. If so, just type this command:
    ```
    gradle execute
    ```
    2.1 This command:
    * build your application to `jar` file including unit testing
    * generate Javadoc to `javadoc` direectory
    * run your application and print result in the console
2. If you don't have installed `gradle` on your computer, you can use `./gradlew` command.
3. Type below command to build application:
    ```
    ./gradlew execute
    ```
    This command additionally:
    * download `gradle` if you didn't install
    * after that all cases in point 2.1 will execute