/*
 * @(#)TestHashMap.java
 *
 * Summary: example use of java.util.HashMap. Sample code to TEST HashMap.
 *
 * Copyright: (c) 2009-2010 Roedy Green, Canadian Mind Products, http://mindprod.com
 *
 * Licence: This software may be copied and used freely for any purpose but military.
 *          http://mindprod.com/contact/nonmil.html
 *
 * Requires: JDK 1.6+
 *
 * Created with: IntelliJ IDEA IDE.
 *
 * Version History:
 *  1.0 2009-01-01 - initial version
 */
package com.mindprod.example;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static java.lang.System.out;

/**
 * example use of java.util.HashMap. Sample code to TEST HashMap.
 *
 * @author Roedy Green, Canadian Mind Products
 * @version 1.0 2009-01-01 - initial version
 * @since 2009-01-01
 */
@SuppressWarnings( { "UnusedAssignment" } )
public final class TestHashMap
    {
    // --------------------------- main() method ---------------------------

    /**
     * Sample code to TEST HashMap. requires JDK 1.5+
     *
     * @param args not used
     */
    @SuppressWarnings( "unchecked" )
    public static void main( String[] args )
        {
        // create a new HashMap
        HashMap<String, String> h = new HashMap<String, String>( 149
                /* capacity */,
                0.75f
                /* loadfactor */ );

        // add some key/value pairs to the HashMap
        h.put( "WA", "Washington" );
        h.put( "NY", "New York" );
        h.put( "RI", "Rhode Island" );
        h.put( "BC", "British Columbia" );
        h.put( "NC", "North Carolina" );
        h.put( "NE", "Nebraska" );

        // look up a key in the HashMap
        String stateName = h.get( "NY" );

        // prints "New York"
        out.println( stateName );

        out.println( "enumerate all the keys in the HashMap" );
        // keySet gives you a Set, which is not a List.
        // If you need something you can sort, use toArray.
        // If you need a List, then use Arrays.asList.
        for ( String key : h.keySet() )
            {
            String value = h.get( key );

            // prints lines of the form NY New York
            // in effectively random order.
            System.out.println( key + " " + value );
            }

        out.println( "enumerate all the values in the HashMap" );
        // values gives you a Collection which is not a List.
        // If you need something you can sort, use to Array.
        // If you need a List, then use Arrays.asList.
        for ( String value : h.values() )
            {
            // prints lines of the form New York
            // in effectively random order.
            System.out.println( value );
            }

        out.println( "enumerate all the key/value Entries in the HashMap" );
        // This gives you a Map of Entry items. This is not suitable for sorting.
        for ( Map.Entry<String, String> entry : h.entrySet() )
            {
            // prints lines of the form NY=New York
            // in effectively random order.
            System.out.println( "as Entry: " + entry );

            // this does not require an expensive get lookup to find the value.
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println( "separately: " + key + " " + value );
            }

        out.println( "extract the keys into an array" );
        // actual type is a private nested static class HashMap.KeySet
        // This Set is not Serializable.
        Set<String> justKeys = h.keySet();

        // Use toArray that takes an skeleton String[] array,
        // otherwise we end up with a useless Object[] instead of a String[].
        String[] keys = justKeys.toArray( new String[justKeys.size()] );

        out.println( "extract values into an array, may contain duplicates unlike a Set." );
        // actual type is a private nested static class HashMap.Values
        // This Collection is not Serializable.
        Collection<String> justValues = h.values();
        String[] values = justValues.toArray( new String[justValues.size()] );

        out.println( "extract key/value pair entries into an array." );

        Set<Map.Entry<String, String>> justEntries = h.entrySet();

        @SuppressWarnings( "unchecked" ) Map.Entry<String, String>[] keyValuePairs =
                justEntries.toArray( new Map.Entry[justEntries.size()] );
        // Infuriatingly, this generates an unchecked conversion warning message.
        // Type erasure won't let us say:
        // Map.Entry<String, String>[] keyValuePairs =
        // justEntries.toArray ( new Map.Entry<String,String>[justEntries.size()] );
        // There might be some clever way of using Class.asSubclass to mollify the compiler.
        // There so many times when generics create more problems than they solve.
        }
    }
