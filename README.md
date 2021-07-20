## Some Docker commands for running tests on Selenoid

First we need to downloads "selenoid/vnc:chrome_85.0", "selenoid/video-recorder:latest-release" and "selenoid/chrome:85.0" docker images with the following commands.

```$ sudo docker pull selenoid/vnc:chrome_88.0```

```$ sudo docker pull selenoid/chrome:88.0```

```$ sudo docker pull selenoid/video-recorder:latest-release```

Go to project Directory and run selenoid image as a container

```$ sudo docker-compose up -d selenoid```

Go to project Directory and run selenoid-ui image as a container

```$ sudo docker-compose up -d selenoid-ui```

To open Selenoid UI navigate to the following URL “http://localhost:8084/”.      Check SSE and SELENOID are connected.

Go to project Directory and run tests-winners-net image as a container

```$ sudo docker-compose build tests-winners-net```
```$ sudo docker-compose up tests-winners-net```


### How to see the tests work?
Open “http://localhost:8084/” url and go to Stats tab. Click on “VNC.
