#! /bin/bash

# COMMAND LINE VARIABLES
#branch name
# Ex: develop | master | feature/...
branchName=$1

# deploy port 
# Ex: 8080 | 8081 | 8082 
springPort=$2

# deploy front port 
# Ex: 8111 | 8112 | 8113 
frontPort=$3

# project name, deploy folder name and jar name
projectName=$4 #spring-boot

# external config file name
# Ex: application.yml
configFile=$5


#### CONFIGURABLE VARIABLES ######
#destination absolute path. It must be pre created or you can
# improve this script to create if not exists
destAbsPath=/home/ubuntu/$projectName/$branchName
configFolder=src/main/resources
##############################################################

#####
##### DONT CHANGE HERE ##############
#jar file
# $WORKSPACE is a jenkins variable
springSourceFile=$WORKSPACE/obosa_server/build/libs/$projectName*.jar
springDestFolder=$destAbsPath/obosa_server
springDestFile=$destAbsPath/obosa_server/$projectName.jar

#config files folder
sourConfigFolder=$WORKSPACE/obosa_server/$configFolder/*
destConfigFolder=$destAbsPath/obosa_server/$configFolder

properties=--spring.config.location=$destAbsPath/obosa_server/$configFolder/$configFile

#front dist folder
frontSourceFolder=$WORKSPACE/obosa_front/dist
frontDestFolder=$destAbsPath/obosa_front/dist

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
    fuser -n tcp -k $springPort > redirection &
    echo " "
    
    echo "Stoping process on port: $frontPort"
    fuser -n tcp -k $frontPort > redirection &
    echo " "
}

function deleteFiles(){
    echo "Deleting $springDestFile"
    rm -rf $springDestFile

    echo "Deleting $destConfigFolder"
    rm -rf $destConfigFolder

    echo "Deleting $dstLogFile"
    rm -rf $dstLogFile
    
    echo "Deleting $frontDestFolder"
    rm -rf $frontDestFolder

    echo " "
}

function copyFiles(){
    echo "      ====SERVER===="
    echo "Creating $springDestFolder"
    mkdir -p $springDestFolder
    echo "Copying files from $springSourceFile"
    cp $springSourceFile $springDestFile

    echo "Creating $destConfigFolder"
    mkdir -p $destConfigFolder
    echo "Copying files from $sourConfigFolder"
    cp -r $sourConfigFolder $destConfigFolder

    
    echo "      ====FRONT===="
    echo "Creating $frontDestFolder"
    mkdir -p $frontDestFolder
    echo "Copying files from $frontSourceFolder"
    cp -r $frontSourceFolder $frontDestFolder


}

function run(){
    nohup http-server -p $frontPort $frontDestFolder 2>&1 &
    echo "COMMAND: nohup http-server -p $frontPort $frontDestFolder 2>&1 &"
    echo " "

    #echo "java -jar $springDestFile --server.port=$springPort $properties" | at now + 1 minutes
    nohup nice java -jar $springDestFile --server.port=$springPort $properties $> $dstLogFile 2>&1 &
    echo "COMMAND: nohup nice java -jar $springDestFile --server.port=$springPort $properties $> $dstLogFile 2>&1 &"
    echo " "
}
function changeFilePermission(){
    echo "Changing File Permission: chmod 777 $springDestFile"
    chmod 777 $springDestFile
    echo " "
}   

function watch(){
    tail -f $dstLogFile |

        while IFS= read line
            do
                echo "$msgBuffer" "$line"

                if [[ "$line" == *"$whatToFind"* ]]; then
                    echo $msgAppStarted
                    pkill  tail
                fi
        done
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

# 5 - watch loading messages until  ($whatToFind) message is found
watch