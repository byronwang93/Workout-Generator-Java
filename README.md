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
I decided to implement the first Java language construct by making my project more robust through editing a method
to be able to handle exceptions. Within the Activity class, I changed the implementation of the changeIntensity method
to throw an InvalidIntensityException (an exception I created) whenever the user inputs an intensity that isn't
"easy", "medium", or "hard". Afterwards, I changed the project in the following areas:
- changed an existing test to account for when the exception is not expected
- added a new test to account for when the exception is expected
- changed the implementation of the changeIntensity methods in the GIU class and WorkoutGenerator class to have a
try/catch for this method now.


I also decided to edit a different method in a class to throw an exception.
In my WorkoutOptions class within the UI package, I modified the removeActivity method by making it throw an 
InvalidRemovalException if the inputted exercise name to remove doesn't exist within the list of workout options.
I also edited my test cases to test the functionality of my new checked Exception:
- In WorkoutOptionsTest class, on line 41, accounted for when the exception is not expected
- In WorkoutOptionsTest class, on line 55, accounted for when the exception is expected

## Phase 4: Task 3

If I had more time to work on the project, a huge source of potential refactoring would be
within my WorkoutGeneratorGUI class where I essentially had my entire GUI implemented in the class.
This is an example of low cohesion (where my class serves more than a single function), so it would make more sense to 
split up the functions into separate classes and make the WorkoutGeneratorGUI contain fields of these new classes.
On a similar note, each button action event was implemented within the WorkoutGeneratorGUI class so it might have made
more sense to implement the methods within the separate button classes.
Additional refactoring of my code could be:
- making my entire project more robust so that it handles exceptions
- put enums into the project because when users add a new activity, the intensity and muscle group only have 3 options
each, so we already know the possible values beforehand, which would help improve the readability of the code.