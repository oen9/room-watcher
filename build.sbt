lazy val stage = taskKey[Unit]("Compile for heroku'")

stage := (assembly in backend).value
