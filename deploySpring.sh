#! /bin/bash


# deploy port 
# Ex: 8080 | 8081 | 8082 
springPort=$1

projectName=$2
# external config file name
# Ex: application.yml
configFile=$3


#### CONFIGURABLE VARIABLES ######
#destination absolute path. It must be pre created or you can
# improve this script to create if not exists
sourceAbsPath=/home/ubuntu/ObosaSpring
destAbsPath=$sourceAbsPath/deploy
configFolder=src/main/resources
##############################################################

#####
##### DONT CHANGE HERE ##############
#jar file
# $WORKSPACE is a jenkins variable
springSourceFile=$sourceAbsPath/$projectName*.jar
springDestFolder=$destAbsPath
springDestFile=$springDestFolder/$projectName.jar

#config files folder
sourConfigFile=$sourceAbsPath/$configFile
destConfigFolder=$destAbsPath/$configFolder

properties=--spring.config.location=$destConfigFolder/$configFile

#CONSTANTS
logFile=initServer.log
dstLogFile=$destAbsPath/$logFile
#whatToFind="Started Application in"
whatToFind="Started "
msgLogFileCreated="$logFile created"
msgBuffer="Buffering: "
msgAppStarted="Application Started... exiting buffer!"

### FUNCTIONS
##############
function stopServer(){
    echo " "
    echo "Stoping process on port: $springPort"
    sudo fuser -n tcp -k $springPort > redirection &
    echo " "
}

function deleteFiles(){
    echo "Deleting $springDestFile"
    rm -rf $springDestFile

    echo "Deleting $destConfigFolder"
    rm -rf $destConfigFolder

    echo "Deleting $dstLogFile"
    rm -rf $dstLogFile
    
    echo " "
}

function copyFiles(){
    echo "      ====SERVER===="
    echo "Creating $springDestFolder"
    mkdir -p $springDestFolder
    echo "Copying jar file to $springDestFile"
    cp $springSourceFile $springDestFile

    echo "Creating $destConfigFolder"
    mkdir -p $destConfigFolder
    echo "Copying Config file to $destConfigFolder"
    cp $sourConfigFile $destConfigFolder

}

function run(){
    nohup nice java -jar $springDestFile --server.port=$springPort $properties $> $dstLogFile 2>&1 &
    echo "COMMAND: nohup nice java -jar $springDestFile --server.port=$springPort $properties $> $dstLogFile 2>&1 &"
    echo " "
}
function changeFilePermission(){
    echo "Changing File Permission: chmod 777 $springDestFile"
    chmod 777 $springDestFile
    echo " "
}   


### FUNCTIONS CALLS
#####################
# Use Example of this file. Args: branchname | springPort | frontPort | project name | external resourcce
# BUILD_ID=dontKillMe /path/to/this/file/api-deploy.sh develop 8080 8111 obosa application.yml

# 1 - stop server on port ...
stopServer

# 2 - delete destinations folder content
deleteFiles

# 3 - copy files to deploy dir
copyFiles

changeFilePermission
# 4 - start server
run