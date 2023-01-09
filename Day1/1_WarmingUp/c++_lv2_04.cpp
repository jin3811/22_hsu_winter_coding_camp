#include <iostream>
#include <string>
#include <vector>
#include <sstream>
using namespace std;

vector<string> split(const string& str, char delim) {
	vector<string> result;
	string token;
	stringstream sstream(str);
	while (getline(sstream, token, delim)) {
		result.push_back(token);
	}
	return result;
}

int main() {
	string str = "Hello This is Hansung Computer";
	vector<string> tokens = split(str, ' ');
	for (auto& str : tokens) {
		cout << str << endl;
	}
	return 0;
}