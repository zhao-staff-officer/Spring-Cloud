package com.cloud.staff.apifirst.controller.java.Basic.Map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap源码分析
 */
public class HashMapDemo {
    //hash值算法
    static final int hash(Object key) {
        int h;
        System.out.println("hashMap-key-hash算法："+key.hashCode());
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    public static void main1(String[] args) {
        HashMap hashMap =new HashMap();
        hashMap.put("a","a");
        hashMap.put("b","b");
        hashMap.put("zhangsan","zhangsan");
        hashMap.put(Integer.valueOf(97),"integer");

    }
    public static void main(String[] args) {
        int a = -5;
        System.out.println(a);
        System.out.println("二进制输出"+Integer.toBinaryString(a));
        System.out.println(a+"<<2"+"="+(a<<2));
        System.out.println("二进制输出"+Integer.toBinaryString(a<<2));
        System.out.println(a+">>2"+"="+(a>>2));
        System.out.println("二进制输出"+Integer.toBinaryString(a>>2));
        System.out.println("无符号右移"+a+">>>2"+"="+(a>>>2));
        System.out.println("二进制输出"+Integer.toBinaryString(a>>>2));
        System.out.println();
        int b = 5;
        System.out.println(b);
        System.out.println("二进制输出"+Integer.toBinaryString(b));
        System.out.println(b+"<<2"+"="+(b<<2));
        System.out.println("二进制输出"+Integer.toBinaryString(b<<2));
        System.out.println(b+">>2"+"="+(b>>2));
        System.out.println("二进制输出"+Integer.toBinaryString(b>>2));
        System.out.println("无符号右移"+b+">>>2"+"="+(b>>>2));
        System.out.println("二进制输出"+Integer.toBinaryString(b>>>2));
    }
}
/**************************源码分析******************************************/
/**************************全局变量******************************************
*    //初始化容器大小16
*    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;
*    //最大容量大小2^30
*    static final int MAXIMUM_CAPACITY = 1 << 30;
*    //扩容系数0.75
*    static final float DEFAULT_LOAD_FACTOR = 0.75f;
*    //链表转红黑树阀值，当链表数>8是会转换为红黑树
*    static final int TREEIFY_THRESHOLD = 8;
*    //红黑树转链表阀值，当红黑树<6时转链表
*    static final int UNTREEIFY_THRESHOLD = 6;
*    //当整个容器元素大于64时，转换为红黑树
*    static final int MIN_TREEIFY_CAPACITY = 64;
*    //存储数据组
*    transient HashMap.Node<K,V>[] table;
*    //迭代数据
*    transient Set<Map.Entry<K,V>> entrySet;
*    //元数数量
*    transient int size;
*    //map修改次数
*    transient int modCount;
*    //加载因子
*    final float loadFactor;
*
*******************************************************************************/

/************************method.put()******************************************
 *
 * public V put(K key, V value) {
 *     //key计算hash值，key,value,false:替换旧值，true
 *     return putVal(hash(key), key, value, false, true);
 * }
 *
 *
 *  final V putVal(int hash, K key, V value, boolean onlyIfAbsent,boolean evict) {
 *         Node<K,V>[] tab; Node<K,V> p; int n, i;
 *          //【1】如果当前数组为空或者长度为0,resize初始化table长度
 *         if ((tab = table) == null || (n = tab.length) == 0)
 *             n = (tab = resize()).length;
 *          //【2】如果table数组未插入数据，newNode创建放入
 *         if ((p = tab[i = (n - 1) & hash]) == null)
 *             tab[i] = newNode(hash, key, value, null);
 *          //【3】如果当前数组有值
 *         else {
 *             Node<K,V> e; K k;
 *             //【A】判断当前hashcode，key是否与table[i]相等，相等直接把table[i]赋为新值
 *             if (p.hash == hash && ((k = p.key) == key || (key != null && key.equals(k))))
 *                 e = p;
 *             //【B】判断table[i]是否为红黑树，如果是转换红黑树新增
 *             else if (p instanceof TreeNode)
 *                 e = ((TreeNode<K,V>)p).putTreeVal(this, tab, hash, key, value);
 *             else {
 *             //【C】遍历链表，遍历循环
 *                 for (int binCount = 0; ; ++binCount) {
 *                    //判断next是否有值，如果没有，直接插入，判断是否查过8转换为红黑树
 *                     if ((e = p.next) == null) {
 *                         p.next = newNode(hash, key, value, null);
 *                         if (binCount >= TREEIFY_THRESHOLD - 1) // -1 for 1st
 *                             treeifyBin(tab, hash);
 *                         break;
 *                     }
 *                     //next有值，判断当前hash值是否一致，key与下个next是否一致，一致则将旧值当新值
 *                     if (e.hash == hash &&
 *                         ((k = e.key) == key || (key != null && key.equals(k))))
 *                         break;
 *                     p = e;
 *                 }
 *             }
 *             //【D】处理
 *             if (e != null) { // existing mapping for key
 *                 V oldValue = e.value;
 *                 if (!onlyIfAbsent || oldValue == null)
 *                     e.value = value;
 *                 afterNodeAccess(e);
 *                 return oldValue;
 *             }
 *         }
 *         【4】
 *         ++modCount;
 *         if (++size > threshold)
 *             resize();
 *         afterNodeInsertion(evict);
 *         return null;
 *     }
 *
 ******************************************************************************/

/***********************resize()***********************************************
 * inal Node<K,V>[] resize() {
 *         Node<K,V>[] oldTab = table;
 *         int oldCap = (oldTab == null) ? 0 : oldTab.length;
 *         int oldThr = threshold;
 *         int newCap, newThr = 0;
 *         if (oldCap > 0) {
 *             if (oldCap >= MAXIMUM_CAPACITY) {
 *                 threshold = Integer.MAX_VALUE;
 *                 return oldTab;
 *             }
 *             else if ((newCap = oldCap << 1) < MAXIMUM_CAPACITY &&
 *                      oldCap >= DEFAULT_INITIAL_CAPACITY)
 *                 newThr = oldThr << 1; // double threshold
 *         }
 *         else if (oldThr > 0) // initial capacity was placed in threshold
 *             newCap = oldThr;
 *         else {               // zero initial threshold signifies using defaults
 *             newCap = DEFAULT_INITIAL_CAPACITY;
 *             newThr = (int)(DEFAULT_LOAD_FACTOR * DEFAULT_INITIAL_CAPACITY);
 *         }
 *         if (newThr == 0) {
 *             float ft = (float)newCap * loadFactor;
 *             newThr = (newCap < MAXIMUM_CAPACITY && ft < (float)MAXIMUM_CAPACITY ?
 *                       (int)ft : Integer.MAX_VALUE);
 *         }
 *         threshold = newThr;
 *         @SuppressWarnings({"rawtypes","unchecked"})
 *         Node<K, V>[] newTab = (Node<K,V>[])new Node[newCap];
 *         table = newTab;
 *         if (oldTab != null) {
 *             for (int j = 0; j < oldCap; ++j) {
 *                 Node<K,V> e;
 *                 if ((e = oldTab[j]) != null) {
 *                     oldTab[j] = null;
 *                     if (e.next == null)
 *                         newTab[e.hash & (newCap - 1)] = e;
 *                     else if (e instanceof TreeNode)
 *                         ((TreeNode<K,V>)e).split(this, newTab, j, oldCap);
 *                     else { // preserve order
 *                         Node<K,V> loHead = null, loTail = null;
 *                         Node<K,V> hiHead = null, hiTail = null;
 *                         Node<K,V> next;
 *                         do {
 *                             next = e.next;
 *                             if ((e.hash & oldCap) == 0) {
 *                                 if (loTail == null)
 *                                     loHead = e;
 *                                 else
 *                                     loTail.next = e;
 *                                 loTail = e;
 *                             }
 *                             else {
 *                                 if (hiTail == null)
 *                                     hiHead = e;
 *                                 else
 *                                     hiTail.next = e;
 *                                 hiTail = e;
 *                             }
 *                         } while ((e = next) != null);
 *                         if (loTail != null) {
 *                             loTail.next = null;
 *                             newTab[j] = loHead;
 *                         }
 *                         if (hiTail != null) {
 *                             hiTail.next = null;
 *                             newTab[j + oldCap] = hiHead;
 *                         }
 *                     }
 *                 }
 *             }
 *         }
 *         return newTab;
 *     }
 *
 */

























