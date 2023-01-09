#include <iostream>
#include "Stack.hpp"
using namespace std;

int main() {
    Stack<int>* stack = new Stack<int>();
    stack->push(5);
    stack->push(4);
    stack->push(3);
    int val;
    stack->pop(val);
    cout << val << endl;

    Stack<float>* stack_f = new Stack<float>();
    stack_f->push(3.3);
    stack_f->push(4.3);
    float fval;
    stack_f->pop(fval);
    cout << fval << endl;
    stack_f->pop(fval);
    cout << fval << endl;
    return 0;
}