# PegelOnlineWaterLevelImporter

- Importer for internal and coastal levels of waterways of Germany
- No need for url (imports the latest measurements automatically)

-----------------------------
## Components
- Data Source
- Batch Jobs
  - Step
      - Read
      - Process
      - Write
- Data Model
- Data Producer for Kafka Queue

-------------------------------

## Running:

Inside the root directory of the project run the commands:
```
$ cd application
$ mvn spring-boot:run
```

## Building

For building run the following command either inside the root directory of the project, or inside the 'application' directory:
```
$ mvn clean install
```
The generated jar file is inside application/target directory.

--------------------------------

## Use the docker

#### Pulling the image:

```sh
$ docker pull ahmadjawidjami/pegel_online_importer
```
#### Running with sample environment variables:

```sh
$  docker run \
--env "KAFKA_BOOTSTRAP_SERVERS=localhost:9092" \
--env "KAFKA_TOPIC=waterLevel" \
ahmadjawidjami/pegel_online_importer
```
#### Mandatory enviroment variables:

- KAFKA_BOOTSTRAP_SERVERS

#### Optional environment variable

- KAFKA_TOPIC &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; //Default is 'waterLevel'

