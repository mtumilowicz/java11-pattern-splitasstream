[![Build Status](https://travis-ci.com/mtumilowicz/java11-pattern-splitasstream.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-pattern-splitasstream)

# java11-pattern-splitasstream

# preface
Since java 8 we have very handy function in `Pattern` class
to split char sequence as a stream:
```
Stream<String> splitAsStream(final CharSequence input)
```
* creates a stream from the given input sequence around 
matches of this pattern
* substrings in the stream are in the order in which they occur 
in the input
* trailing empty strings will be discarded
* if this pattern does not match any subsequence of the input then
the resulting stream has just one element - input sequence in 
the string form
* if the input sequence is mutable, it must remain constant 
during the execution of the terminal stream operation, otherwise 
the result of the terminal stream operation is undefined

# project description
Two easy examples:
1. list letters
    ```
    var pattern = Pattern.compile("-");

    var top3Letters = pattern.splitAsStream("a-b-c-d-e-f-g")
            .sorted()
            .limit(3)
            .collect(Collectors.toList());

    assertThat(top3Letters, is(List.of("a", "b", "c")));    
    ```
1. count the words occurrences
    ```
    var pattern = Pattern.compile(" ");
    
    var string = "go been go hi there go hi";
    
    var wordsMap = pattern.splitAsStream(string)
            .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    
    assertThat(wordsMap.size(), is(4));
    assertThat(wordsMap.get("go"), is(3L));
    assertThat(wordsMap.get("been"), is(1L));
    assertThat(wordsMap.get("hi"), is(2L));
    assertThat(wordsMap.get("there"), is(1L));
    ```