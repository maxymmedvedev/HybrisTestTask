# Hybris Internship Test Task
###### by M.Medvedev

This is my implementation of a test task for Hybris Ciklum Hybris internship.

- Database is MySQL
- Connection to DB is via JDBC
- Connection properties are stored in local.properties in resources directory
     and are loaded from classpath
- By default database sevrer is hosted at AWS RDS, but database dump file is also sullpied 
    in project scr/main/resources/MySQL
- Project is built using Gradle


# How to run application
By default all needed files are already created, database server is hosted and running.
To use application, simply go to **Hybris_Test_build\bin** and run file suitable for your operating system (*Hybris_Test* for Linux, *Hybris_Test.bat* for Windows), and follow the instructions

If you want to build application by yoursefl, you may use Gradle wrapper (gradlew, gradlew.bat)
from project root directory 

#### Database manipulation
As stated, by defatult MySQL database used for this project is hosted in cloud services, and all data required for connection is stored in **src/main/resources/local.propeties** file

Also, a workbase dump file is stored in  *src/main/resources/MySQL/*. You can create a database on your server from specified dump file. Please refer to MySQL manual for details if you need.
In order to connect to database restored from dump file you need to change some of the *local.properties* fields (server_adress, user, password etc).

### Source code

Project consists of the following classes/interfaces:

| Class /interface| README |
| ------ | ------ |
| ConnectionManager |Establish an manage connections to database, switching users |
| DBAcessor | represents all the functional requirements for this project |
| ProductStatus | ENUM which represents status of product in the database |
| JDBCDataAccessor | [implements DBAcessor interface, enables interaction with the database |
| UserInteraction | text (console) user interface for all necessary input/output operations|
| HybrisMainClassJava| Controls the flow of the program, inserts dependeies, application entry point |


## Have questions?
Feel free to contact me:
e-mail:maxymm.medvedev@gmail.com
Telegram: @mx_medvedev



[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [dill]: <https://github.com/joemccann/dillinger>
   [git-repo-url]: <https://github.com/joemccann/dillinger.git>
   [john gruber]: <http://daringfireball.net>
   [df1]: <http://daringfireball.net/projects/markdown/>
   [markdown-it]: <https://github.com/markdown-it/markdown-it>
   [Ace Editor]: <http://ace.ajax.org>
   [node.js]: <http://nodejs.org>
   [Twitter Bootstrap]: <http://twitter.github.com/bootstrap/>
   [jQuery]: <http://jquery.com>
   [@tjholowaychuk]: <http://twitter.com/tjholowaychuk>
   [express]: <http://expressjs.com>
   [AngularJS]: <http://angularjs.org>
   [Gulp]: <http://gulpjs.com>

   [PlDb]: <https://github.com/joemccann/dillinger/tree/master/plugins/dropbox/README.md>
   [PlGh]: <https://github.com/joemccann/dillinger/tree/master/plugins/github/README.md>
   [PlGd]: <https://github.com/joemccann/dillinger/tree/master/plugins/googledrive/README.md>
   [PlOd]: <https://github.com/joemccann/dillinger/tree/master/plugins/onedrive/README.md>
   [PlMe]: <https://github.com/joemccann/dillinger/tree/master/plugins/medium/README.md>
   [PlGa]: <https://github.com/RahulHP/dillinger/blob/master/plugins/googleanalytics/README.md>
