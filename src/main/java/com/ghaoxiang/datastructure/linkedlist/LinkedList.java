package com.ghaoxiang.datastructure.linkedlist;

import java.util.HashSet;
import java.util.Set;

/**
 * @author haoxiang_guo
 * @version 1.0.0
 * @ClassName LinkedList.java
 * @Description java实现链表
 * @createTime 2020年02月15日 22:53:00
 * 1、边界条件：
 * 1.1、如果链表为空时，代码是否能正常工作？
 * 1.2、如果链表只包含一个结点时，代码是否能正常工作？
 * 1.3、如果链表只包含两个结点时，代码是否能正常工作？
 * 1.4、代码逻辑在处理头结点和尾结点的时候，是否能正常工作？
 * 2、实现以下几个方法：
 * 2.1、单链表反转
 * 2.2、链表中环的检测
 * 2.3、两个有序的链表合并
 * 2.4、删除链表倒数第n个结点
 * 2.5、求链表的中间结点
 * 2.6、并实现LRU（最近最少）
 */
public class LinkedList<T> {

    // 头结点
    public Node<T> head;

    // 链表节点数量
    private int size = 0;

    private static class Node<T> {
        // 当前节点的后继节点
        private Node<T> next;
        // 当前节点储存的数据
        private T data;

        public Node(T data) {
            this.data = data;
        }

        public Node() {
        }
    }

    /*
     * @title addFirst
     * @description 添加头结点
     * @author haoxiang_guo
     * @param [t]
     * @updateTime 2020-02-16 12:54:45
     * @return void
     * @throws
     */
    public void addFirst(T t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        newNode.next = head;
        head = newNode;
        size++;
    }


    /*
     * @title addLast
     * @description 添加尾结点
     * @author haoxiang_guo
     * @param [t]
     * @updateTime 2020-02-16 12:55:00
     * @return void
     * @throws
     */
    public void addLast(T t) {
        Node newNode = new Node(t);
        if (head == null) {
            head = newNode;
            size++;
            return;
        }
        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
        size++;
    }

    /*
     * @title add
     * @description 在指定index后添加节点
     * @author haoxiang_guo
     * @param [t, index]
     * @updateTime 2020-02-16 12:55:10
     * @return void
     * @throws
     */
    public void add(T t, int index) {
        // 检查是否越界
        if (index < 0 || index > maxIndex()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            addFirst(t);
        }
        if (index == maxIndex()) {
            addLast(t);
        }
        Node newNode = new Node(t);
        // 从head开始移动的指针
        int position = 0;
        // 当前节点
        Node curr = head;
        // 当前节点前驱节点
        Node pre = null;
        while (curr != null) {
            if (position == index) {
                newNode.next = curr;
                pre.next = newNode;
                size++;
                return;
            }
            pre = curr;
            curr = curr.next;
            position++;
        }
    }

    public int maxIndex() {
        return size - 1;
    }

    /*
     * @title updateNext
     * @description 修改第updateIndex个链表节点的next指针指向destIndex节点
     * @author haoxiang_guo
     * @param [index, next]
     * @updateTime 2020-02-16 23:26:35
     * @return boolean
     * @throws
     */
    public boolean updateNext(int updateIndex, int destIndex) {
        if (updateIndex <= destIndex) {
            throw new IllegalArgumentException("updateIndex must before destIndex!");
        }
        int position = 0;
        Node dest = null;
        Node<T> curr = head;
        while (curr != null) {
            if (position == destIndex) {
                dest = curr;
            }
            if (position == updateIndex) {
                curr.next = dest;
                return true;
            }
            curr = curr.next;
            position++;
        }
        return false;
    }

    /*
     * @title getFirst
     * @description 获取头结点
     * @author haoxiang_guo
     * @param []
     * @updateTime 2020-02-16 13:56:04
     * @return T
     * @throws
     */
    public T getFirst() {
        return head.data;
    }

    /*
     * @title getLast
     * @description 获取尾结点
     * @author haoxiang_guo
     * @param []
     * @updateTime 2020-02-16 13:56:44
     * @return T
     * @throws
     */
    public T getLast() {
        if (head == null) {
            return null;
        }
        Node<T> last = head;
        while (last.next != null) {
            last = last.next;
        }
        return last.data;
    }

    /*
     * @title get
     * @description 获取指定节点
     * @author haoxiang_guo
     * @param [index]
     * @updateTime 2020-02-16 13:56:56
     * @return T
     * @throws
     */
    public T get(int index) {
        if (index < 0 || index > maxIndex()) {
            throw new IndexOutOfBoundsException();
        }
        int position = 0;
        Node<T> curr = head;
        while (curr != null) {
            if (position == index) {
                return curr.data;
            }
            curr = curr.next;
            position++;
        }
        return null;
    }

    /*
     * @title deleteFirst
     * @description 删除头结点
     * @author haoxiang_guo
     * @param []
     * @updateTime 2020-02-16 14:08:11
     * @return T
     * @throws
     */
    public T deleteFirst() {
        if (head == null) {
            return null;
        }
        Node<T> del;
        // 根据头结点是否设置进入不同分支
        if (head.next == null) {
            del = head;
            head = null;
        } else {
            // 将head的后继设置为新的head并将原head的值返回
            Node next = head.next;
            head.next = next.next;
            del = head;
            head = next;
        }
        size--;
        return del.data;
    }

    /*
     * @title deleteLast
     * @description 删除尾结点
     * @author haoxiang_guo
     * @param []
     * @updateTime 2020-02-16 14:08:35
     * @return T
     * @throws
     */
    public T deleteLast() {
        if (head == null) {
            return null;
        }
        Node<T> del = head;
        Node<T> curr = null;
        // 找到尾节点
        while (del.next != null) {
            curr = del;
            del = del.next;
        }
        curr.next = null;
        size--;
        return del.data;
    }

