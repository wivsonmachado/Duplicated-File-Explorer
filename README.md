# Duplicated File Explorer

The project consists on explore all file system(based by root path) looking for duplicated files.

## How it works?

The program take a look to file system recursively, identifying all the files and creating an object that contains your path and one hash created based on content byte-to-byte, after that the program compare each one object searching for same hashes.

## Run

Get download of DFE.jar(compiled with Java 17) or create a runnable jar by the source code.

Type on terminal:

```bash
java -jar DFE.jar [path to explore]
```
you can type dot(.) for searching on working directory or pass a valid directory path.

## Install - Linux only

Get download of DFE.jar or create a runnable jar by the source code.

Move the runnable jar to /opt directory

create a file on /usr/bin named *duplicated-file-explorer* as content below:

```script
#!/bin/sh
java -jar /opt/DFE.jar $@
```
Make a executable file with:

```bash
sudo chmod +x duplicated-file-explorer
```

Now you can type on your terminal:

```bash
duplicated-file-explorer [path to explore]
```
