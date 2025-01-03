# Billionaire's Expense Tracker
#### Embarking on the Billionaire Journey — Money Management

## Clarification
This repository contains the source code for my individual project, developed as part of the **CPSC210** course at the University of British Columbia (UBC). The project was created between January and April of 2024.

Initially, this project was hosted on a course-specific GitHub account. Unfortunately, I missed the opportunity to transfer the repository to my personal GitHub account before losing access to the course account. As a result, the repository history, including all commits and contributions, could not be preserved. 

The files uploaded here represent the final state of the project, retrieved directly from the original project folder on my local machine. While the Git history is unavailable, I remain the sole developer and author of the project.

## Description

In today's world, a lot of young people find themselves living paycheck to paycheck, where what they earn is what they spend each month. There are various reasons for this, like the economy not being so great, people losing jobs, and not having good money management skills. Therefore, I'm thinking of making an app, but not the usual kind – I want it to be all about helping users handle their money smartly, not just tracking expenses.

An expense tracking application for everyday users that allows you to input various categories of daily expenses and incomes. 

The non-trivial class (X):
- Expense *(with data, expense category, currency, comment)*
- Income
- Balance
- Budget *(with category, amount)*
- Large expense
- ...

The trivial class (Y):
- A list of budget, 
- A list of expense categories
- The monthly/yearly average expenses/incomes
- Lists of expenses and incomes in a day/month/year
- Lists of expenses in one category
- ...

## User Stories
- As a user, I want to be able to add an expense/income to my list of expenses and income.
- As a user, I want to be able to remove an expense/income from my list of expenses and income.
- As a user, I want to be able to set the budget of different categories of my budget list.
- As a user, I want to be able to set what percentage of the budget an expenditure takes up to be considered a large expense.
- As a user, I want to be able to alert users when an expense is identified as a large expense.
- As a user, I want to be able to view the balance of a day/month/year.
- As a user, I want to be able to view the monthly/yearly average expenses/income of my list of expenses and income. 
- As a user, I want to be able to view the total expenses/incomes in a day/month/year of my list of expenses and income. 
- As a user, I want to be able to view the total expenses of the one category of my list of expenses and income.
- As a user, I want to be able to view the remaining budget of different categories of my budget list.
- As a user, when I start the application, I want to be given the option to load my transaction from file.
- As a user, when I select the quit option from the menu, I want to be reminded to save my transaction to file and have the option to do so or not.


## Instructions for Grader
- You can generate the first required action related to the user story "adding an expense to my list of expenses" by clicking expense
- You can generate the second required action related to the user story "set the budget of different categories of my budget list" by clicking "no" at the first window
- You can locate my visual component by clicking budget at the welcome window, then click view budget remaining, and finally click view graph
- You can save the state of my application by clicking save
- You can reload the state of my application by clicking "yes" at the first window

## Phase 4: Task 2
- To add an expense, click on the 'Expense' button in the Welcome window. Once you've entered the year, month, day, amount, and type of the expense, click the 'Save' button. 
- After clicking 'Save,' the following information will be printed on the console:
- Wed Apr 03 15:08:22 PDT 2024
- The expense has been added!
- After completing the first step as described above, if you click the "View Expense List" button, the following information will be printed on the console:
- Wed Apr 03 15:12:58 PDT 2024
- The expense list has been viewed!

## Phrase 4: Task 3
In reflecting on the design presented in the UML class diagram for my project, I recognize some areas for potential improvement, particularly regarding the distribution of methods among classes. One aspect that stands out is the concentration of methods within the user class, which could be refactored for better organization and encapsulation.
If I had more time to work on the project, one refactoring I would consider is redistributing some of the methods from the user class to other related classes, particularly those belonging to the model layer. By doing so, I could enhance the cohesion and clarity of the design, ensuring that each class has a well-defined responsibility and reducing the potential for bloating the user class with unrelated functionalities. This refactoring aligns with the principles of object-oriented design, promoting better encapsulation and separation of concerns, ultimately leading to a more maintainable and extensible codebase.
