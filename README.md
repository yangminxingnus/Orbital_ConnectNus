Project README

Team Name: #King in the Source#

Proposed Level of Achievement: Apollo 11

Scope of Project:
Our application will be used mainly by people who are currently around NUS. 
This application is designed for NUS students, staffs, visitors, including all the other members currently staying inside NUS campus. Initially we think that this project is able to be extended to all people in Singapore. However, due to the security issue, it is not considered to be safe enough since it is not easy to check the identity of a person. Also in our app, the google map function (accurate location of buildings) is only available on campus. Thus, it will more likely to be used by NUS students.

Motivation:
As in our project, people inside NUS campus, especially students, will benefit most from the platform that we have built. When they have lost something that is very important and they cannot even remember where they have lost their stuff, then it would take a long time and a lot of trouble to ask the lost and found offices all around the campus or call the student service centre for help, which is also a low chance for them to get their belongings back. Compared to that, it would be much more convenient if all the lost and found offices and individuals who have found something unclaimed post them onto one platform, and the owner just need to open their mobile app and search for what they have lost. In this way, they can easily find their belongings once someone has found and posted them.

Description of Detailed Features:
As for Milestone 3, we have successfully implemented all the three expected features.
Instead of using Google API to let the users login by Facebook, we have manually built a user login system after the welcome page. Students can enter their username and password and then login to the platform. They can also register an account if they do not have one. In consideration of privacy, we don’t think it is suitable if a person who has found an item or wants to sell an item does not expect others to know their Facebook account.
There are three main features in the app:

	1.	Around Me:
	This is a page with all kinds of news and information that is relevant to students. It will show students the school events that currently comes out, so that they are able to know more about what is happening on campus. We extract all the relevant news from our NUS email and post the date, title, a short description and also a link directing to the according website. Users can also share the event to their friends if they think it is interesting.

	2.	Lost and Found:
	In this feature, any user who has found any unclaimed items can make a post here, and people who have lost their belongings can search for them from all the posts. We have created an item list, showing all the items that have been found already. The owners can search for their belongings by keyword, and a filtered list will be shown. Users can check the date, item type, a detailed description, found location as well as the contact information of the person who found the item. Here we have inserted Google Map API for the owners to see clearly where others have found the items. Users are also able to create new posts onto the platform and deleted what they have posted. We have also created a refresh button for users so that they can see the updates of the database at any moment.
	
	3.	Exchange of Goods:
	Similar to the lost and found function, we have also built another platform here for users to post items that they do not want to own anymore. Students who are looking for goods can come to the platform and search for items that they want. As mentioned above, this platform is quite similar to the lost and found platform, we just made some changes on the item information since its function is different. Click on the post and users can see the post date, item type, description, selling price, the address and contact of the owner, and also a picture link of the item. Similar to lost and found function, users can also search for items they want by category, create new posts and delete their own posts, and refresh the platform to see new posts.

Technologies Used:
During the mobile app development process, we have been using Android Studio and Android Virtual Machine for designing the UI, main features and self testing. Considering the running speed of AVD on Android Studio, we have downloaded and installed Genemotion to run the app on a virtual device. As for database setup, initially in Milestone 2 we used SQLite to build a local database, but then we realised that this would not satisfy our requirements on the features expected. Thus, we had decided to rebuild the database using MySQL. We used our own computer as the server and we built the database on the localhost to achieve the database auto-update, information sharing and communication. As for connection between app and database, we used a BackgroundWorker java class and also PHP to send SQL commands and pull information.

Testing Details:
As for our project, since we are creating an Android application, we have been using Android Studio for app development and Android Virtual Device for testing our app.  We frequently test our project when each new feature has been launched. For database testing, we used Wampserver so that the database on our localhost can be shown visually. We have also imported the project into the real Android device to test the actual using effect on the perspective of app users.We make improvements and do testing for several times until we are satisfied.
