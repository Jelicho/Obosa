#! /bin/bash

# deploy front port 
# Ex: 8111 | 8112 | 8113 
frontPort=$1

# project name, deploy folder name and jar name
projectName=$2 #spring-boot


#### CONFIGURABLE VARIABLES ######
#destination absolute path. It must be pre created or you can
# improve this script to create if not exists
destAbsPath=/home/ubuntu/$projectName/master
##############################################################

#####
##### DONT CHANGE HERE ##############
#jar file
# $WORKSPACE is a jenkins variable

#front dist folder
frontSourceFolder=$WORKSPACE/obosa_front/dist/*
frontDestFolder=$destAbsPath/obosa_front/dist

### FUNCTIONS
##############
function stopServer(){
    echo " "
    echo "Stoping process on port: $frontPort"
    fuser -n tcp -k $frontPort > fuserFrontResult.txt 2>&1
    echo " "
}

function deleteFiles(){
    
    echo "Deleting $frontDestFolder"
    rm -rf $frontDestFolder
    echo " "
}

function copyFiles(){
    
    echo "      ====FRONT===="
    echo "Creating $frontDestFolder"
    mkdir -p $frontDestFolder
    echo "Copying files from $frontSourceFolder"
    cp -r $frontSourceFolder $frontDestFolder


}

function run(){
    echo " "
    echo "COMMAND: nohup http-server -p $frontPort $frontDestFolder 2>&1 &"
    nohup http-server -p $frontPort $frontDestFolder 2>&1 &
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

# 4 - start server
run
