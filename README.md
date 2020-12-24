# flightfinder
This rest api allows user to find flights between two locations either by "browse flights" or by "browse dates" api. Users can also opt for email subscription to receive notifications when the flights fare has reduced below a certain limit and unsubscribe from this service anytime.

## How to configure & install

### Configuration

#### Database
This application requires a database to provide an option of subscription for notifications. Oracle database has been used for this application. However, you are free to use the database of your choice. Please update the following fields in [application.properties](https://github.com/singhalrahul0125/flightfinder/blob/master/src/main/resources/application.properties):
```
spring.datasource.url
spring.datasource.username
spring.datasource.password
spring.datasource.driver.class
```
#### Source API
This application uses [Skyscanner API](https://rapidapi.com/skyscanner/api/skyscanner-flight-search) to look for flights. An api key is already available in the program but if you want to use your own api key, you will need to [sign up](https://rapidapi.com/search/flight%20finder) in order to generate a new one. Once signed up, you will receive an api key. Please update the api key in the following field in [application.properties](https://github.com/singhalrahul0125/flightfinder/blob/master/src/main/resources/application.properties) :

```
sourceApi.apikey
```
### Installation

To install the application run the following command in the project directory:

```mvn clean install```

This command will create a `flightfinder-0.0.1-SNAPSHOT.jar`

To start the application, switch to the directory where jar file is located, and run the following command:

`java -jar flightfinder-0.0.1-SNAPSHOT.jar`

## API
### Browse Flights
For a given origin, destination and outbound date, this API will return all the available flights that match the search criteria. Inbound date, however, is optional. In place of origin and destination fields, the IATA code of respective airports must be filled.

#### Request

```
GET http://{host}:{port}/flightfinder/browseflights/{origin}/{destination}/{outbound date}/{inbound date}
```

##### Request parameters

| Parameter        | Type    | Required  |     Description  |
| :--------------- |:--------| :---------|:-----------------|
| origin           | String  |    yes    |IATA code of origin airport. For eg. DEL for Delhi, India.
| destination      | String  |    yes    |IATA code of destination airport. For eg. MUC for Munich, Germany
| outbound date    | String  |    yes    |date of travel in YYYY-MM-DD format. For eg. 2021-01-20
| inbound date     | String  |    no     |date of return in YYYY-MM-DD format. For eg. 2021-02-10


#### Sample Request and Response

##### Sample Request
```
curl --location --request GET 'http://localhost:8082/flightfinder/browseflights/DEL/MUC/2021-01-20'
```
##### Sample Response
```
[
    {
        "quoteID": 1,
        "minPrice": 661.0,
        "direct": false,
        "currency": "$",
        "airlines": [
            "British Airways"
        ]
    }
]
```

### Browse Dates
For a given origin and destination, this API will return all possible flights in the future irrespective of inbound and outbound dates. The results will be in ascending order of price. 

###Request
```
GET http://{host}:{port}/flightfinder/browsedates/{origin}/{destination}
```
##### Request parameters

| Parameter        | Type    | Required  |     Description  |
| :--------------- |:--------| :---------|:-----------------|
| origin           | String  |    yes    |IATA code of origin airport. For eg. DEL for Delhi, India.
| destination      | String  |    yes    |IATA code of destination airport. For eg. DEL for Delhi, India.

#### Sample Request and Response

##### Sample Request
```
curl --location --request GET 'http://localhost:8082/flightfinder/browsedates/DEL/MUC'
```
##### Sample Response
```
[
    {
        "quoteID": 1,
        "minPrice": 251.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-01-14T00:00:00",
        "airlines": [
            "Air India Express"
        ]
    },
    {
        "quoteID": 2,
        "minPrice": 259.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-02-26T00:00:00",
        "airlines": [
            "Air India Express"
        ]
    },
    {
        "quoteID": 3,
        "minPrice": 345.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-06-28T00:00:00",
        "airlines": [
            "Aeroflot"
        ]
    },
    {
        "quoteID": 4,
        "minPrice": 351.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2020-12-26T00:00:00",
        "airlines": [
            "GoAir"
        ]
    },
    {
        "quoteID": 5,
        "minPrice": 364.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-05-12T00:00:00",
        "airlines": [
            "Turkish Airlines"
        ]
    },
    {
        "quoteID": 6,
        "minPrice": 365.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-04-07T00:00:00",
        "airlines": [
            "Turkish Airlines"
        ]
    },
    {
        "quoteID": 7,
        "minPrice": 378.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-07-31T00:00:00",
        "airlines": [
            "Turkish Airlines"
        ]
    },
    {
        "quoteID": 8,
        "minPrice": 382.0,
        "currency": "$",
        "direct": false,
        "departureDate": "2021-03-12T00:00:00",
        "airlines": [
            "Turkish Airlines"
        ]
    },
    {
        "quoteID": 9,
        "minPrice": 469.0,
        "currency": "$",
        "direct": true,
        "departureDate": "2021-04-02T00:00:00",
        "airlines": [
            "Lufthansa"
        ]
    },
    {
        "quoteID": 10,
        "minPrice": 505.0,
        "currency": "$",
        "direct": true,
        "departureDate": "2021-03-12T00:00:00",
        "airlines": [
            "Lufthansa"
        ]
    },
    {
        "quoteID": 11,
        "minPrice": 582.0,
        "currency": "$",
        "direct": true,
        "departureDate": "2021-05-12T00:00:00",
        "airlines": [
            "Lufthansa"
        ]
    },
    {
        "quoteID": 12,
        "minPrice": 582.0,
        "currency": "$",
        "direct": true,
        "departureDate": "2021-06-14T00:00:00",
        "airlines": [
            "Lufthansa"
        ]
    },
    {
        "quoteID": 13,
        "minPrice": 582.0,
        "currency": "$",
        "direct": true,
        "departureDate": "2021-07-31T00:00:00",
        "airlines": [
            "Lufthansa"
        ]
    }
]
```

### Subscribe
Allows user to get notification via e-mail when the flights fare reduced below a certain threshold predefined by user.

###Request
```
POST http://{host}:{port}/flightfinder/subscribe/{origin}/{destination}/{desiredFare}
```

##### Request parameters

| Parameter        | Type    | Required  |     Description  |
| :--------------- |:--------| :---------|:-----------------|
| origin           | String  |    yes    |IATA code of origin airport. For eg. MUC for Munich, Germany.
| destination      | String  |    yes    |IATA code of destination airport. For eg. DEL for Delhi, India.
| desiredFare      | Double  |    yes    |Threshold fare in Us dollars ($) below which user wants to get notification.


##### Request body

| Parameter        | Type    |     Description  |
| :--------------- |:--------| :-----------------|
| email            | String  | Preferred email id of user to receive notifications.

#### Sample Request and Response

##### Sample Request
```
curl --location --request POST 'http://localhost:8082/flightfinder/subscribe/MUC/DEL/100' \
--header 'Content-Type: application/json' \
--data-raw '{
                "email": "rahul.singhal@gmail.com"
            }'
```
##### Sample Response
```
Email and preferences have been saved.
```

### Unsubscribe
Allows user to opt out from the notification service. 
User can unsubscribe in two ways. 

#### Unsubscribe email Id
In this case, all the preferences saved using the given email id will be deleted and no more notifications will be sent to user's email id. 

####Request
```
POST http://{host}:{port}/flightfinder/unsubscribe
```
###### Request body

| Parameter        | Type    |     Description  |
| :--------------- |:--------| :-----------------|
| email            | String  | Email id of the user to stop notifications.

###### Sample Request
```
curl --location --request POST 'http://localhost:8082/flightfinder/unsubscribe' \
--header 'Content-Type: application/json' \
--data-raw '{
                "email": "rahul.singhal@gmail.com"
            }'
```
###### Sample Response
```
Email has been successfully unsubscribed.
```

##### Unsubscribe only a specific saved preference
User has a choice to delete a specific preference by providing origin, destination and email id. In this case user will only be unsubscribed from the matching saved preference. User will still receive notifications from any other saved preferences.

#### Request
```
POST http://{host}:{port}/flightfinder/subscribe/{origin}/{destination}
```
###### Request body

| Parameter        | Type    |     Description  |
| :--------------- |:--------| :-----------------|
| email            | String  | Email id of the user to stop notifications.

###### Sample Request
```
curl --location --request POST 'http://localhost:8082/flightfinder/unsubscribe/MUC/DEL' \
--header 'Content-Type: application/json' \
--data-raw '{
                "email": "rahul.singhal@gmail.com"
            }'
```
###### Sample Response
```
Email has been successfully unsubscribed.
```
In both the cases, an error message will be displayed if the incorrect input parameters such as incorrect email id or incorrect origin name have been provided by the user.

