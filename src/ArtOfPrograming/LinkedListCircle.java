package ArtOfPrograming;

public class LinkedListCircle {

	public static void main(String[] args) {

	}
	
	/*
	 * return true if the List has Circle, or
	 * false, List do not has Circle.
	 */
	public boolean hasCircle(ListNode head){
		ListNode node1 = head;
		ListNode node2 = head;
		if(head == null || head.next == null) return false;
		while(node1 != null && node1.next != null){
			node1 = node1.next.next;
			node2 = node2.next;
			if(node2 == node1){
				return true;
			}
		}
		return false;
	}
	
	/*
	 * return the node where circle begins,
	 * If there is no circle, return null.
	 */
	public ListNode detectCycle(ListNode head){
		ListNode node1 = head;
		ListNode node2 = head;
		if(head == null || head.next == null) return null;
		int i=0, j;
		while(node1 != null && node1.next != null){
			node1 = node1.next.next;
			node2 = node2.next;
			if(node2 == node1){
				node1 = head;
				break;
			}
		}
		//没有环，返回空
		if( node1 == null || node1.next == null) return null;
		while(node1 != node2){
			node1 = node1.next;
			node2 = node2.next;
		}
		return node1;
	}
}

class ListNode{
	int val;
	ListNode next;
	public ListNode(int x){
		this.val = x;
		next = null;
	}
}