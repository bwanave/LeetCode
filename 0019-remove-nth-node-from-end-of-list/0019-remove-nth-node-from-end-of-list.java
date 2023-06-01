class Solution {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode first = dummyHead;
        ListNode second = dummyHead;
        for (int i = 0; i < n + 1; i++) second = second.next;
        
        while (second != null) {
            first = first.next;
            second = second.next;
        }
        
        first.next = first.next.next;
        return dummyHead.next;
    }
}
