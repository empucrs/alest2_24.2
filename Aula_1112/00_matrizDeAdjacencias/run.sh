if ! [ -e ./bin ]; then
  mkdir bin
fi;

javac ../../00_SedgewickCode/src/*.java -d ../../00_SedgewickCode/bin
javac -cp ../../00_SedgewickCode/bin/.:./. App.java
echo "consumindo o arquivo "$1
java -cp ../../00_SedgewickCode/bin/.:./. App $1
