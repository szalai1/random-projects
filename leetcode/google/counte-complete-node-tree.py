class Solution:
    def countNodes(self, root: Optional[TreeNode]) -> int:
        if root is None:
            return 0
        count = self.countNodes(root.right)
        count += self.countNodes(root.left)
        return 1 + count