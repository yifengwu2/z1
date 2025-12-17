package com.c;

public class Solution02 {
    public void deleteNode(ListNode node) {
        //要删除的后继节点
        // 将下一个节点的值复制到当前节点
        node.val = node.next.val;
        // 跳过下一个节点
        node.next = node.next.next;

    }
}

