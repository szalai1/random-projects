class Solution(object):
    def licenseKeyFormatting(self, s, k):
        """
        :type s: str
        :type k: int
        :rtype: str
        """
        no_dash = s.replace("-", "").upper()
        n = len(no_dash)
        first_block_size = n%k
        result = no_dash[:first_block_size]
        for i in range(first_block_size, n):
            if (i-first_block_size)%k == 0 and result != "":
                result += "-"
            result += no_dash[i]
        return result

# time: 10mins