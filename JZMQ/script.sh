#!/bin/bash

javac -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar -d bin *.java

gnome-terminal -- bash -c 'java -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar:bin Operador_01; exec bash'
gnome-terminal -- bash -c 'java -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar:bin Operador_02; exec bash'
gnome-terminal -- bash -c 'java -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar:bin Broker; exec bash'
gnome-terminal -- bash -c 'java -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar:bin Servidor; exec bash'
gnome-terminal -- bash -c 'java -cp /usr/share/java/zmq-3.1.0.jar:/usr/share/java/jzmq-3.1.0.jar:/usr/share/java/jzmq.jar:/usr/share/java/zmq.jar:bin Cliente; exec bash'

