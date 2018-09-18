# Privacy Butler

Many people like you and I feel that we are unable to cope with all the various privacy notices we have to deal with on a daily basis. Our data is important to us, and indeed defines our online identity.

Your Privacy Butler will help you to understand any privacy notices. Simply tell Privacy Butler which data processing is a “no go” for you. It converts the privacy notice into icons that show you immediately whether your desired data protection standard is met or not.

This project was started at the [Swiss Legal Tech](http://swisslegal.tech) 2018 hackathon in Zürich, Switzerland.
The original challenge idea can be found
[here](https://github.com/SchweizerischeBundesbahnen/legal-hackathon-2018/blob/master/SBB_Hackathon2018_PrivacyButler.pdf).

## Demo

You can see a screencast of the [working demo here](https://github.com/SwissLegalTech/privacy-butler/blob/master/privacy-butler-demo.gif).

### Backend

The backend uses Java with Spring Boot 2 and communicates with the Google Cloud Natural Language API.
You have to create the credentials yourself in order to be able to communicate with Google Cloud.

Find the backend project files here and instructions to get started in the [legal-hackathon-backend](./legal-hackathon-backend) folder.

### Frontend

The frontend uses Typescript with Angular 6 and Material Design as a styling framework addition to Angular.

You can find the frontend project files and build instructions in [legal-hackathon-frontend](./legal-hackathon-frontend).

## Data

We used the **Google Cloud Natural Language API** for analyzing policy texts:

- [Quickstart](https://cloud.google.com/natural-language/docs/quickstart)
- [Samples from the docs](https://cloud.google.com/natural-language/docs/samples)
- [Java samples for Google Cloud](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/language/)

You will need to obtain a developer key from the Cloud API console to use our current backend.

## Research

We ran a short machine learning classification experiment using an open dataset of opt-out policies from [usableprivacy.org](https://usableprivacy.org/data/) and the **[Keras.io](https://keras.io)** deep learning environment. The results can be seen in a [Python notebook](https://github.com/SwissLegalTech/privacy-butler/blob/master/ml/keras-test.ipynb) made with Jupyter, in the `ml` subfolder.

The dataset used in the experiment above was one of the ones recommended by [Pribot.org](https://pribot.org/), a project that was a major motivation for our work here. Many thanks to Dr. Harkous and Prof. Aberer for their feedback to our concept during the hackathon.

We also considered using [IBM Watson](https://www.ibm.com/watson/services/natural-language-understanding/) (see [Fredrik Stenbeck comparison](http://fredrikstenbeck.com/google-natural-language-vs-watson-natural-language-understanding/) - and [OpenNLP at Apache](https://opennlp.apache.org/).

Further reading list, in no particular order:

- https://tosdr.org/
- https://chrome.google.com/webstore/detail/privacycheck/poobeppenopkcbjejfjenbiepifcbclg?hl=en-US
- https://medium.com/datathings/the-magic-of-lstm-neural-networks-6775e8b540cd
- https://github.com/ar-ms/lstm-mnist
- https://openscience.adaptcentre.ie/projects/GDPRtEXT/
- https://www.w3.org/community/dpvcg/
- https://ebiquity.umbc.edu/resource/html/id/370/Ontology-for-Data-Privacy-Policy
- http://www.semantic-web-journal.net/content/improving-readability-online-privacy-policies-through-doop-domain-ontology-online-privacy
