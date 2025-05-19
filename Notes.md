## Add MASON to local repo
``` shell
mvn install:install-file \
  -Dfile=libs/mason.20.jar \
  -DgroupId=com.example.libs \
  -DartifactId=mason \
  -Dversion=20 \
  -Dpackaging=jar

```