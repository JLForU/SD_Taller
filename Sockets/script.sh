#!/bin/bash

javac -d bin *.java

gnome-terminal -- bash -c 'java -cp bin Servidor; exec bash'
gnome-terminal -- bash -c 'java -cp bin Operador_01; exec bash'
gnome-terminal -- bash -c 'java -cp bin Operador_02; exec bash'
gnome-terminal -- bash -c 'java -cp bin Cliente; exec bash'
