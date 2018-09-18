This is a Spring Boot project managed with Apache Maven.

We used the **Google Cloud Natural Language API** for analyzing policy texts:

- [Quickstart](https://cloud.google.com/natural-language/docs/quickstart)
- [Samples from the docs](https://cloud.google.com/natural-language/docs/samples)
- [Java samples for Google Cloud](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/language/)

You will need to obtain a developer key from the Cloud API console to use our backend. Put the resulting JSON formatted file in your file system, and use the `GOOGLE_APPLICATION_CREDENTIALS` environment variable to specify it's location, e.g.:

`export GOOGLE_APPLICATION_CREDENTIALS=./gclangapi.json`

Then run

```
mvn compile
```

And then

```
mvn spring-boot:run
``` 
