Introduction
============
The files explorercat-global.properties and log4j.properties should be built using local paths.
Basically, the goal is provide a tool to generate the properties file using a
config file.
Both files were shared in the SVN repository.
Now, they are not shared but created dynamically.

Config File
===========
The ANT script tries to open the local file "myConfig.properties".
This file is not present in the SVN repository.
If this file doesn't exist, the script tries to open "default.properties"
This file is shared and contains "deploy" setting for ExplorerCat.
Actually inside the config file, we have to define two tokens.
E.g. (the value are examples)
 GENERICPATH=....../workspace/ExplorerCat
 ECTEMP=/Users/loginXX/ectemp

How it works
============
To generate these files we have to run build.xml
 Select build.xml
 Click the right button
 Select "Run As" --->"Ant Build"

Inside the console panel you will see the result of this action.
The result of this action is the creation of two files
  explorercat-global.properties
  log4j.properties
  
Error Message
==============
The script checks the following points
 *) There should exist at least "myConfig.properties" or "default.properties"
 *) All $TOKEN have to be defined in the config file