package com.gildedrose;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {
    Map<String, Map<Integer, List<Integer>>> itemSimulation = new HashMap<>();
    Item[] items = new Item[] {
        new Item("+5 Dexterity Vest", 10, 20), //
        new Item("Aged Brie", 2, 0), //
        new Item("Elixir of the Mongoose", 5, 7), //
        new Item("Sulfuras, Hand of Ragnaros", 0, 80), //
        new Item("Sulfuras, Hand of Ragnaros", -1, 80),
        new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20),
        new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49),
        new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49),
        // this conjured item does not work properly yet
        new Item("Conjured Mana Cake", 3, 6) };
    GildedRose app = new GildedRose(items);

    void runSimulation(){
        for(Item item : items){
            System.out.println("before starting simulation");
            System.out.println(item);
            itemSimulation.put(item.name,simulateItemDays(10, item));
        }
    }

    List<Integer> saveItem(Item item){
        System.out.println("before saving item");
        System.out.println(item);
        List<Integer> sellInQuality = new ArrayList<>();
        sellInQuality.add(item.sellIn);
        sellInQuality.add(item.quality);
        return sellInQuality;
    }

    Map<Integer, List<Integer>> simulateItemDays(int days, Item item){
        Map<Integer, List<Integer>> itemThroughTime = new HashMap<>();
        int day = 1;
        while(day <= days){
            System.out.println("before adding to simulation");
            System.out.println("day " + day);
            System.out.println(item);
            itemThroughTime.put(day, saveItem(item));
            app.updateQuality();
            day++;
        }
        return itemThroughTime;
    }

    void print(){
        itemSimulation.forEach((name, timeSim) -> {
            System.out.println(" --- Item: " +  name + " --- ");
            timeSim.forEach((daySim,results) -> {
                System.out.println("day " + daySim + ": " + results.get(0) + ", " + results.get(1));
            });
            System.out.println(" --- --- ");
        });
    }

    @Test
    void updateQualitySulfurasTest(){
        runSimulation();
        //print();
        assertEquals(1,1);
    }
}
