# My Personal Project

## Workout Simulator

**I plan on creating a project that simulates a workout plan** based on some given inputs from the user, where
the inputs include:
- the intensity of workout you desire
- the body part/muscle group you would like to work on
- whether or not you have access to gym equipment

As someone who does a workout every night with my dad at home, there's only a few options of what we can do, so by 
building this project, not only do I hope to gain experience on the process of designing and creating a personal project, but also in hopes to
explore more fitness variations to incorporate into my daily life.
 

## User Stories

In the context of a workout-simulator:

- As a user, I want to be able to generate a workout plan based off of input preferences from the user
- As a user, I want to be able to add an exercise to the potential workout options
- As a user, I want to be able to remove an exercise from the potential workout options
- As a user, I want to be able to change the intensity of a given workout option
- As a user, I want to be able to access the list of workout options
- As a user, I want to be able to save the exercises that the user has inputted into the possible options list
- As a user, I want to be able to load my previously saved list of workout options


## Phase 4: Task 2

I decided to implement the first Java language construct by editing a method in a class to throw an exception.
In my WorkoutOptions class within the UI package, I modified the removeActivity method by making it throw an 
InvalidRemovalException if the inputted exercise name to remove doesn't exist within the list of workout options.
I also edited my test cases to test the functionality of my new checked Exception:
- In WorkoutOptionsTest class, on line 41, accounted for when the exception is not expected
- In WorkoutOptionsTest class, on line 55, accounted for when the exception is expected