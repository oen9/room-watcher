# room-watcher

## backend
`sbt backend/run -Dapp.port=9000`

`sbt backend/assembly`

`sbt compile stage`

`java -jar -Dapp.port=9000 backend/target/scala-2.12/rw-backend.jar`


## frontend

```
sbt
project frontend
~fastOptJS
```
