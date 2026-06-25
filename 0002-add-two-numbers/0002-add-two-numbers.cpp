/**
 * Definition for singly-linked list.
 * struct ListNode {
 * int val;
 * ListNode *next;
 * ListNode() : val(0), next(nullptr) {}
 * ListNode(int x) : val(x), next(nullptr) {}
 * ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */
class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        // Dummy head simplifies our code by eliminating edge cases for the head node
        ListNode* dummyHead = new ListNode(0);
        ListNode* current = dummyHead;
        int carry = 0;

        // Loop as long as there is something to add (l1 nodes, l2 nodes, or a leftover carry)
        while (l1 != nullptr || l2 != nullptr || carry != 0) {
            int sum = carry;

            if (l1 != nullptr) {
                sum += l1->val;
                l1 = l1->next;
            }

            if (l2 != nullptr) {
                sum += l2->val;
                l2 = l2->next;
            }

            // Calculate new carry and the digit to store in the new node
            carry = sum / 10;
            current->next = new ListNode(sum % 10);
            current = current->next;
        }

        // The actual result list starts at the node following the dummy head
        ListNode* result = dummyHead->next;
        delete dummyHead; // Free the placeholder memory
        return result;
    }
};