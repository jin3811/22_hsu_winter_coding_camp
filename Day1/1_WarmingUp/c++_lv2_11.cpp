#include <iostream>
#include <string>
#include <sstream>
#include <vector>

using namespace std;

string ltrim(string& str) {
	if (*str.begin() != ' ') return str;
	
	else {
		str.erase(str.begin());
		ltrim(str);
		return str;
	}
}

string rtrim(string& str) {
	if (*(str.end() - 1) != ' ') return str;

	else {
		str.erase(str.end() - 1);
		rtrim(str);
		return str;
	}
}

string trim(string str) {
	ltrim(str);
	rtrim(str);
	return str;
}

vector<string> split(const string& str, char delim) {
	vector<string> result;
	string token;
	stringstream sstream(str);
	while (getline(sstream, token, delim)) {
		result.push_back(trim(token));
	}
	return result;
}

int main() {
	string query;
	string longestString;
	cout << "임의의 이름을 ; 으로 구분하여 입력하시오(단, 마지막 ; 는 생략이 가능합니다.)" << endl;
	getline(cin, query);

	vector<string> tokens = split(query, ';');
	longestString = tokens[0];

	for (int i = 0; i < tokens.size(); i++) {
		cout << i + 1 << " : " << tokens[i] << endl;
		if (longestString.length() < tokens[i].length()) {
			longestString = tokens[i];
		}
	}

	cout << "가장 긴 이름은 " << longestString << endl;
}