package org.by.issoft.paramCollector;

public class B extends A {

	@Override
	public void print() {
		super.print();
		System.out.println("B");

	}

	public static void main(String[] args) {
		A a = new A();
		B b = new B();
		a.print();
		b.print();
	}
}
