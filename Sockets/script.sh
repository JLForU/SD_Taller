#!/bin/bash

javac *.java

gnome-terminal -- bash -c 'java Servidor; exec bash'
gnome-terminal -- bash -c 'java Operador_01; exec bash'
gnome-terminal -- bash -c 'java Operador_02; exec bash'
gnome-terminal -- bash -c 'java Cliente; exec bash'
