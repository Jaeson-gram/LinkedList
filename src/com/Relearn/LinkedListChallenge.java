package com.Relearn;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

record Place(String name, int distance){

    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}

public class LinkedListChallenge {

    public static void main(String[] args) {

        LinkedList<Place> placesToVisit = new LinkedList<>();
        addPlace(placesToVisit, new Place("Melbourne", 877));
        addPlace(placesToVisit, new Place("Brisbane", 917));
        addPlace(placesToVisit, new Place("Adelaide", 1374));
        addPlace(placesToVisit, new Place("Alice Springs", 2771));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Perth", 3923));
        addPlace(placesToVisit, new Place("Darwin", 3972));

        placesToVisit.addFirst(new Place("Sydney", 0));


        System.out.println();

        ListIterator listIterator = placesToVisit.listIterator();
        Scanner scanner = new Scanner(System.in);

        printInstructions();
        boolean breaker = false;
        boolean forward = true;

        while (!breaker) {
            if (!listIterator.hasPrevious()){
                System.out.println("Originating: " + listIterator.next());
                forward = true;
            }
            if (!listIterator.hasNext()){
                System.out.println("Final: " + listIterator.previous());
                forward = false;
            }

            System.out.print("Enter your value: ");
            var response = scanner.nextLine();

            switch (response.toLowerCase()) {
                case "f", "forward" -> {
                    // whenever we're reversing direction, so that it doesn't print the same place twice
                    if (!forward){      //if we're on a backward move...
                        forward = true;
                        if (listIterator.hasNext()){
                            listIterator.next();     //Adjust the direction forward... given the position of the iterator's cursor
                        }
                    }
                    if (listIterator.hasNext()) {
                        System.out.println("going on -> " + listIterator.next());
                    }
                }

                case "b", "backward" -> {
                    // whenever we're reversing direction, so that it doesn't print the same place twice
                    if (forward){      //if we're on a forward move...
                        forward = false;
                        if (listIterator.hasPrevious()){
                            listIterator.previous();     //Adjust the direction backwards... given the position of the iterator's cursor
                        }
                    }
                    if (listIterator.hasPrevious()) {
                        System.out.println(listIterator.previous() + " <- going backwards");
                    }
                }

                case "m", "menu" -> printInstructions();

                case "l", "list" -> System.out.println(placesToVisit);

                default -> breaker = true;
            }
        }

    }

    //Our own method to add a new place, so we can reject duplicates...
    private static void addPlace(LinkedList<Place> list, Place place){
        if (list.contains(place)){
            System.out.printf("duplicate of '%s' found", place);
            return;
        }

        // to make sure the user doesn't add the same place even with a lowercase name...
        for (Place somewhere : list){
            if (somewhere.name().equalsIgnoreCase(place.name())){
                System.out.printf("duplicate of '%s' found", place);
                return;
            }
        }

        //making sure that the places are added closer-to-sydney (distance is smaller) first
        int matchedIndex = 0;
        for (var listPlace : list){
            if (place.distance() < listPlace.distance()){
                list.add(matchedIndex, place);
                return;
            }

            matchedIndex++;
        }

        list.add(place);
//        System.out.println(list);
    }

    private static void printInstructions(){
        String text = """
                Available actions (select word or letter)
                (F)orward
                (B)ackward
                (L)ist Places
                (M)enu
                (Q)uit""";

        System.out.println(text);
    }

    private static void forwad(LinkedList<Place> places){
        var iterator = places.listIterator();

        var nextPlace = iterator.next();
        if (iterator.hasNext())
            System.out.println("-> " + nextPlace);
        else
            System.out.println("whoa! You're all caught up.");
    }

    private static void backward(LinkedList<Place> places){
        var iterator = places.listIterator();

        var previousPlace = iterator.previous();

        if (iterator.hasPrevious())
            System.out.println(previousPlace + " <-");
        else
            System.out.println("whoa! You're all caught up.");

    }

    private static void Itinerary(LinkedList<Place> list){
        System.out.println("We start off at " + list.getFirst());

        var currentLocation = list.getFirst();

        ListIterator listIterator = list.listIterator(1);

        while (listIterator.hasNext()){
            var place = (Place) listIterator.next();
            System.out.println("-> " + currentLocation + " -> " + place);
            currentLocation = place;
        }


    }
}
