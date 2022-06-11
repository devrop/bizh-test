# bizh-test
#Filling item microservice 
a project about recording transactions for storing items in slots


Java 8 & higher
Framework Spring 5 (Spring boot 2.x.x version)
Database : hsql inMemory
Build tool : maven

How to Build project
mvn clean install /path/to/project

Run project 
jar path/{application}.jar


order of running project
1 Run config project
2 Run eureka project
3 Run gateway project
4 Run rack project
5 Run box project
6 Run slot project
7 Run filling-item project

Test
create rack
body 

{
	
	"rackCode" : 1,
	"rackName" : "Rack satu"
}
contentTtype = applications/json
Method Post 
url http://localhost:8888/api/racks/save
result 

{
    "status": 0,
    "message": "OK",
    "totalRecords": 0,
    "contents": null
}


1). create box
contentTtype = applications/json
Method Post 

{
  "boxCode": 1,
  "boxName": "box 1",
  "rackCode": 1
}

contentTtype = applications/json
Method Post 
http://localhost:8888/api/boxs/save

{
    "status": 0,
    "message": "OK",
    "totalRecords": 0,
    "contents": null
}

2). create slot
body

{
  "slotCode": 2,
  "slotName": "slot 2",
  "boxCode": 1,
  "rackCode": 1,
  "slotCapacity": 10,
  "usedQty": -1
}

contentTtype = applications/json
Method Post 
3) create filling item
url http://localhost:8888/api/fillings/input
contentTtype = applications/json
Method Post 

{
  "boxCode": 0,
  "boxName": "string",
  "details": [
    {
      "itemName": "item 1",
      "trxNo": "string"
    }
  ],
  "fillType": "string",
  "qty": 1,
  "rackCode": 0,
  "rackName": "string",
  "slotCode": 2,
  "slotName": "string",
  "trxName": "test 1",
  "trxNo": "string"
}

4) get data detail created
url http://localhost:8888/api/fillings/detailTrx
response
{
  "trxNo": "ijkqsxbjhn",
  "trxName": "test 1",
  "slotCode": 2,
  "slotName": "slot 2",
  "boxCode": 1,
  "boxName": "box 1",
  "rackCode": 1,
  "rackName": "rack 1",
  "qty": 1,
  "fillType": "I",
  "details": [
    {
      "trxNo": "ijkqsxbjhn",
      "itemName": "item 1"
    }
  ]
}

All Api Doc can acces at {uri-project}/swagger-ui.html
All config can see at https://github.com/devrop/bizh-test/tree/master/config-files