package com.example.lint_k2_constructor_call

import com.example.mylibrary.Foo
import com.example.mylibrary.FooWithGeneric

class MyClass {

    fun callFoo() = Foo() // always detected

    fun callFooWithGeneric() = FooWithGeneric<Any>() // not detected in k2

    fun callBaz() = Baz() // always detected

    fun callBazWithGeneric() = BazWithGeneric<Any>() // always detected
}