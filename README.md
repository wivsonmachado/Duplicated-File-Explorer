# Repeated File Explorer

This an experimental project for study purposes, the concept consists on explore all file system(based by root path) looking for repeated files.

## How it works?

The program take a look to file system recursively, identifying all the files and creating an object that contains your path and one hash created based on content byte-to-byte, after that the program compare each one object searching for same hashes.

## Run

Get download of RFE.jar(compiled with Java 17) or create a runnable jar by the source code.

Type on terminal:

```bash
java -jar RFE.jar [path to explore]
```
you can type dot(.) for searching on working directory or pass a valid directory path.

## Install - Linux only

Get download of RFE.jar or create a runnable jar by the source code.

Move the runnable jar to /opt directory

create a file on /usr/bin named *repeated-file-explorer* as content below:

```script
#!/bin/sh
java -jar /opt/RFE.jar $@
```
Make a executable file with:

```bash
sudo chmod +x repeated-file-explorer
```

Now you can type on your terminal:

```bash
repeated-file-explorer [path to explore]
```
