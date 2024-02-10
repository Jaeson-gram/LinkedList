package com.Relearn;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {

    public static void main(String[] args) {
	// write your code here
        LinkedList<String> placesToVisit = new LinkedList<>();

        placesToVisit.add("Prague");
        placesToVisit.add("Greece");
        placesToVisit.add("Norway");
        placesToVisit.add("Brazil");
        placesToVisit.add("Bangkok");
        placesToVisit.add("Canada");



        System.out.println(placesToVisit);

        placesToVisit.addFirst("Shadow Play"); //adds to the beginning
        placesToVisit.addLast("Kenya"); //adds to the end

        System.out.println(placesToVisit);

        //Queue Methods
        placesToVisit.offerFirst("Hills"); //Adds to the beginning
        placesToVisit.offer("Forest"); //adds to the end, as normally
        placesToVisit.addLast("Leisure Park"); //adds to the end

        System.out.println(placesToVisit);

        //Stack Methods
        placesToVisit.push("Iceland"); //pushes to the top, or in this case, the start of the stack

        //So the LinkedList can be used as a List, a double ended Queue, and a Stack


        //Removing Elements
        placesToVisit.remove(6); // removes by a specific index
        placesToVisit.remove("Forest"); //removes a specific content
        System.out.println(placesToVisit);

        String tbRemoved = placesToVisit.remove(); //no arg remove(), removes the first element.
        System.out.println(tbRemoved + " was removed!");
        System.out.println(placesToVisit);

        /*
        * we also have the removeFirst() and removeLast() , and like the add methods, they remove and add to the beginning and the end
        *
        * Queue/Dequeue -> Poll:
        * */
        placesToVisit.poll(); // no args poll() removes from the beginning. pollFirst() and pollLast() exist too, to remove from first or last resp.
        System.out.println(placesToVisit);


        System.out.println();
        System.out.println("-".repeat(30));

        gettingElements(placesToVisit, 3);

        System.out.println();
        System.out.println("-".repeat(30));

//        printIncinary3(placesToVisit);
        testIterator(placesToVisit);
    }

    private static void gettingElements(LinkedList<String> list, int index){
        System.out.println("retrieving element ...");
        System.out.println(list.get(index)); //brings up an element at an index in the List.
        /*
         * There is also the getFirst() and getLast() that gets the first and the last elements, respectively.
         * */

        System.out.println("Norway is at position " + list.indexOf("Norway"));
//        System.out.println("Downtown is at position " + list.lastIndexOf("Downtown")); -> assuming the element 'Downtown' exists in more than one place, it gets the last occurrence

        //Queue retrieval method
        System.out.println("Element from element() = " + list.element()); //as in a queue, it retrieves the first element

        //Stack retrieval methods
        System.out.println("Element from peek() = " + list.peek()); //as in a stack, it peeks the last element
        System.out.println("Element from peek() = " + list.peekFirst()); //it peeks the first element
        System.out.println("Element from peek() = " + list.peekLast()); //it peeks the last element
    }

    public static void printIncinary(LinkedList<String> linkedList){
        System.out.println("Our journey starts at " + linkedList.getFirst());

        for (int i = 1; i < linkedList.size(); i++){
            System.out.println("-> from: " + linkedList.get(i - 1) + " to " + linkedList.get(i));
        }

        System.out.println("Our journey ends at " + linkedList.getLast());

        //but the index thing is not the most efficient way to access a LinkedList, in fact, if indexing will be very often, you'd probably use an ArrayList
    }

    public static void printIncinary2(LinkedList<String> linkedList){
        System.out.println("Our trip starts at " + linkedList.getFirst());

        var currentTown = linkedList.getFirst();

        for (String town : linkedList){
            System.out.println("-> from: " + currentTown + " to -> " + town);
            //the above line prints '-> from [firstTown] to -> [firstTown]' at the beginning, before we change the value of currentTown… that's an issue
            //we solve this issue by using the ListIterator<T>. see method below...
            currentTown = town;
        }

        System.out.println("Our trip ends at " + linkedList.getLast());
    }

    public static void printIncinary3(LinkedList<String> linkedList) {
        System.out.println("Our trip starts at " + linkedList.getFirst());

        var currentTown = linkedList.getFirst();

        ListIterator<String> listIterator = linkedList.listIterator(1);
        //this listIterator() method takes an int - the index where the iteration should start, so we can start at the next town,
        // already storing the first one in the currentTown variable, so that we don't get the '-> from [firstTown] to -> [firstTown]' output

        while (listIterator.hasNext()) {
            var town = listIterator.next();
            System.out.println("-> from: " + currentTown + " to -> " + town);
            currentTown = town;
        }

        System.out.println("Our trip ends at " + linkedList.getLast());

    }

    private static void testIterator(LinkedList<String> linkedList){
        var iterator = linkedList.iterator();

        while (iterator.hasNext()){
//            System.out.println(iterator.next());
            if (iterator.next().equals("USA"))
//             linkedList.remove(); -> this code will throw an Exception, because the iterator (as with the enhanced
//                                                               for loop) encourages a safe way to remove elements from a list, so we can only remove from the iterator
//                                                               object itself, or the enhanced loop's object. Safe!
                iterator.remove();
        }

        System.out.println(linkedList);
    }

    private static void testListIterator(LinkedList linkedList){
        var listIterator = linkedList.listIterator();

        while(listIterator.hasNext()){
            if (listIterator.next() == "Spain"){
                listIterator.add("Barcelona");
            }
        }

//        while (listIterator.hasNext()){
//            System.out.println(listIterator.next()); -> This code will not run as long as we're using the same listIterator instance. Because it's gotten to the end alr.
//        }

        while (listIterator.hasPrevious()){
            //iterate backwards...
            System.out.println(listIterator.previous()); // -> the ListIterator<> has the hasPrevious(), previous(), add(), and set() methods too
        }
    }
}
