# QQGroupNoty

A QQ Chat Bot Application for Evn Online Jabber notification.

## Deploy

### Requirements
 A running Window machine. I did not test for the any Linux distribution but it will work in theory. You might need to modify a bit of code in the FoolQQ to make sure the clean screen macro works.

### Configuration
A configuration file is required in the root directory, config in the format

HOST|PORT|UserName|Password|

e.g. 

goonsfleet.com|someport|myuname|123456

### Picture and others
And you will need to follow the setup instructions for the [FoolQQ](https://github.com/shiyafeng/foolqq).


### Compile the source code

mvn install


### Run the code

java -jar xxxxx.jar 


## Dependencies
[foolQQ](https://github.com/shiyafeng/foolqq)

[rocks.xmpp](https://bitbucket.org/sco0ter/babbler)
