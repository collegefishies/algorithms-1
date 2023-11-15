# Algorithm Questions

## Question 1: 3-SUM in Quadratic Time

Design an algorithm for the 3-SUM problem that takes time proportional to \( n^2 \) in the worst case. You may assume that you can sort the \( n \) integers in time proportional to \( n^2 \) or better.

## Question 2: Search in a Bitonic Array

An array is bitonic if it is comprised of an increasing sequence of integers followed immediately by a decreasing sequence of integers. Write a program that, given a bitonic array of \( n \) distinct integer values, determines whether a given integer is in the array.

- Standard version: Use \( \sim 3 \log n \) compares in the worst case.
- Signing bonus: Use \( \sim 2 \log n \) compares in the worst case (and prove that no algorithm can guarantee to perform fewer than \( \sim 2 \log n \) compares in the worst case).

## Question 3: Egg Drop

Suppose that you have an \( n \)-story building (with floors 1 through \( n \)) and plenty of eggs. An egg breaks if it is dropped from floor \( T \) or higher and does not break otherwise. Your goal is to devise a strategy to determine the value of \( T \) given the following limitations on the number of eggs and tosses:

- Version 0: 1 egg, \( \leq T \) tosses.
- Version 1: \( \sim 1 \log n \) eggs and \( \sim 1 \log n \) tosses.
- Version 2: \( \sim \log T \) eggs and \( \sim 2 \log T \) tosses.
- Version 3: 2 eggs and \( \sim 2 \sqrt{n} \) tosses.
- Version 4: 2 eggs and \( \leq cT \) tosses for some fixed constant \( c \).
