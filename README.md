# Cron Parser

### Test application

```bash
./gradlew test
```

## Run application 

### Docker

Verify that on machine working docker - execute below command 

```bash
docker --version
```

if there is not you should install it 
1. MAC - https://docs.docker.com/desktop/install/mac-install/
2. Linux - https://docs.docker.com/desktop/install/linux-install/
3. Windows - https://docs.docker.com/desktop/install/windows-install/

or go into Local Environment point

Build new image 
```bash
docker build -t shift4 . 
``` 

Run application
```bash
docker run -it -e APP=DOCKER shift4
```

### Local Environment

Build application 
```bash
./gradlew build
```

Run application
```bash
java -jar build/libs/cron.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

### IDE like IntelliJ

Configure runner like that 

https://github.com/harisford/shift4/assets/10939118/341bf62a-a7de-4312-8d69-41ebfc5521fa

