# Yildiz-Engine common-authentication

This is the official repository of The Common Authentication library, part of the Yildiz-Engine project.
The common authentication library is a set of utility and helper classes to handle easily authentications.

## Features

* Authentication.
* Input validation system.
* Validation rules.
* Password encryption
* ...

## How to contribute

If your issue is not already in the issue tracker, please create a new entry with a clear title and description.
If you can fix it, please fork the repository, and create a pull request with the change.

## Requirements

To build this module, you will need a java 9 JDK and Maven 3.

## Coding Style and other information

Project website:
https://engine.yildiz-games.be

Issue tracker:
https://github.com/yildiz-online/common-authentication/issues

Wiki:
https://yildiz.atlassian.net/wiki

Quality report:
https://sonarcloud.io/dashboard/index/be.yildiz-games:common-authentication

## License

All source code files are licensed under the permissive MIT license
(http://opensource.org/licenses/MIT) unless marked differently in a particular folder/file.

## Build instructions

Go to your root directory, where you POM file is located.

Then invoke maven

	mvn clean install

This will compile the source code, then run the unit tests, and finally build a jar file.

## Usage

In your maven project, add the dependency

```xml
<dependency>
    <groupId>be.yildiz-games</groupId>
    <artifactId>common-auhentication</artifactId>
    <version>LATEST</version>
</dependency>
```
Replace LATEST with the correct version.

## Contact
Owner of this repository: Grégory Van den Borre