    /*
     * @title delete
     * @description 删除指定节点
     * @author haoxiang_guo
     * @param [index]
     * @updateTime 2020-02-16 14:13:37
     * @return T
     * @throws
     */
    public T delete(int index) {
        if (index < 0 || index > maxIndex()) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            return deleteFirst();
        }
        if (index == maxIndex()) {
            return deleteLast();
        }
        int position = 0;
        // 当前节点，默认取head
        Node<T> curr = head;
        // 当前节点前驱
        Node<T> pre = null;
        // 返回被删除的值
        Node<T> del = null;
        while (curr != null) {
            // 指针到达指定位置
            if (position == index) {
                // 将当前节点的前驱设置为当前节点的后继
                pre.next = curr.next;
                // 返回值赋值
                del = curr;
                // help GC
                curr.next = null;
                curr = null;
                break;
            }
            // 指针未到达指定位置依次将pre、curr2个节点后移一个节点并将指针后移
            pre = curr;
            curr = curr.next;
            position++;
        }
        size--;
        return del.data;
    }

    /*
     * @title iterate
     * @description 迭代链表
     * @author haoxiang_guo
     * @param []
     * @updateTime 2020-02-16 14:30:04
     * @return void
     * @throws
     */
    public void iterate() {
        System.out.println("-------------开始迭代");
        for (int i = 0; i < size; i++) {
            System.out.println(this.get(i));
        }
        System.out.println("-------------迭代结束");
    }

    /*
     * @title cycleReversal
     * @description 使用循环将单向链表反转
     * @author haoxiang_guo
     * @param [head]
     * @updateTime 2020-02-16 21:55:13
     * @return com.ghaoxiang.datastructure.linkedlist.LinkedList.Node
     * @throws
     */
    public Node cycleReversal(Node head) {
        // head的前驱，默认为null
        Node<T> pre = null;
        // head的后继，起临时变量的作用
        Node<T> temp;
        // 开始循环
        while (head != null) {
            // 将后继赋值给临时变量temp head -> temp(next)
            temp = head.next;
            // 将前驱赋值给后继 head -> null
            head.next = pre;
            // next -> head -> null
            pre = head;
            head = temp;
        }
        return pre;
    }

    /**
     * --------------------------------------------------2.1 反转单向链表 start ----------------------------------------------------
     */
    /*
     * @title recursiveReversal
     * @description 使用递归反转单向链表
     * @author haoxiang_guo
     * @param [head]
     * @updateTime 2020-02-16 21:48:26
     * @return com.ghaoxiang.datastructure.linkedlist.LinkedList.Node
     * @throws
     */
    public Node recursiveReversal(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 找到后继节点
        Node next = head.next;
        // 断开当前节点与后继节点的指针 A->B->C->D  ----->  A B C D
        head.next = null;
        // 递归执行找到后继节点...断开当前节点与后继节点指针...
        Node newNode = recursiveReversal(next);
        // 递归着将后继的后继变为当前节点 A->B->C->D  ----->  A<-B<-C<-D
        next.next = head;
        return newNode;
    }
    /**--------------------------------------------------2.1 反转单向链表 end ----------------------------------------------------*/

    /**
     * --------------------------------------------------2.2 链表中环的检测 start ----------------------------------------------------
     */
    /*
     * @title isLoop
     * @description 快慢指针判断是否存在环
     * @author haoxiang_guo
     * @param [head]
     * @updateTime 2020-02-16 23:03:50
     * @return boolean
     * @throws
     */
    public boolean hasCycleByPoint(Node head) {
        // head为空或者链表中只有head一个节点则不存在环
        if (head == null || head.next == null) {
            return false;
        }
        // 慢指针
        Node slowNode = head;
        // 快指针
        Node quickNode = head.next;
        while (slowNode != quickNode) {
            // 快指针已经遍历完链表，此时两者未相遇，表示链表不存在环
            if (quickNode == null || quickNode.next == null) {
                return false;
            }
            // 慢指针一次移动1个节点
            slowNode = slowNode.next;
            // 快指针一次移动2个节点
            quickNode = quickNode.next.next;
        }
        return true;
    }

    /*
     * @title hasCycleByHash
     * @description 用散列表判断链表是否存在环
     * @author haoxiang_guo
     * @param [head]
     * @updateTime 2020-02-16 23:07:51
     * @return boolean
     * @throws
     */
    public boolean hasCycleByHash(Node head) {
        Set set = new HashSet();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
    /**--------------------------------------------------2.2 链表中环的检测 end ----------------------------------------------------*/

    /**--------------------------------------------------2.2 两个有序的链表合并 start ----------------------------------------------------*/
    /**--------------------------------------------------2.2 两个有序的链表合并 end ----------------------------------------------------*/

    /**--------------------------------------------------2.2 删除链表倒数第n个结点 start ----------------------------------------------------*/
    /**--------------------------------------------------2.2 删除链表倒数第n个结点 end ----------------------------------------------------*/

    /**--------------------------------------------------2.5 求链表的中间结点 start ----------------------------------------------------*/
    /**--------------------------------------------------2.5 求链表的中间结点 end ----------------------------------------------------*/

    /**--------------------------------------------------2.6 实现缓存淘汰算法LRU（最近最少） start ----------------------------------------------------*/
    /**
     * --------------------------------------------------2.6 实现缓存淘汰算法LRU（最近最少） end ----------------------------------------------------
     */
}
