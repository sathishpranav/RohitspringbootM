#!/usr/bin/env groovy
/**
 * Simple Groovy Script Example
 * Demonstrates variables, lists, maps, loops, and functions
 */

// --- Variables ---
def name = "Groovy User"
def age = 25
println "Hello, $name! You are $age years old."

// --- List Example ---
def numbers = [1, 2, 3, 4, 5]
println "\nNumbers in the list:"
numbers.each { num -> println num }

// --- Map Example ---
def person = [name: "Alice", city: "Chennai", age: 30]
println "\nPerson details:"
person.each { key, value -> println "$key : $value" }

// --- Function Example ---
def factorial(n) {
    if (n < 0) {
        throw new IllegalArgumentException("Factorial is not defined for negative numbers.")
    }
    (n == 0 || n == 1) ? 1 : n * factorial(n - 1)
}

// --- Using the function ---
try {
    def num = 5
    println "\nFactorial of $num is: ${factorial(num)}"
} catch (Exception e) {
    println "Error: ${e.message}"
}

// --- Loop Example ---
println "\nCounting from 1 to 5:"
for (i in 1..5) {
    print "$i "
}
println "\nDone!"