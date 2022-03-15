# Developer Guide

The purpose of this file is to capture the steps required to participate in development of this mod as well as document any particular standards, decisions, etc. relevant to this repository.

## Version Control (git)

As this repository is hosted on github, the fact that you should expect to use git is perhaps a forgone conclusion. Never-the-less, there are a few suggested settings for interested participants:
```
$ git config -l
user.name=Your Alias Here
user.email=Please use an email address that you consider dev-facing, or don't mind being spammed. It will be spammed.
push.default=current
```

In particular, setting name and email will generate useful commit logs, in the format `Author:     ALIAS <email>`. The push default will set behavior to only attempt to push your current branch to its configured upstream, which is likely the most intuitive if you are unfamiliar with version control software in general.

You can have github auto-generate an email for you, see [the github user account docs](https://docs.github.com/en/account-and-profile/setting-up-and-managing-your-github-user-account/managing-email-preferences/setting-your-commit-email-address) about how to get your noreply email set up and include it in your git config on the repo. This will ensure that your commits are counted towards your activity, and that you don't expose your email to a wider audience than you expect.

## Compiler, Build Tooling

A functional jdk is required to compile the source and perform any meaningful build tasks. You can find openjdk builds at [Adoptium's Website](https://adoptium.net/). You should expect to require java17 for 18.1 or higher.

You can verify your java install as follows:
```bash
$ java -version
openjdk version "17.0.1" 2021-10-19
OpenJDK Runtime Environment (build 17.0.1+12-Ubuntu-120.04)
OpenJDK 64-Bit Server VM (build 17.0.1+12-Ubuntu-120.04, mixed mode, sharing)
```

You will also need a functional gradle installation, and while you can usually rpm/apt/etc. your way into a functional gradle install, we recommend either relying on your IDE or following the instructions at [gradle's website](https://docs.gradle.org/current/userguide/installation.html) to create a functional gradle install. You should expect to require gradle 7.3 or later for the 18.x branches.

You can verify your gradle version as follows:
```bash
$ gradle wrapper -version

------------------------------------------------------------
Gradle 7.4
------------------------------------------------------------

Build time:   2022-02-08 09:58:38 UTC
Revision:     f0d9291c04b90b59445041eaa75b2ee744162586

Kotlin:       1.5.31
Groovy:       3.0.9
Ant:          Apache Ant(TM) version 1.10.11 compiled on July 10 2021
JVM:          17.0.1 (Private Build 17.0.1+12-Ubuntu-120.04)
OS:           Linux 5.10.16.3-microsoft-standard-WSL2 amd64
```

## Codebase Notes

This mod project makes use of a git submodule, which is fairly simple to initialize but none the less must be initialized before you can build the project for the first time.

```bash
$ git submodule init
$ git submodule update
```

This should look a bit like this:
```bash
$ git submodule init
Submodule 'gradle' (https://github.com/noobanidus/gradlehax) registered for path 'gradle'
$ git submodule update
Cloning into '/home/coffee/devel/personal/MysticalWorld/gradle'...
Submodule path 'gradle': checked out '8b5b4f65de958167e313b1ca94072a287c44fe17'
$
```

Builds should be performed through the gradle wrapper, eg `./gradlew compileJava`. You may need to chmod u+x the gradle wrapper script in your fork.