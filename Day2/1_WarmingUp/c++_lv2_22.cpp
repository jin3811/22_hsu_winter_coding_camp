#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

class SortedArray {
private:
    vector<int> data;

public:
    SortedArray(vector<int>& values) {
        for (int val : values) {
            data.push_back(val);
            sort(data.begin(), data.end());
        }
    }

    SortedArray() {}

    ~SortedArray() {}
    friend SortedArray& operator<< (SortedArray& arr, int val);
    
    SortedArray operator+ (SortedArray& ref) {
        SortedArray result(this->data);
        for (int val : ref.data) {
            result << val;
        }
        return result;
    }

    int& operator[](int idx) {
        return data.at(idx);
    }
};

SortedArray& operator<< (SortedArray& arr, int val) {
    arr.data.push_back(val);
    sort(arr.data.begin(), arr.data.end());
    return arr;
}

int main() {
    SortedArray a, b, c;

    a << 3;
    b << 1 << 7;

    c = a + b;

    cout << a[0];
    c[2] = 5;
    cout << c[2];
}