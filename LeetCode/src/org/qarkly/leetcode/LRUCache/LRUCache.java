package org.qarkly.leetcode.LRUCache;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: qarkly
 * Date: 14-8-5
 * Time: 下午11:53
 * To change this template use File | Settings | File Templates.
 */
public class LRUCache {
    private static final int Default_Capacity = 16;
    private HashMap<Integer,DoubleLinkedList> map ;

    private DoubleLinkedList tail;
    private DoubleLinkedList head;

    private int capacity;

    private int size = 0;

    public LRUCache(int capacity) {
        if(capacity <= 0){
            this.capacity = Default_Capacity;
            map = new HashMap<Integer, DoubleLinkedList>(capacity);
        }else{
            this.capacity = capacity;
            map = new HashMap<Integer, DoubleLinkedList>(capacity);
        }

    }

    public int get(int key) {
        if(map.containsKey(key)){
            DoubleLinkedList node = map.get(key);
            removeToHead(node);
            return node.val;
        }

        return -1;

    }

    private void removeToHead(DoubleLinkedList node) {
        if(size == 1 || node == head){
            return ;
        }

        if(node == tail){
            node.pre.next = null;
            tail = node.pre;
            node.next = head;
            node.pre = null;
            head.pre = node;
            head = node;
        }else{
            node.pre.next = node.next;
            node.next.pre = node.pre;
            node.next = head;
            node.pre = null;
            head.pre = node;
            head = node;

        }


    }

    public void set(int key, int value) {
        if(map.containsKey(key)){
            DoubleLinkedList node  = map.get(key);
            node.val = value;
            removeToHead(node);
        }else{
            if(size < capacity){
                DoubleLinkedList node = new DoubleLinkedList(key,value);
                map.put(key,node);
                addNode(node);
            }else{
                deleteTail(key, value);
            }
        }

    }

    private void deleteTail(int key, int value) {
        DoubleLinkedList node = tail;
        map.remove(node.key);
        node.key = key;
        node.val = value;
        map.put(key,node);
        if(size == 1){
            return;
        }
        tail.pre.next = null;
        tail = tail.pre;

        node.pre = null;
        node.next = head;
        head.pre = node;
        head = node;

    }

    private void addNode(DoubleLinkedList node) {
        size++;
        if(head == null){
            head = node;
            tail = node;
            return;
        }
        node.next = head;
        head.pre = node;
        head = node;
    }

    public void print(){
            DoubleLinkedList p = head;
            while (p != null){
                System.out.printf("("+p.key+","+p.val+")-->");
                p = p.next;
            }
        System.out.println();
    }

    public  class DoubleLinkedList{
        public DoubleLinkedList pre;
        public DoubleLinkedList next;

        public int val;
        public int key;

        public DoubleLinkedList(int key, int value){
            this.val = value;
            this.key = key;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(4);
        cache.set(1,1);
        cache.print();
        cache.set(2,2);
        cache.print();
        cache.set(3,3);
        cache.print();
        cache.set(4,4);
        cache.print();
        cache.set(5,5);
        cache.print();
        cache.get(2);
        cache.print();
        cache.get(3);
        cache.print();
    }
}