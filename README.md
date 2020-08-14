# Readability Score
Readability Score project in Java. From JetBrains Academy (hyperskill.org).

### About
Everyone has their own personal reading history, and as we grow up, we are able to comprehend more and more complicated
texts. But how do you estimate the level of difficulty of a given text, and how do you teach a computer to do that? In
this project, you will find it out: write a program that determines how difficult the text is and for which age it is
most suitable.

##### Stage 1: Simplest estimation
Starting off simple, write a program that reads an input from System.in, then determines if it's easy or hard to read.
The criteria at this stage is, well, simple. If the input contains 100 symbols (including whitespace and punctuation) or
less, then it is easy. Otherwise, we'll consider it hard.

##### Stage 2: Words and sentences
Time to take another approach. We'll now base our decision on the average numbers of words in each sentence.

Read from System.in a single line of text containing sentences. Calculate the average number of words per sentence, and
output `EASY` if the average is 10 or lower. Otherwise output `HARD`.