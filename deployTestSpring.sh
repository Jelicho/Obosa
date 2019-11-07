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
destAbsPath=/home/ubuntu/$projectName
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
    fuser -n tcp -k $springPort > fuserTestSpringResult.txt 2>&1
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
    cp $sourConfigFolder $destConfigFolder

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

watch