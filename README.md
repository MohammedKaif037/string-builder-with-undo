# UndoableStringBuilder - A StringBuilder that supports undo functionality using Memento Pattern
## Features: append, delete, insert, replace, reverse with automatic state snapshots before each mutation
## Usage: new UndoableStringBuilder("text").append("more").undo() // reverts to previous state
## Inspired from: https://github.com/openjdk/jdk/blob/master/src/java.base/share/classes/java/lang/StringBuilder.java
