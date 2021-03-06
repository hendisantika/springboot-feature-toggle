# Spring boot & Feature Toggles

Developing applications becomes harder as they grow. We know that. What we don’t know is what’s the best way to deal with it. Watching [Feature Branches and Toggles in a Post-GitHub World • Sam Newman](https://www.youtube.com/watch?v=lqRQYEHAtpk) inspired me to try and explore implementing feature toggles in a micro-services context. *This post has examples for Java with Spring Boot but should be useful for other stacks.*

## Considerations

Feature toggles have some requirements that we need to take into account when thinking about them:

* **difficulty**: one point to consider is the amount of code YOU need to write to make everything awesome
* **default state**: when my app first starts what state will the flags be in? will the features be default-disabled? default-enabled?
* **run-time configuration**: can I change the state of the flags once the app has started?
* **persistence**: what happens when my service restarts? do all the flags reset to their initial state? do they maintain any changes I’ve made?
* *(advanced)* **trigger**: are they enabled/disabled for everyone all the time? or just a subset of users? how do you pick which users / connections / requests / API calls get the feature and which don’t?
* **suitability**: is the solution suited to the problem at hand? (are you over-engineering this? )

## Single instance

Using a single service instance is a good entry level problem for trying out workflows based on feature flags.

**Attempt 1**. Feature flags can be as simple as having a constant global *Map<String, Boolean>* which you can update/check from anywhere in your code. Let’s review our checklist:

* **difficulty**: low
* **default** state: default state is hard-coded
* **run-time** configuration: not without some boilerplate
* **persistence**: no persistence
* **trigger**: no ability to categorize requests differentiate between them
* **suitability**: this solution is suitable for very small, short-lived projects
    
**Attempt 2**. To try and overcome some of the short-comings of the previous attempt we could have a repository that stores the state of the flags when they’re changed. Also have a few strategies that tell us if a flag should or should not be considered for a certain user. Getting back to our list:

* **difficulty**: high. there’s a lot of repository/service methods to implement. not hard in itself, but the code needs to be written, tested and maintained.
* **default** state: default state is hard-coded
* **run-time configuration**: not without some boilerplate
* **persistence**: if implemented adds to the overall complexity
* **trigger**: if implemented adds to the overall complexity
* **suitability**: I don’t think this solution is worth pursuing when considering alternatives
 
**Attempt 3**. What if I want to both have my cake (have persistence and triggers) and eat it (have a low implementation cost)? One solution is to use the [Togglz library](https://www.togglz.org/) . It’s simple to use and [easy to get started with](https://www.togglz.org/documentation/overview.html) . Quick glance at the checklist:

* **difficulty**: low
* **default** state: default state is hard-coded (cold start) or loaded from store (subsequent starts)
* **run-time configuration**: has a nice console that allows you to change all variables
* **persistence**: can be added if needed with minimal code. multiple storage back-ends are available
* **trigger**: has support
* **suitability**: suitable for projects of all sizes

    