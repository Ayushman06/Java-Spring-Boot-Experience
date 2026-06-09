package com.practice;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.*;
import java.util.stream.*;

/**
 * ============================================================
 *  JAVA 8 - COMPLETE PRACTICE REFERENCE
 *  Covers: Streams, Lambdas, Functional Interfaces, Optional,
 *          Method References, Collectors, Concurrency basics
 *  Order: Beginner → Intermediate → Advanced
 * ============================================================
 */
public class Java8_Complete_Practice {

    // =========================================================
    // SECTION 1: LAMBDA EXPRESSIONS
    // =========================================================

    // 1.1 Runnable using lambda (no return value, no args)
    static void lambdaRunnable() {
        Runnable r = () -> System.out.println(Thread.currentThread().getName() + " running via Runnable lambda");
        new Thread(r).start();
    }

    // 1.2 Callable using lambda (returns a value)
    static void lambdaCallable() throws ExecutionException, InterruptedException {
        Callable<String> callable = () -> Thread.currentThread().getName() + " running via Callable lambda";
        ExecutorService executor = Executors.newFixedThreadPool(1);
        System.out.println(executor.submit(callable).get());
        executor.shutdown();
    }

    // 1.3 Comparator using lambda (sorting a list)
    static void lambdaComparator() {
        List<String> names = Arrays.asList("John", "Alice", "Bob");
        names.sort((s1, s2) -> s1.compareTo(s2));
        System.out.println("Sorted list: " + names);
    }

    // =========================================================
    // SECTION 2: FUNCTIONAL INTERFACES
    // =========================================================

    // 2.1 Predicate<T> — takes T, returns boolean
    static void predicateDemo() {
        Predicate<Integer> isEven = n -> n % 2 == 0;
        System.out.println("Is 10 even: " + isEven.test(10));

        Predicate<String> startsWithDigit = s -> Character.isDigit(s.charAt(0));
        System.out.println("Starts with digit: " + startsWithDigit.test("1springboot"));
    }

    // 2.2 Function<T, R> — takes T, returns R
    static void functionDemo() {
        Function<String, Integer> strLength = s -> s.length();
        System.out.println("Length: " + strLength.apply("springboot"));

        Function<Integer, Integer> square = n -> n * n;
        System.out.println("Square of 7: " + square.apply(7));

        Function<String, Boolean> startsWithOne = s -> s.startsWith("1");
        System.out.println("Starts with 1: " + startsWithOne.apply("1springboot"));
    }

    // 2.3 BiFunction<T, U, R> — takes two inputs, returns R
    static void biFunctionDemo() {
        BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
        System.out.println("Sum: " + add.apply(7, 6));
    }

    // 2.4 Consumer<T> — takes T, returns nothing (used in forEach)
    static void consumerDemo() {
        List<String> words = Arrays.asList("Java", "Python", "Kotlin");
        words.forEach(w -> System.out.print(w + " "));
        System.out.println();
        // Method reference equivalent:
        words.forEach(System.out::println);
    }

    // =========================================================
    // SECTION 3: METHOD REFERENCES
    // =========================================================

    // 3.1 Static method reference: ClassName::staticMethod
    // 3.2 Instance method reference on type: ClassName::instanceMethod
    // 3.3 Instance method reference on object: instance::method
    static void methodReferenceDemo() {
        List<String> words = Arrays.asList("Java", "Python", "Kotlin");

        // Static method reference
        words.stream().map(String::toUpperCase).forEach(System.out::println);

        // Instance method on each element
        words.stream().map(String::length).forEach(System.out::println);

        // Constructor reference
        // Supplier<List<String>> listFactory = ArrayList::new;
    }

    // =========================================================
    // SECTION 4: OPTIONAL
    // =========================================================

    // 4.1 Optional.of, Optional.ofNullable, isPresent, ifPresent
    static void optionalDemo() {
        Optional<String> present = Optional.of("Hello");
        Optional<String> empty = Optional.ofNullable(null);

        present.ifPresent(System.out::println);
        System.out.println("Empty optional present: " + empty.isPresent());
        System.out.println("OrElse fallback: " + empty.orElse("Default Value"));
    }

    // =========================================================
    // SECTION 5: STREAMS — BASICS
    // =========================================================

