package com.sheu.aoc.util

import java.nio.file.Files
import java.nio.file.Paths

fun readEntireFile(path: String): List<String> = Files.readAllLines(Paths.get(path))