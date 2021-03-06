Notes-
Multiple monsters + high noise + moving monsters can mean that it can take a 
loooong time to complete running. Above .2 noise is not reccommended.

This lab would be harder with false negatives- the extentions would become
much harder, and the moving monster one would be nearly impossible.




Introduction

The goal of this lab is to apply your understanding of 2D arrays to implement
	a complex algorithm. In addition, you will implement a unit test for your
	program using the JUnit framework and create documentation for your
	program using JavaDoc.
	
One common application of computing is signal analysis. In real-world
	applications, the data that is processed is a combination of something of
	interest (the signal) and garbage that obscures the signal (noise).
	Computational techniques for signal analysis are prevalent in a wide
	variety of scientific and financial applications. This lab provides a
	simplified context in which to explore signal analysis. Conceptually, the
	techniques you will use in this lab are similar to those used in particle
	physics experiments.

Requirements

* Write a Monster Early Warning program that finds a stationary monster based
	on the data from your noisy radar system. This is described in
	DataGenetics blog post, "Detecting Signals in Noise." The starter code in
	GitHub implements the graphics and the foundation of the Radar class. You
	will complete the methods in the Radar class.
* Design your program such that the initial position of the monster can be
	specified interactively (e.g., from a user) or as parameters (e.g., for
	your unit test).
* Implement a unit test for your program that tests multiple cases.
* Document your code with standard JavaDoc comments.

Extensions:
* Add support for detecting multiple monsters.
* Add support for a moving monster. This is significantly more complicated!
* Add more awesome.

Submission:
* Submit a pull request in GitHub and submit a link to the request with this
	assignment.
