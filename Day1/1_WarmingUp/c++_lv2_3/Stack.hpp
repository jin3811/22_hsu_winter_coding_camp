#include <iostream>
using namespace std;

template<typename T>
class Stack {
private:
    T* data;
    int top;

public:
    Stack(int _len) {
        data = new T[_len];
        top = -1;
    }

    Stack() : Stack(10) {}

    ~Stack() { delete[] data; }

    void push(T val) {
        data[++top] = val;
    }

    bool isEmpty() { return top < 0; }

    int getSize() { return top + 1; }

    void pop(T& val) {
        if (isEmpty()) {
            cerr << "stack empty" << endl;
            return;
        }
        val = data[top--];
    }
};