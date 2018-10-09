# HtmlContentExtractor

## Clone with GIT
Install Git

$ git clone https://github.com/apinzonf/HtmlContentExtractor.git


## Compile

Install JDK 1.8 and Maven 3.5

$ cd HtmlContentExtractor

$ mvn compile


## Packaging

$ mvn package


## Run

Install Java 8  

$ java -jar ./target/html-content-extractor-0.0.1-SNAPSHOT.jar -input ./src/main/resources/input.txt -output ./outputResults


## usage: html-context-extractor

* -input <arg>    a location of a file that has a list of URLs

* -output <arg>   a location of a folder to write the results