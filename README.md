# EntroPay Rates Retrieval Assessment

*EntroPay deals with money on a daily basis and, like any other financial institution, deals with foreign exchange rates between currencies*.

*Implementation of services which reads, parses and stores foreign exchange rates off a standard text file into a relational database. These rates would eventually be retrieved via a REST API*.


## Database

Postgres 9.3 is used with following credentials:

Database Name: **RatesDB**

Username: **postgres**

Password: **hajjcoreit**


### Database Schema

CREATE TABLE rate

(

  id integer NOT NULL DEFAULT nextval('"RATE_id_seq"'::regclass),

  file text,

  buycurrency text,

  sellcurrency text,

  "timestamp" time with time zone,

  rate double precision,

  CONSTRAINT "RATE_pkey" PRIMARY KEY (id)

)

WITH (

  OIDS=FALSE

);

ALTER TABLE rate

  OWNER TO postgres;


## Rest APIs 

### REST API Layer
The REST API implementation having following functionality:


- despatch the aforementioned job which will fetch, parse, and save rates from the file using following api call

**http://localhost:8080/RatesRetrievalService/rservice/processRates**


- the ability to fetch a list of rates

**http://localhost:8080/RatesRetrievalService/rservice/getRates**


- the ability to fetch a list of rates with the option to filter by date. date should be 8-character long value having yyyyMMdd format

**http://localhost:8080/RatesRetrievalService/rservice/getRates/20160104**

