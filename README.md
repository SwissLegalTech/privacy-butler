# Privacy Butler
Many people like you and I feel that we are unable to cope with all the various privacy notices we have to deal with on a daily basis. Our data is important to us, and indeed defines our online identity.

Your Privacy Butler will help you to understand any privacy notices. Simply tell Privacy Butler which data processing is a “no go” for you. It converts the privacy notice into icons that show you immediately whether your desired data protection standard is met or not.

The original idea can be found
[here](https://github.com/SchweizerischeBundesbahnen/legal-hackathon-2018/blob/master/SBB_Hackathon2018_PrivacyButler.pdf).

## Demo
You can see a working demo here:
https://github.com/SwissLegalTech/privacy-butler/blob/master/privacy-butler-demo.gif

### Backend
The backend uses Java with Spring Boot 2 and communicates with the Google Cloud Natural Language API.
You have to create the credentials yourself in order to be able to communicate with Google Cloud.

You can find the backend project files here:
https://github.com/SwissLegalTech/privacy-butler/tree/master/legal-hackathon-backend

### Frontend
The frontend uses Typescript with Angular 6 and Material Design as a styling framework addition to Angular.

You can find the frontend project files here:
https://github.com/SwissLegalTech/privacy-butler/tree/master/legal-hackathon-frontend

## Resources
These resources may help you get started:

### Google Cloud Natural Language API
- [Quickstart](https://cloud.google.com/natural-language/docs/quickstart)
- [Samples from the docs](https://cloud.google.com/natural-language/docs/samples)
- [Java samples for Google Cloud](https://github.com/GoogleCloudPlatform/java-docs-samples/tree/master/language/)

### IBM Watson
See https://www.ibm.com/watson/services/natural-language-understanding/

### Open NLP by Apache
See https://opennlp.apache.org/

### Further resources (without any particular order)
- http://fredrikstenbeck.com/google-natural-language-vs-watson-natural-language-understanding/
- https://tosdr.org/
- https://pribot.org/
- https://chrome.google.com/webstore/detail/privacycheck/poobeppenopkcbjejfjenbiepifcbclg?hl=en-US
- https://usableprivacy.org/data/
- https://medium.com/datathings/the-magic-of-lstm-neural-networks-6775e8b540cd
- https://github.com/ar-ms/lstm-mnist
- https://openscience.adaptcentre.ie/projects/GDPRtEXT/
- https://www.w3.org/community/dpvcg/
- https://ebiquity.umbc.edu/resource/html/id/370/Ontology-for-Data-Privacy-Policy
- http://www.semantic-web-journal.net/content/improving-readability-online-privacy-policies-through-doop-domain-ontology-online-privacy