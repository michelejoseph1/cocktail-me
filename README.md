# CocktailMe

## Table of Contents
1. [Overview](#Overview)
1. [Product Spec](#Product-Spec)
1. [Wireframes](#Wireframes)
2. [Schema](#Schema)

## Overview
### Description
CocktailMe allows users to insert a list of ingredients they have on hand and be returned a list of cocktail recipes they can create using them.  

### App Evaluation
[Evaluation of your app across the following attributes]
- **Category:** Food/Drink
- **Mobile:** App has edit and view components
- **Story:** Allows users to insert a list of ingredients they have on hand and be returned a list of cocktail recipes they can create using them  
- **Market:** Anyone that enjoys mocktails/cocktails could enjoy using this app.
- **Habit:** Users can explore endless cocktail recipes according to the ingredients they input whenever they want.
- **Scope:** Could be expanded by introducing social components (can post when you make a recipe/upload a picture of it, etc.)

## Product Spec

### 1. User Stories (Required and Optional)

**Required Must-have Stories**


* Send network requests to an API
* Parse the response from an API call and load into a model
* Define model objects in your applications to store basic API data

**Optional Nice-to-have Stories**

* Orientation functionality
* Improve the user interface through styling and coloring

### 2. Screen Archetypes

* Login 
* Register - User signs up or logs into their account
   * Upon Download/Reopening of the application, the user is prompted to log in to gain access to their profile information to be properly matched with another person. 
* Ingredient insertion screen - User inputs ingredients they have on-hand to use for cocktail
   * Upon inserting ingredient options users are matched with potential recipes
* Profile Screen 
   * Allows user to save their favorite recipes
* Recipe selection screen
   * Allows user to get a full-screen description of the recipe
* Settings Screen
   * Lets people change language, and app notification settings.

### 3. Navigation

**Tab Navigation** (Tab to Screen)

* Ingredient insertion
* Profile
* Settings

Optional:
* Discover (Top Recipes)

**Flow Navigation** (Screen to Screen)
* Forced Log-in -> Account creation if no log in is available
* Ingredient insertion -> Jumps to recipe list
* Profile -> Text field to be modified. 
* Settings -> Toggle settings


## Wireframes
<img width="420" alt="Screen Shot 2022-06-17 at 10 14 23 AM" src="https://user-images.githubusercontent.com/66920319/174346535-39656b66-2ed2-4ed0-8053-560a8b8f03c4.png">

### [BONUS] Digital Wireframes & Mockups
<img width="481" alt="Screen Shot 2022-06-16 at 9 27 04 AM" src="https://user-images.githubusercontent.com/66920319/174119898-7f1c63f9-9868-489c-a1ac-bafe21ae1271.png">

## Schema 
### Models
#### Cocktail

   | Property      | Type     | Description |
   | ------------- | -------- | ------------|
   | objectId      | String   | unique id for the name of cocktail (default field) |
   | ingredients        | String Array | image author |
   | image         | File     | image of cocktail |


### Networking
 - Create Ingredient Insertion Screen
      - (Read/GET) List of recipes using ingredients
   - Create Recipe Screen
      - (Create/POST) Create a new Recipe object
   - Profile Screen
      - (Read/GET) Query logged in user object
      - (Update/PUT) Update user profile image


##### An API Of Alcohol
- Base URL - [the-cocktail-db.p.rapidapi.com] (the-cocktail-db.p.rapidapi.com)

   HTTP Verb | Endpoint | Description
   ----------|----------|------------
    `GET`    | /drinks/strDrink | list all drinks containing ingredient
    `GET`    | /drinks/?strIngredient | return specific drinks by ingredient