    // 5.1 filter() — keep elements matching a condition
    static void streamFilter() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4);
        System.out.print("Even numbers: ");
        numbers.stream().filter(n -> n % 2 == 0).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 5.2 map() — transform each element
    static void streamMap() {
        List<String> words = Arrays.asList("Java", "Python", "Kotlin");
        words.stream().map(String::toUpperCase).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 5.3 sorted() — natural and reverse order
    static void streamSorted() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9);
        numbers.stream().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();
        numbers.stream().sorted(Comparator.reverseOrder()).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 5.4 distinct() — remove duplicates
    static void streamDistinct() {
        List<String> words = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        words.stream().distinct().forEach(System.out::println);
    }

    // 5.5 limit() and skip() — pagination-like ops
    static void streamLimitSkip() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.print("First 3: ");
        numbers.stream().limit(3).forEach(n -> System.out.print(n + " "));
        System.out.println();

        System.out.print("Skip first 3: ");
        numbers.stream().skip(3).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 5.6 count() — terminal op that counts elements
    static void streamCount() {
        List<String> words = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        System.out.println("Word count: " + words.stream().count());
    }

    // 5.7 anyMatch / allMatch / noneMatch
    static void streamMatchOps() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Any even: " + numbers.stream().anyMatch(n -> n % 2 == 0));
        System.out.println("All positive: " + numbers.stream().allMatch(n -> n > 0));
        System.out.println("None negative: " + numbers.stream().noneMatch(n -> n < 0));
    }

    // =========================================================
    // SECTION 6: STREAMS — INTERMEDIATE OPERATIONS
    // =========================================================

    // 6.1 max() and min() using Comparator
    static void streamMaxMin() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4);
        numbers.stream().max(Comparator.naturalOrder()).ifPresent(n -> System.out.println("Max: " + n));
        numbers.stream().min(Comparator.naturalOrder()).ifPresent(n -> System.out.println("Min: " + n));

        List<String> words = Arrays.asList("Java", "Python", "C#");
        words.stream().max(Comparator.comparing(String::length)).ifPresent(w -> System.out.println("Longest: " + w));
    }

    // 6.2 Second smallest and second largest using sorted + skip
    static void streamNthElements() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4);

        Optional<Integer> secondSmallest = numbers.stream().sorted().skip(1).findFirst();
        System.out.println("2nd smallest: " + secondSmallest);

        Optional<Integer> secondLargest = numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(1).findFirst();
        System.out.println("2nd largest: " + secondLargest.get());

        // Nth highest generalized
        int n = 2;
        numbers.stream().distinct().sorted(Comparator.reverseOrder()).skip(n - 1).findFirst()
                .ifPresent(num -> System.out.println(n + " highest: " + num));
    }

    // 6.3 3rd longest word using sorted + distinct + skip
    static void streamThirdLongest() {
        List<String> words = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");
        words.stream().sorted(Comparator.comparing(String::length).reversed()).distinct().skip(2).findFirst()
                .ifPresent(w -> System.out.println("3rd longest: " + w));
    }

    // 6.4 reduce() — combine all elements into one
    static void streamReduce() {
        List<Integer> numbers = Arrays.asList(2, 5, 8, 1, 9, 3, 7, 4, 6, 10);

        // Sum using reduce
        System.out.println("Sum: " + numbers.stream().reduce((a, b) -> a + b));

        // Sum with identity (returns int, not Optional)
        int total = Stream.of(1, 2, 3).reduce(0, Integer::sum);
        System.out.println("Total: " + total);

        // Product of first two elements
        System.out.println("Product of first 2: " + numbers.stream().limit(2).reduce((a, b) -> a * b));

        // Sum using mapToInt
        numbers.stream().mapToInt(Integer::intValue).sum();
    }

    // 6.5 flatMap() — flatten nested lists
    static void streamFlatMap() {
        List<List<Integer>> nested = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9));
        nested.stream().flatMap(List::stream).forEach(System.out::println);
    }

    // 6.6 summaryStatistics() — count, sum, min, max, avg in one shot
    static void streamSummaryStatistics() {
        List<Integer> numbers = Arrays.asList(10, 12, 14, 16, 22, 24, 26, 2, 3, 5);
        System.out.println("Stats: " + numbers.stream().mapToInt(Integer::intValue).summaryStatistics());
    }

    // 6.7 average using Collectors.averagingDouble
    static void streamAverage() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4, 13, 11, 16, 14, 19);
        System.out.println("Average: " + numbers.stream().collect(Collectors.averagingDouble(n -> n)));
    }

    // 6.8 concat two streams (List and int[])
    static void streamConcat() {
        // Concat two integer lists
        List<Integer> list1 = Arrays.asList(10, 12, 14, 16);
        List<Integer> list2 = Arrays.asList(22, 24, 26, 10);
        Stream.concat(list1.stream(), list2.stream()).distinct().sorted().forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Concat two int arrays
        int[] arr1 = {5, 2, 8, 1, 7};
        int[] arr2 = {9, 4, 6, 3, 7};
        int[] merged = IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)).distinct().sorted().toArray();
        System.out.println("Merged array: " + Arrays.toString(merged));
    }

    // 6.9 Lazy evaluation — stream does minimal work
    static void streamLazyEvaluation() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("Creating stream...");
        List<Integer> result = numbers.stream()
                .filter(n -> { System.out.println("Filtering: " + n); return n % 2 == 0; })
                .map(n -> { System.out.println("Mapping: " + n); return n * 2; })
                .limit(2)
                .collect(Collectors.toList());
        System.out.println("Result: " + result);
        // Key insight: only processes until limit(2) is satisfied — stops early
    }

    // =========================================================
    // SECTION 7: STREAMS — COLLECTORS
    // =========================================================

    // 7.1 partitioningBy() — split into true/false groups
    static void collectorPartitioning() {
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4);
        Map<Boolean, List<Integer>> evenOdd = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("Even: " + evenOdd.get(true));
        System.out.println("Odd: " + evenOdd.get(false));
    }

    // 7.2 groupingBy() — group elements by a key
    static void collectorGroupingBy() {
        List<String> words = Arrays.asList("Java", "Python", "C#", "Java", "Kotlin", "Python");

        // Group by word frequency
        Map<String, Long> wordFreq = words.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word frequency: " + wordFreq);

        // Group by string length
        List<String> names = Arrays.asList("Himanshu", "Devraj12", "rajeesh", "pavan", "sachine");
        System.out.println("By length: " + names.stream().collect(Collectors.groupingBy(s -> s.length())));

        // Group Naruto characters by name length
        List<String> chars = Arrays.asList("Naruto", "itachi", "Sasuke", "Madara", "Kakashi", "Obito");
        System.out.println("By length: " + chars.stream().collect(Collectors.groupingBy(w -> w.length())));
    }

    // 7.3 joining() — concatenate strings
    static void collectorJoining() {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry");
        System.out.println(words.stream().collect(Collectors.joining(", ", "[", "]")));

        List<String> upperWords = Arrays.asList("Java", "Python", "C#");
        System.out.println(upperWords.stream().map(String::toUpperCase).collect(Collectors.joining(", ")));
    }

    // 7.4 toSet() and collecting to different collection types
    static void collectorToCollection() {
        List<Integer> numbers = Arrays.asList(11, 22, 33, 11, 55, 33, 77, 22);
        Set<Integer> uniqueNums = numbers.stream().collect(Collectors.toSet());
        System.out.println("Unique: " + uniqueNums);
    }

    // 7.5 Sorting a Map by key after groupingBy
    static void collectorSortedByKey() {
        String s = "apple banana mango orange apple banana apple";
        Arrays.stream(s.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> System.out.print(entry + ", "));
        System.out.println();
    }

    // =========================================================
    // SECTION 8: INTSTREAM AND NUMERIC STREAMS
    // =========================================================

    // 8.1 IntStream.range and rangeClosed
    static void intStreamRange() {
        System.out.println("Sum 1-10: " + IntStream.rangeClosed(1, 10).sum());
        IntStream.rangeClosed(1, 10).map(n -> n * 2).forEach(n -> System.out.print(n + " "));
        System.out.println();
        System.out.println("Sum 1-10 (range): " + IntStream.range(1, 11).sum());
    }

    // 8.2 Sum and average of an int array
    static void intArrayOps() {
        int[] arr = {5, 2, 8, 1, 7};
        System.out.println("Sum: " + Arrays.stream(arr).sum());
        System.out.println("Average: " + Arrays.stream(arr).average());
    }

    // 8.3 Get last element of an array using skip
    static void arrayLastElement() {
        int[] arr = {10, 20, 30, 40, 55};
        Arrays.stream(arr).skip(arr.length - 1).findFirst()
                .ifPresent(n -> System.out.println("Last element: " + n));
    }

    // 8.4 Sum of first two elements using limit
    static void arraySumFirstTwo() {
        int[] arr = {10, 20, 30, 40, 55};
        System.out.println("Sum of first two: " + Arrays.stream(arr).limit(2).sum());
    }

    // 8.5 Sum of digits of a number
    static void sumOfDigits() {
        int number = 123456789;
        System.out.println("Sum of digits: " +
                String.valueOf(number).chars().map(Character::getNumericValue).sum());
    }

    // =========================================================
    // SECTION 9: STRING OPERATIONS WITH STREAMS
    // =========================================================

    // 9.1 Palindrome check
    static void palindromeCheck() {
        String s = "racecar";
        System.out.println("Palindrome: " + s.equals(new StringBuilder(s).reverse().toString()));
    }

    // 9.2 Anagram check
    static void anagramCheck() {
        String word1 = "silent";
        String word2 = "listen";
        boolean isAnagram = Arrays.equals(word1.chars().sorted().toArray(), word2.chars().sorted().toArray());
        System.out.println("Anagram: " + isAnagram);
    }

    // 9.3 Character frequency in a string (excluding spaces)
    static void charFrequency() {
        String input = "Java Concept Of The Day, Java";
        Map<Character, Long> freq = input.chars().mapToObj(c -> (char) c)
                .filter(c -> !Character.isWhitespace(c))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Char frequency: " + freq);
    }

    // 9.4 Word frequency in a sentence
    static void wordFrequency() {
        String sentence = "things are soo expensive these days";
        Map<String, Long> freq = Arrays.stream(sentence.split(" "))
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println("Word frequency: " + freq);
    }

    // 9.5 First non-repeated character (order preserved with LinkedHashMap)
    static void firstNonRepeatedChar() {
        String word = "swiss";
        word.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(ch -> ch, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(c -> System.out.println("First non-repeated: " + c));
    }

    // 9.6 First repeated character
    static void firstRepeatedChar() {
        String word = "swiss";
        word.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(ch -> ch, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .findFirst()
                .ifPresent(c -> System.out.println("First repeated: " + c));

        // Alternative using HashSet add() trick
        Set<Character> seen = new HashSet<>();
        String word2 = "racecar";
        Optional<Character> firstRepeated = word2.chars().mapToObj(c -> (char) c)
                .filter(ch -> !seen.add(ch))
                .findFirst();
        System.out.println("First repeated (set): " + firstRepeated);
    }

    // 9.7 All non-repeated characters using indexOf vs lastIndexOf
    static void allNonRepeatedChars() {
        String word = "swiss";
        word.chars().mapToObj(c -> (char) c)
                .filter(c -> word.indexOf(c) == word.lastIndexOf(c))
                .forEach(c -> System.out.print("Non-repeated: " + c + " "));
        System.out.println();
    }

    // 9.8 Count vowels in each word of a sentence
    static void vowelCountPerWord() {
        String sentence = "things are soo expensive these days";
        Stream.of(sentence.split(" ")).forEach(word -> {
            long count = word.chars().filter(ch -> "aeiouAEIOU".indexOf(ch) != -1).count();
            System.out.println(word + " -> " + count + " vowels");
        });
    }

    // 9.9 Extract only vowels from a word
    static void extractVowels() {
        String word = "LapToP";
        word.chars().mapToObj(c -> (char) c)
                .filter(ch -> "aeiouAEIOU".contains(String.valueOf(ch)))
                .forEach(c -> System.out.print(c + " "));
        System.out.println();
    }

    // 9.10 Toggle case of each character
    static void toggleCase() {
        String word = "LapToP";
        String toggled = word.chars().mapToObj(c -> (char) c)
                .map(c -> Character.isUpperCase(c) ? Character.toLowerCase(c) : Character.toUpperCase(c))
                .map(String::valueOf)
                .collect(Collectors.joining());
        System.out.println("Toggled: " + toggled);
    }

    // 9.11 Reverse each word in a sentence
    static void reverseEachWord() {
        String sentence = "Java Concept Of The Day";
        Arrays.stream(sentence.split(" "))
                .map(w -> new StringBuilder(w).reverse())
                .forEach(w -> System.out.print(w + " "));
        System.out.println();
    }

    // 9.12 Count total characters (including spaces) in a string
    static void totalCharCount() {
        String sentence = "things are soo expensive these days";
        System.out.println("Total chars: " + sentence.chars().count());
    }

    // 9.13 Normalize whitespace and lowercase
    static void normalizeString() {
        String s = "  Hello,  World!  ";
        System.out.println(s.replaceAll("\\s+", " ").toLowerCase().trim());
    }

    // 9.14 Most frequent character in a string
    static void mostFrequentChar() {
        String word = "Kakashi";
        word.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(ch -> ch, Collectors.counting()))
                .entrySet().stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .ifPresent(e -> System.out.println("Most frequent: " + e));
    }

    // =========================================================
    // SECTION 10: LIST AND COLLECTION OPERATIONS
    // =========================================================

    // 10.1 Find duplicate elements in a list using HashSet
    static void findDuplicates() {
        List<Integer> numbers = Arrays.asList(2, 5, 8, 1, 9, 3, 7, 4, 6, 10, 55, 2, 5, 8, 9);
        Set<Integer> seen = new HashSet<>();
        numbers.stream().filter(n -> !seen.add(n))
                .forEach(n -> System.out.println("Duplicate: " + n));
    }

    // 10.2 Find duplicate words in a sentence
    static void findDuplicateWords() {
        String sentence = "Java Concept Of The Day, Java";
        Set<String> seenWords = new HashSet<>();
        Set<String> repeated = Arrays.stream(sentence.split(" "))
                .filter(w -> !seenWords.add(w))
                .collect(Collectors.toSet());
        System.out.println("Repeated words: " + repeated);
    }

    // 10.3 Check if a list contains a duplicate using anyMatch
    static void hasDuplicateCheck() {
        int[] arr = {1, 2, 3, 1};
        Set<Integer> s = new HashSet<>();
        System.out.println("Has duplicate: " + Arrays.stream(arr).anyMatch(a -> !s.add(a)));
    }

    // 10.4 Find common elements between two lists
    static void commonElements() {
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(3, 4, 5, 6, 7);
        list1.stream().filter(list2::contains).forEach(n -> System.out.println("Common: " + n));

        // Using numbers::contains as method reference (filter by another list)
        List<Integer> numbers = Arrays.asList(7, 2, 10, 0, 5, 9, 3, 1, 6, 8, 4);
        List<Integer> numbers2 = Arrays.asList(17, 2, 10, 0, 15, 9, 13, 1, 16, 8, 4);
        numbers.stream().filter(numbers2::contains).forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 10.5 Sort words by length
    static void sortByLength() {
        List<String> words = Arrays.asList("Java", "Python", "C#", "HTML", "Kotlin", "C++", "COBOL", "C");
        words.stream().sorted(Comparator.comparing(String::length).reversed()).forEach(System.out::println);
    }

    // 10.6 Filter words starting with a digit
    static void filterWordsStartingWithDigit() {
        List<String> words = Arrays.asList("Java", "1Python", "C#", "2Java", "Kotlin", "3Python");
        words.stream().filter(w -> Character.isDigit(w.charAt(0))).forEach(w -> System.out.print(w + " "));
        System.out.println();

        // Alternative: using startsWith
        words.stream().filter(w -> w.startsWith("1")).forEach(w -> System.out.print(w + " "));
        System.out.println();
    }

    // 10.7 Count words starting with 'a' or 'b'
    static void countWordsStartingWithAorB() {
        List<String> words = Arrays.asList("apple", "banana", "apricot", "cherry", "avocado");
        System.out.println("Count (a or b): " + words.stream().filter(w -> w.startsWith("a") || w.startsWith("b")).count());
    }

    // 10.8 Square of distinct even numbers
    static void squareDistinctEvens() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 4, 5, 6, 7, 7, 8, 9, 9, 10);
        numbers.stream().distinct().filter(n -> n % 2 == 0).map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 10.9 Square of even numbers and print
    static void squareOfEvens() {
        List<Integer> numbers = Arrays.asList(2, 5, 8, 1, 9, 3, 7, 4, 6, 10);
        numbers.stream().filter(n -> n % 2 == 0).map(n -> n * n)
                .forEach(n -> System.out.print(n + " "));
        System.out.println();
    }

    // 10.10 Filter numbers starting with "1" (as string conversion trick)
    static void filterNumbersStartingWithOne() {
        List<Integer> numbers = Arrays.asList(10, 12, 14, 16, 22, 24, 26, 2, 3, 5);
        numbers.stream().filter(n -> String.valueOf(n).startsWith("1"))
                .forEach(n -> System.out.print(n + " "));
        System.out.println();

        // Alternative with map to string first
        numbers.stream().map(n -> n + "").filter(s -> s.startsWith("1"))
                .forEach(s -> System.out.print("Starts with 1: " + s + ", "));
        System.out.println();
    }

    // 10.11 removeIf() — mutate a list by removing matching elements
    static void removeIfDemo() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        numbers.removeIf(n -> n % 2 == 0);
        System.out.println("After removeIf (evens): " + numbers);
    }

    // 10.12 Filter blank/empty strings
    static void filterBlankStrings() {
        List<String> words = Arrays.asList("Naruto", "", "itachi", " ", "Sasuke");
        words.stream().filter(s -> !s.isBlank()).forEach(System.out::println);
    }

    // 10.13 StringJoiner — manual string building
    static void stringJoinerDemo() {
        StringJoiner joiner = new StringJoiner(", ");
        joiner.add("Saket");
        joiner.add("John");
        joiner.add("Franklin");
        System.out.println("StringJoiner: " + joiner);
    }

    // =========================================================
    // SECTION 11: PRIME NUMBER CHECK WITH INTSTREAM
    // =========================================================

    // 11.1 isPrime helper using IntStream
    static boolean isPrime(int num) {
        if (num < 2) return false;
        return IntStream.range(2, num).noneMatch(n -> num % n == 0);
    }

    // 11.2 Print all primes in a range
    static void printPrimesInRange() {
        IntStream.rangeClosed(1, 20).filter(Java8_Complete_Practice::isPrime).forEach(System.out::println);
    }

    // =========================================================
    // SECTION 12: TYPE CASTING AND PRIMITIVE QUIRKS
    // =========================================================

    // 12.1 Integer.parseInt
    static void parseIntDemo() {
        int parsed = Integer.parseInt("123");
        System.out.println("Parsed: " + parsed);
    }

    // 12.2 Narrowing cast — long to byte (overflow behavior)
    static void narrowingCast() {
        long bigVal = 130L;
        byte b = (byte) bigVal; // 130 - 256 = -126 (overflow)
        System.out.println("Narrowing cast (130L to byte): " + b);
    }

    // 12.3 char arithmetic
    static void charArithmetic() {
        char ch = 'A';
        System.out.println(ch + 1 + ", " + 1 + ch); // 66, 1A — left to right evaluation
    }

    // 12.4 Pre vs post increment
    static void incrementDemo() {
        int x = 5;
        System.out.println("x++: " + x++); // prints 5, then x becomes 6
        System.out.println("++x: " + ++x); // x becomes 7, then prints 7
    }

    // =========================================================
    // SECTION 13: LIST CREATION PATTERNS (MUTABLE VS IMMUTABLE)
    // =========================================================

    // 13.1 Mutable vs immutable list creation
    static void listCreationPatterns() {
        List<String> ls = Arrays.asList("alice", "bob");          // fixed size, no add/remove
        List<String> ls1 = List.of("alice", "bob");               // fully immutable (Java 9+)
        List<String> ls2 = new ArrayList<>(Arrays.asList("alice", "bob")); // mutable

        List<String> ls3 = new ArrayList<>(Arrays.asList("john"));
        ls2.addAll(ls3);
        ls2.add("doe");
        ls2.remove("alice");
        System.out.println("Mutable list: " + ls2);
    }

    // =========================================================
    // SECTION 14: THREADS WITH LAMBDAS (CONCURRENCY INTRO)
    // =========================================================

    // 14.1 Thread using lambda
    static void threadWithLambda() {
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName()));
        thread.start();
    }

    // =========================================================
    //  MAIN — run all sections in order
    // =========================================================
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        sep("SECTION 1: LAMBDA EXPRESSIONS");
        lambdaRunnable();
        lambdaCallable();
        lambdaComparator();

        sep("SECTION 2: FUNCTIONAL INTERFACES");
        predicateDemo();
        functionDemo();
        biFunctionDemo();
        consumerDemo();

        sep("SECTION 3: METHOD REFERENCES");
        methodReferenceDemo();

        sep("SECTION 4: OPTIONAL");
        optionalDemo();

        sep("SECTION 5: STREAMS — BASICS");
        streamFilter();
        streamMap();
        streamSorted();
        streamDistinct();
        streamLimitSkip();
        streamCount();
        streamMatchOps();

        sep("SECTION 6: STREAMS — INTERMEDIATE");
        streamMaxMin();
        streamNthElements();
        streamThirdLongest();
        streamReduce();
        streamFlatMap();
        streamSummaryStatistics();
        streamAverage();
        streamConcat();
        streamLazyEvaluation();

        sep("SECTION 7: COLLECTORS");
        collectorPartitioning();
        collectorGroupingBy();
        collectorJoining();
        collectorToCollection();
        collectorSortedByKey();

        sep("SECTION 8: INTSTREAM AND NUMERIC STREAMS");
        intStreamRange();
        intArrayOps();
        arrayLastElement();
        arraySumFirstTwo();
        sumOfDigits();

        sep("SECTION 9: STRING OPERATIONS");
        palindromeCheck();
        anagramCheck();
        charFrequency();
        wordFrequency();
        firstNonRepeatedChar();
        firstRepeatedChar();
        allNonRepeatedChars();
        vowelCountPerWord();
        extractVowels();
        toggleCase();
        reverseEachWord();
        totalCharCount();
        normalizeString();
        mostFrequentChar();

        sep("SECTION 10: LIST AND COLLECTION OPERATIONS");
        findDuplicates();
        findDuplicateWords();
        hasDuplicateCheck();
        commonElements();
        sortByLength();
        filterWordsStartingWithDigit();
        countWordsStartingWithAorB();
        squareDistinctEvens();
        squareOfEvens();
        filterNumbersStartingWithOne();
        removeIfDemo();
        filterBlankStrings();
        stringJoinerDemo();

        sep("SECTION 11: PRIME NUMBERS");
        printPrimesInRange();

        sep("SECTION 12: TYPE CASTING & PRIMITIVE QUIRKS");
        parseIntDemo();
        narrowingCast();
        charArithmetic();
        incrementDemo();

        sep("SECTION 13: LIST CREATION PATTERNS");
        listCreationPatterns();

        sep("SECTION 14: THREADS WITH LAMBDAS");
        threadWithLambda();
    }

    // Utility: section separator for clean console output
    private static void sep(String title) {
        System.out.println("\n" + "=".repeat(60));
        System.out.println("  " + title);
        System.out.println("=".repeat(60));
    }
}

