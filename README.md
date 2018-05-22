# myWaytor
Software Engineering Course Project

Restaurant food ordering mobile application, myWaytor, implemented with Android Studio and SQLiteStudio database.

#Purpose of this app: 

The purpose of developing this mobile android application, myWaytor, is to essentially make food ordering in dining settings 
more easily, efficiently and conveniently. The ultimate goal of this application is to reduce unnecessary time when ordering 
for food,  eliminate the probability of messing up on the orders and allow waiters to work more efficiently during busy hours. Think about it: taking orders and cashiering take up a huge factor of the business time. Although our application has two options of paying with card and cash, in the long run, paying with card on the device will outscale the cash portion and that will greatly reduces waiting time for customers.

#Design process challenges:

1) Regarding the prototype design, previously, group decided to have the application scan a QR barcode inside every restaurant to open its menu upon user login. Although this sounds efficient, there are quite a few trade offs; such as, certain devices do not support it, the code may be scratched or ripped off from the table which requires replacement, link to different URLs and troubleshoot technical issues. Instead of this, we proposed to implement a location detector with Google Maps API which tracks down and pinpoints the restaurant location by simply pressing a button. Secondly, we proposed our original idea of the application “TEnP”, which stands for travel, eat and play. The idea of this app was to introduce an entertainment element of the app which allows user to book traveling tickets, find local restaurants and seek tourist attractions to explore. However, the application appeared to be overly complex when we discussed in depth during the initial stage of planning. As a result, we decided to narrow it down to only the “eating” aspect of the app - an ordering in restaurant type of application which we refer to as myWaytor.

2) Regarding the database design, it was difficult to manage the foreign key constraints among different tables; essentially creating the “relational database” by connecting each pieces of information as a whole. I figured that each customer will contain an unique username, email and card number which will only correspond to a specific primary key within the database; thus, one to one relationship. This exact same concept applies to the credit card registration activity, though enforced differently, because each customer may have more than one credit cards(s), but those credit cards must corresponds to an existing user; one to many relationship. Because we are accessing our information through a local database, many authentication problems need to be resolved manually. Thus, making it more difficult to implement/design compared to real time network based databases, such as Firebase and MongoDB which already contain majority of the essential APIs embedded that will automatically receive updates with the latest data and perform authentication checks. Therefore, for our database with SQlite, I have to implement all the queries by hand for both execution and authentication.

#Development process challenges:

1) Hardware issue on laptop’s BIOS setting; HAXM version.
2) Software issue on Android Studio’s emulator configuration.
3) Database issue on generating database tables and extracting them from local directory; Android Device Monitor and conflicting with emulator.
4) Database foreign key issue; data types conflict
5) UI database interaction issue; manipulating java objects with the database
6) Countless exception failures issues

When I initially started developing, I had difficulty on troubleshooting the BIOs setting on my laptop due to the 
differences in HAXM version of the device and Android Studio. This took me really long time to resolve, because I realized 
that I updated my device’s HAXM version (this is on Mac) prior to updating Android Studio’s and that caused a mismatch in 
the system between the two versions resulting in conflict running the emulator. I had to manually execute commands into the 
system to override that issue. Android Studio also wasn’t helpful, because it just kept on telling me to update my BIOS 
setting, when the version should be downgraded instead. I looked all over the web for help and stackoverflow turns out to 
give me answers on how to “upgrade” rather than downgrade. Because I didn’t know that in the first place, my initial 
reactions were to keep on installing the newest HXAM version with the hope “acquiring the latest possible updates” and being 
able to start up the emulator. The process was frustrating, because I simply can’t do anything before connecting to the 
emulator for my app. On top of that, I have encountered numerous issues on configuration properties, such as build gradle, 
android manifest xml, and many other errors that strongly impacted my development. However, I managed to learn a lot on both 
the software and hardware aspects of the system in general. I also struggled to set up the database with SQLite due to 
syntax differences and approaches to obtain the database table. For Android Studio, I have to access my database through the 
monitor device and find it inside the data folder. When I was executing my create table queries, I ran into the problem of 
not creating a db pointer object in my onCreate function which led to no results found in the folder. It took me a while to 
notice that, but I managed to resolve that issue with some tutorials online. On the other hand, I also had issue with 
enabling foreign key constraints. initially, I thought foreign key is enabled by default, however in Android Studio it is 
not the case as I have to activate it manually on the database open function. Therefore, I could not obtain references for 
the tables until I resolved that issue. On top of that, foreign key must contain the same datatype as the primary key. As I 
was modifying my code and debugging the application, it kept on crashing until I realize that problem and fix it. I figured 
that debugging is quite challenging when it comes to large scale project, because one problem may be the consequence of many 
other problems as everything are relevant to one another. 

#Testing process challenges:

Bugs discovered: Crashes for buttons not handled with database/logical reasons, such as order counts cannot be less than or equal to 0. Login page handles three attempts login failure termination. Registration handles mismatching username and password, existing database username, password and email (unique account) primary key enabled, invalid length inputs handled. Credit card unique card number and foreign key references primary key of registration user id, which is unique; one to many relationship.Delete card must check first for existing card on file. Payment summary checks if orders are empty, pay button checks if orders exist and credit card is valid. For all these problems, I integrated the necessary check statements either with database functions and/or through user interface objects manipulation. Though, majority of the checks were performed by executing database queries to eliminate and prevent errors/crashes  when user interacts with the application - that is, to handle the “dumbest” possible situation that may occur from user requests.

