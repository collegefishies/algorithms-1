# Makefile for Java project

# Define the Java compiler and the source files directory
JAVAC = javac
SRC_DIR = src

# Define the build directory
BUILD_DIR = build

# Default target for compiling your Java source files
all:
	$(JAVAC) -d $(BUILD_DIR) $(SRC_DIR)/*.java

# Clean target for removing all .class files and "out" directories recursively
clean:
	find . -name "*.class" -type f -delete
	find . -name "out" -type d -exec rm -rf {} +
