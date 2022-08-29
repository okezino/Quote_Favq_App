# Quote_Favq_App

This applications shows list of Random Inispiration quotes that can lighten your days. using Api from https://favqs.com, i will give you a tour guide on the application functionality 

## SPLASH SCREEN 
This splash screen shows an animated Text field with two Randown quotes and imediately users to the Login Screen 
<img src="https://user-images.githubusercontent.com/46386915/187205065-46a94de4-78bd-43cc-898e-e70b15fa71b2.png" width="300"/>

## LOGIN AND SIGNUP
For New users with no account, we can navigate to the SIGN UP screen to create an Account, on  successsful Account creation, users will be directed back to the login screen. 

<img src="https://user-images.githubusercontent.com/46386915/187206577-07547268-8744-4326-bada-afe4f3ceda6a.png" width="300"/>______
<img src="https://user-images.githubusercontent.com/46386915/187206642-83122f3c-ba41-4bfd-ae49-32bc1adf72a2.png" width="300"/>

## HOME AND DETAIL SCREEN

### Home Screen
On successful Login, User will view List of Random quotes, There are 3 ways the quote on the list can be Updated
1. Pull To refresh : pull down to refresh the List of Random quote on the App
2. Filter by Tag : Click on any of the Tag of a random quote and this will update the list with same Tags 
3. Filter by Search : Use the search field to input an text and the list will be update by quotes containing the text 


### Detail Screen
On clicking of the Random quote, we will view a well detailed screen of some of the details of that quote

<img src="https://user-images.githubusercontent.com/46386915/187207887-ac763701-f3d3-4f36-8fc5-4423b4814cd9.png" width="300"/>______
<img src="https://user-images.githubusercontent.com/46386915/187208108-eac5cffa-b86a-4c62-8186-efb570eeba52.png" width="300"/>

### List of Implemetation done 

- As a user, I want to see a random quote when I open the app.{Splash screen}
- As a user, I want to be able to browse through a list of public quotes.{Home Screen}
- As a user, I want to be able to search through public quotes.{Home Screen}
- As a user, I want to see the contents and author of any displayed quote.{Home Screen}
- As a user, I want to be able to log in with my existing FavQs account.{Login Screen}
- As a user, I want to be able to tap on any displayed quote to see its details.{Home Screen -> Detail Screen}
- As a user, I want to see the tags of any displayed quote.{Home Screen}
- As a user, I want to be able to tap on a tag to see a list of matching quotes.{Home Screen -> filter by tapping on Tags}
- As a user, I want to be able to sign up for a FavQs account.{Sign Up Screen}


## TECHNOLOGY

### MULTI THREADING : Coroutine and LiveData
### Navigation : JetPack Navigation Component 
### Dependency Injection : Dagger Hilt
### Network Call & Serialization  : Retrofit and Gson
### Testing : Junit 4 and Mockito
### Animation : Navigation Component and Custom Animation
### Architectural Design Pattern : Clean Architecture 






