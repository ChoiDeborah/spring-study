How to run this project is...

Download http://h2database.com for All Platforms

$cd h2/bin
$chmod 755 h2.sh
$./h2.sh

The way to create database
jdbc:h2:~/test (Only once)

~/test.mv.db                    // Check the file created. after then, 
jdbc:h2:tcp://localhost/~/test  // Access like this

home-> test.mv.db 

run project