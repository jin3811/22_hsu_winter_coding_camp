#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

template<typename T>
class Stack{
private:
    T* _data;
    int _top;

public:
    Stack(int _len){
        _data = new T[_len];
        _top = -1;
    }

    Stack() : Stack(10) {}

    ~Stack(){ delete[] _data; }
    
    void push(T val) {
        _data[_top++] = val;
    }

    bool is_empty() { return _top < 0; }

    int getSize() { return _top + 1; }

    void pop(T& val) {
        if (is_empty()) {
            cerr << "stack empty" << endl;
            return;
        }

        val = _data[_top--];
    }
};

int main () {
    Stack<int> *stack = new Stack<int>();
    stack->push(5);
    stack->push(4);
    stack->push(3);
    int val;
    stack->pop(val);
    cout << val << endl;

    Stack<float> *stack_f = new Stack<float>();
    stack_f->push(3.3);
    stack_f->push(4.3);
    float fval;
    stack_f->pop(fval);
    cout << fval << endl;
    stack_f->pop(fval);
    cout << fval << endl;
    return 0;
}