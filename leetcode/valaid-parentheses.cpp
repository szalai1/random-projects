class Solution {
public:
    bool isValid(string s) {
        std::stack<char> stack;
        for(char i : s) {
            if(i == '(' || i == '{' || i == '[') {
                stack.push(i);
            } else {
                if (stack.size() != 0) {
                    char p = stack.top();
                    stack.pop();
                    if (!(p == '(' && i == ')' ||
                         p == '{' && i == '}' ||
                         p == '[' && i == ']')) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return stack.size()==0;
    }
};