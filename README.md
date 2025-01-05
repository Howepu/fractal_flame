![image](https://github.com/user-attachments/assets/e9ece770-48a5-4992-9bad-6e217fa523d9)# ❤️‍🔥 Fractal Flame

![img.png](readme_img/img.png)

Fractal flame is an algorithm proposed by Scott Draves that uses iterated function systems (IFS) to build images. 

Due to different seed values for the pseudorandom number generator, many different “pictures” can be obtained. Although fractality is not always visible in them, the results are very interesting.

<a href="https://flam3.com/flame_draves.pdf">More info</a>

## Features

- Single-thread and multy-thread image rendering
- Symmetrical rendering
- Image transformation types: (Disc, Heart, Linear, Polar, Sinusoidal, Spherical)
- Image normalization

## Build and run

Build program
```shell
mvn clean package assembly:single
```

Generate one image
```shell
java -jar target/flame-jar-with-dependencies.jar --generate
```

Generate many images and get benchmark_report.md
```shell
java -jar target/flame-jar-with-dependencies.jar --generate
```

See other flags
```shell
java -jar target/flame-jar-with-dependencies.jar
```

## Examples

Circular
![image](https://github.com/user-attachments/assets/3d172036-99af-4a92-bac5-2001973995c8)
Cross
![image](https://github.com/user-attachments/assets/2a6b26e7-0661-47eb-8ee8-7c338969e72a)
Eyefish
![image](https://github.com/user-attachments/assets/d4bee2b7-f8e6-435f-84d3-0f949ede66d4)
Heart
![image](https://github.com/user-attachments/assets/8db25ffd-8849-47b5-94a2-a029c4a82212)
Swirl
![image](https://github.com/user-attachments/assets/98239480-9f64-4445-a727-118a3c66b57b)
Tangent
![image](https://github.com/user-attachments/assets/0d1cab50-c80d-46cb-99a9-d72cd92e8a36)
