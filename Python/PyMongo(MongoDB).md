## PyMongo Tutorial
- PyMongo 라이브러리를 통해 Python으로 MongoDB를 이용할 수 있다. 우선, MongoDB를 로컬이나 클라우드 환경에 설치한 다음 PyMongo의 MongoClient 메서드를 이용해 DB에 연결할 수 있다.

## PyMongo 설치
- CMD를 열어 아래 명령어를 실행하면 PyMongo가 설치된다.
```
pip install pymongo
```

## DB연결 - MongoClient
- MongoDB에 연결하는 방법은 두 가지가 있다. 
> 첫 번째 방법은 MongoClient의 값으로 MongoDB 서버 URI를 파라미터로 입력하는 것이다  
> 두 번째 방법은 MongoDB 서버의 호스트와 포트 각각의 값을 파라미터로 입력하는 것이다. 

```
from pymongo import MongoClient

# 방법1 - URI
# mongodb_URI = "mongodb://localhost:27017/"
# client = MongoClient(mongodb_URI)

# 방법2 - HOST, PORT
client = MongoClient(host='localhost', port=27017)

print(client.list_database_names())
```

## DB 접근
```
db = client.mydb
```

## Collection 접근
```
collection = db.myCol
```

## 기본 문법 및 예시
```
from pymongo import MongoClient
client = MongoClient('url')
db = client.dbsparta


# 저장 - 예시
doc = {'name':'bobby','age':21}
db.users.insert_one(doc)

# 한 개 찾기 - 예시
user = db.users.find_one({'name':'bobby'})

# 여러개 찾기 - 예시 ( _id 값은 제외하고 출력)
all_users = list(db.users.find({},{'_id':False}))

# 바꾸기 - 예시
db.users.update_one({'name':'bobby'},{'$set':{'age':19}})

# 지우기 - 예시
db.users.delete_one({'name':'bobby'})
```





