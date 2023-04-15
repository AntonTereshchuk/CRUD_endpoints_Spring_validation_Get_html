package com.example.map_elements_reading_on_web_server;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;

@RestController
public class MapElementsController {

    private int id = 4;

    private HashMap<Integer, String> hm = new HashMap<>();

    {
        for(int i = 0; i < 5; i++) {
            hm.put(i, "Text " + i);
        }
    }

    @GetMapping("/allElements")
    public String showAllElements() {

        String allElementsToString = "";

        for (Integer k : hm.keySet()) {
            allElementsToString +=  hm.get(k) + " ";
        }

        return allElementsToString;
    }

    @GetMapping("/element/{id}")
    public String showElement(@PathVariable Integer id) {

        if(id < 0 || id >= hm.size()) {
            return "Element not found !";
        }

        return hm.get(id);

    }

    @PostMapping("/postElement")
    public ResourceIdHolder postElement(@RequestBody @Validated Element element) {
        var idHolderElement = new ResourceIdHolder(++id);
        hm.put(idHolderElement.getId(), element.getElement());
        return idHolderElement;
    }

    @PostMapping("/postElements")
    public ArrayList<ResourceIdHolder> postGroupOfElements(@RequestBody Element[] elements) {

        ArrayList<ResourceIdHolder> resourceIdHolders = new ArrayList<>();

        for (int i = 0; i < elements.length; i++) {
            var idHolderElement = new ResourceIdHolder(++id);
            resourceIdHolders.add(idHolderElement);
            hm.put(idHolderElement.getId(), elements[i].getElement());
        }
        return resourceIdHolders;

    }

    @PutMapping("/putElement")
    public String putElement(@RequestBody Element element) {

        //hm.put(element.getId(), element.getElement());
        hm.put(element.getId(), element.getElement());
        System.out.println("Test of out");
        System.out.println(element.toString());
        return "Element with id " + element.getId() + " is placed";

    }

    @DeleteMapping("/deleteElement/{id}")
    public String deleteElement(@PathVariable Integer id) {

        if (hm.containsKey(id)) {
            hm.remove(id);
            return "Element with id " + id + " has been deleted";
        } else {
            return "Element with id " + id + " doesn't exist";
        }

    }

}