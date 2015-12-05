# AIProject

#### *TimeTable Scheduling using Genetic Algorithm*

- With the input from the user, the tasks with their priorities and no of hours required, we encode the tasks as a gene string and use genetic algorithm to find the best fitness valued gene over a fixed no of generations.
- The following are the key components of the algorithm :
   Tasks/Events : are given a unique integer and addressed by their name-String 
    * User enters duration and priorities for tasks
    * Default priority is set to 1.
  - EventList : Collection of all proposed events for the user. GA runs on the entire eventlist.
    * An event list can have any arbitrary no of events.
    * Each event list has a schedule capacity which indicates how many time slots are available to the user
  - Individual Gene : This is a gene-string composed of the unique ids’ of the events.
    * Its length is than of the schedule capacity.
  - Population : Collection of Individual genes, set by user.
  - Fitness Calculator : Returns the quantitative fitness value for a given gene using a polynomial function of the priorities of the events.
  - Genetic Algorithm : 
    * Elitism flag retains the fittest member of population into the next generation.
    * Mutation generates a new population with random crossover with members of existing population.
    * Fitter member are selected with some randomness.
    * New population is returned after every iteration.
  - Trade Off in Population size vs Generation iterations.
  - Reaches ~60% of best utility in 1% iterations and ~80% of best utility in 10% iterations, when using ~1000 iterations and 100 population size.

### Mobile Applications and it’s aspects

Navigation Drawer in the application

<img src="http://i.imgur.com/xb9UAiv.png" width="150">

This view was used to add new Tasks to your calender.

<img src="http://i.imgur.com/R5U6GDS.png" width="150">

This view was used to add new Blocks to the app. (Blocks such as time the user sleeps, eats lunch, travels etc.)

<img src="http://i.imgur.com/vqaTOHD.png" width="150">

This view was used to mark a task as completed

<img src="http://i.imgur.com/jWzLpLI.png" width="150">

This view was used to select the tasks the user wants to run the Genetic Algorithm on. The Run Schedule button was meant for this purpose. However, the button click is empty as of now.				

<img src="http://i.imgur.com/q5gkKmQ.png" width="150">  <img src="http://i.imgur.com/7jurSw6.png" width="150">

Calender View. Currently it displays all uncompleted task.

<img src="http://i.imgur.com/CVlulGo.png" width="150">  <img src="http://i.imgur.com/EYBZzCr.png" width="150">
