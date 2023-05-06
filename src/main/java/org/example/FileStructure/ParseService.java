package org.example.FileStructure;

import org.example.Entity.AirportName;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

public class ParseService {
    public Map<Character, Unit> map = new HashMap<>(32);

    public void resolve(Set<AirportName> set) {
        set.forEach(el ->{
            String s = el.getName().toLowerCase();
            Character character = s.charAt(0);
            int ind = el.getNumberInFile();
            if(map.get(character) != null) {
                map.get(character).put(s.substring(1), ind);
            }
            else {
                Unit unit = new Unit(el.getName(), el.getNumberInFile());
                map.put(character, unit);
            }
        });
    }

    public void find(SortedSet<Integer> set, String searchString){
        map.get(searchString.charAt(0)).firstFind(set, searchString, 1);
    }
}
