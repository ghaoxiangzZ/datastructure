package com.ghaoxiang.datastructure.linkedlist;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author haoxiang_guo
 * @version 1.0.0
 * @ClassName LinkedListTest.java
 * @Description LinkedList链表单元测试
 * @createTime 2020年02月16日 23:12:00
 */
public class LinkedListTest<T> {

    @Test
    public void testCycleReversal() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.iterate();
        // 循环反转
        linkedList.head = linkedList.cycleReversal(linkedList.head);
        linkedList.iterate();
    }

    @Test
    public void testRecursiveReversal() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        linkedList.iterate();
        // 递归反转
        linkedList.head = linkedList.recursiveReversal(linkedList.head);
        linkedList.iterate();
    }

    @Test
    public void testHasCycleByPoint() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        System.out.println(linkedList.updateNext(4, 1));
        boolean result = linkedList.hasCycleByPoint(linkedList.head);
        Assert.assertEquals(true, result);
    }

    @Test
    public void testHasCycleByHash() throws Exception {
        LinkedList linkedList = new LinkedList();
        linkedList.addLast(1);
        linkedList.addLast(2);
        linkedList.addLast(3);
        linkedList.addLast(4);
        linkedList.addLast(5);
        System.out.println(linkedList.updateNext(4, 1));
        boolean result = linkedList.hasCycleByHash(linkedList.head);
        Assert.assertEquals(true, result);
    }
}