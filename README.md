# daily_app
|종류|내용|api|request|reponse|상태코드|
|------|-----|------|-----|------|------|
|일정 작성|POST|/api/daily|요청 body|등록정보|200:정상등록|
|일정 조회|PUT|/api/daily|요청 param|단건 응답정보|200:정상조회|
|일정 수정|GET|/api/daily/{id}|요청 body|수정 정보|200:정상수정|
|일정 삭제|DELETE|/api/daily/{id}|요청 param||200:정상삭제|

ERD
|일정||
|-----|-----|
|활동|VarChar|
|ID|INT|
|name|VarChar|
|password|VarChar|
|date|DateTime|
|date-modify|DateTime|

sql

CREATE TABLE Daily_app
(	
	activity varchar(100),
  id INT AUTO_INCREMENT PRIMARY KEY,
	name varchar(100) ,
	password varchar(100),
  date DateTime,
  date-modify Datetime,
);