/*
 * ============================================================
 * SELF-CHECK: JAVA 8 CONCEPTS COVERAGE SUMMARY
 * ============================================================
 *
 * ✅ Lambda Expressions          — Runnable, Callable, Comparator
 * ✅ Functional Interfaces       — Predicate, Function, BiFunction, Consumer
 * ✅ Method References           — Static, Instance, Constructor
 * ✅ Optional                    — of, ofNullable, isPresent, ifPresent, orElse
 * ✅ Stream basics               — filter, map, sorted, distinct, limit, skip, count
 * ✅ Stream terminal ops         — forEach, collect, reduce, findFirst, min, max, matchOps
 * ✅ Stream advanced             — flatMap, summaryStatistics, average, lazy evaluation
 * ✅ Collectors                  — partitioningBy, groupingBy, joining, toSet, counting
 * ✅ IntStream / numeric streams — range, rangeClosed, sum, average, array ops
 * ✅ String stream problems      — palindrome, anagram, char freq, non-repeated, toggle
 * ✅ List/Collection operations  — duplicates, common elements, removeIf, sort by length
 * ✅ Type casting quirks         — parseInt, narrowing, char arithmetic, increment ops
 * ✅ Threads with lambdas        — Runnable, Callable, ExecutorService basics
 * ✅ Prime check via noneMatch   — IntStream.range-based isPrime
 *
 * GAPS intentionally excluded (out of Java 8 scope):
 * — Streams with custom objects/POJO (Employee, Product) — add your own
 * — CompletableFuture             — Java 8 async programming (next level)
 * — Default & static interface methods — add if needed
 * — Date/Time API (java.time)     — LocalDate, LocalDateTime, DateTimeFormatter
 * ============================================================
 */
