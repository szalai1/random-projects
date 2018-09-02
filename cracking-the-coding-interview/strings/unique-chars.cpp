#include <iostream>
#include <algorithm>
#include <set>

// time: O(n log n ); space: O(1)
bool hasAllUniqueChars(std::string str ) {
	if ( str.length() < 2) {
		return true;
	}
	std::sort(str.begin(), str.end());
	for( int i = 1; i < str.length(); ++i) {
		if ( str[i] == str[i-1]) {
			return false;
		}	
	}
	return true;
}


//time: best: O(n) worst: O(n^2)
//space: O( n)
bool hasAllUniqueChars2(std::string str) {
	if ( str.length() < 2) {
		return true;
	}
	std::set<char> chars; 
	for ( auto c : str ) {
		if ( chars.find(c) != chars.end()) {
			return false;
		}
		chars.insert(c);
	}
	return true;
}

int main() {
	std::cout << hasAllUniqueChars("alma") << std::endl;
	std::cout << hasAllUniqueChars2("alma") << std::endl;
	std::cout << hasAllUniqueChars("lma") << std::endl;
	std::cout << hasAllUniqueChars2("lma") << std::endl;
	return 0;
}
