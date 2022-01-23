from typing import List


class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        m, n = len(nums1), len(nums2)
        if n < m:
            n, m = m, n
            nums1, nums2 = nums2, nums1 
        half_point = int((n+m+1)/2)
        imin, imax = 0, m 
        while imin <= imax: 
            i = int((imin + imax) / 2)
            j = half_point - i
            if i < m and nums1[i] < nums2[j-1]:
                imin = i + 1
            elif i > 0 and nums1[i-1]  > nums2[j]:
                imax = i - 1
            else: 
                max_of_left = 0
                if i == 0: max_of_left = nums2[j-1]
                elif j == 0: max_of_left = nums1[i-1]
                else: max_of_left = max(nums1[i-1], nums2[j-1])

                if (n+m) %2 == 1:
                    return max_of_left
                
                min_of_right = 0
                if i == m: min_of_right = nums2[j]
                elif j == n: min_of_right = nums1[i]
                else: min_of_right = min(nums1[i], nums2[j]) 

                return (min_of_right + max_of_left) / 2.0
            

print(Solution().findMedianSortedArrays([1,2,3], [4,5,6,7]))