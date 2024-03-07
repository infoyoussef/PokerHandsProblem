package constants;


import java.util.Map;

//Each card also has a value which is one of 2, 3, 4, 5, 6, 7, 8, 9, 10, jack, queen, king, ace (denoted 2, 3, 4, 5, 6, 7, 8, 9, T, J, Q, K, A).
public class Value {

     public static final Map<Character, String>  values =   Map.ofEntries(
             Map.entry('2', "2"),
             Map.entry('3', "3"),
             Map.entry('4', "4"),
             Map.entry('5', "5"),
             Map.entry('6', "6"),
             Map.entry('7', "7"),
             Map.entry('8', "8"),
             Map.entry('9', "9"),
             Map.entry('T', "10"),
             Map.entry('J', "Jack"),
             Map.entry('Q', "Queen"),
             Map.entry('K', "King"),
             Map.entry('A', "Ac")
     );
}
