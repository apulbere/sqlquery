# How to use
* Config log level
```
curl -X POST "http://localhost:8085/actuator/loggers/org.hibernate.SQL" -H 'Content-Type: application/json' -d '{"configuredLevel": "DEBUG"}'
curl -X POST "http://localhost:8085/actuator/loggers/org.hibernate.type.descriptor.sql.BasicBinder" -H 'Content-Type: application/json' -d '{"configuredLevel": "TRACE"}'
```

* Copy logs in resources/params.log and resources/query.sql