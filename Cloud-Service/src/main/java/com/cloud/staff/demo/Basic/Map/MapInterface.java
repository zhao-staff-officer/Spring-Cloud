package com.cloud.staff.demo.Basic.Map;

import java.util.*;

/**
 * Map实现类
 */
public class MapInterface {
    Map hashmap = new HashMap<String,String >();
    Map linkedHashMap = new LinkedHashMap();
    Map hashTable = new Hashtable();
    Map treeMap = new TreeMap();
    Map enumMap = new EnumMap(MapInterface.class);
}

/********************************************************************
 *             -------------------Map-----------------
 *             |           |             |           |
 *           HashMap    HashTable    SortedMap    EnumMap
 *             |           |             |
 *        LinkedHashMap  Properties    TreeMap
*********************************************************************/
