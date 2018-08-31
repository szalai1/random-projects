#include <vector>
#include <iostream>

void solver(std::vector<int> arr) {
	std::cout << "-------" << "--------" << std::endl ;
	int sum = 0;
	int num_of_positives = 0;
	int index = 0;
	int counter = 0; 
	for ( std::vector<int>::iterator it = arr.begin(); it != arr.end(); ++it, ++index) {
		int diff = *it - index - 1;
		std::cout << index +1<< "\t" << *it << "\t-\t" << diff << "\t-\t" << sum+diff << " - " << counter << std::endl;
		if ( diff > 2 ) {
			std::cout << "Too chaotic" << std::endl;
		}
		sum += diff; 
		if ( diff > 0 ) {
			num_of_positives++;
			counter+=diff;
		} 
		if ( sum == 0 ) {
			std::cout << "\t=====" << std::endl;
			counter+= sum;
			num_of_positives = 0;
		} else {
			if ( diff <= 0 && num_of_positives + diff != 0) {
				counter++;
			}
		}
	}
	std::cout << counter << std::endl;
}


int main() {
	int T ; 
	std::cin >> T;
	for ( int t = 0; t < T; ++t) {
		int n;
		std::cin >> n;
		std::vector<int> v(0);
		for( int i = 0; i < n; ++i) {
			int num;
			std::cin >> num;
			v.push_back(num);
		}
		solver(v);
		
	}
}
