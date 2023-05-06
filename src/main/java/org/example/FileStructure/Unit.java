package org.example.FileStructure;

import lombok.Data;
import org.example.Entity.AirportName;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;

@Data
public class Unit {
    Character currChar;
    Map<Character, Unit> map;
    int ind = -1;

    public Unit(String input, int indx) {
        map = new HashMap<>();
        currChar = input.charAt(0);
        if (input.length() == 1) {
            ind = indx;
        } else {
            Character character = input.charAt(0);
            Unit unit = new Unit(input.substring(1), indx);
            map.put(character, unit);
        }
    }

    public void put(String input, int indx) {
        if (input.length() == 1) {
            ind = indx;
        } else {
            Character character = input.charAt(0);
            Unit unit = map.get(character);
            String output = input.substring(1);
            if (unit == null) {
                unit = new Unit(output, indx);
                map.put(character, unit);
            } else {
                unit.put(output, indx);
            }
        }
    }

    public void firstFind(SortedSet<Integer> set, String searchString, int ind) {
        if(searchString.length() == 1){
            find(set);
            return;
        }
        Character ch = searchString.charAt(ind);
        Unit unit = map.get(ch);
        if(unit == null){
            System.out.println("Airport names does not matched by this pattern: " + searchString);
            return;
        }
        if (searchString.length() == ind+1) {
            unit.find(set);
            return;
        }
        unit.firstFind(set, searchString, ind+1);
    }

    public void find(SortedSet<Integer> set) {
        if(ind > 0) {
            set.add(ind);
        }
        map.forEach((k,v) ->{
            v.find(set);
        });
    }
}
