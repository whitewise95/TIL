## Flask란?
- Flask는 웹 애플리케이션 개발을 위한 파이썬 프레임워크다. (자바의 Spring 같은 개념?!)
- Flask 정보를 얻기 위해 서치중에 발견한 글이다.

> 물론 파이썬으로 웹 개발을 할 때 많이 쓰이는 프레임워크로 가장 유명한 것은 Django(장고)인데, Django는 너무 무겁고 기능이 많아서 복잡하다. 나처럼 웹에 대한 이해가 풍부하지 않은 초보 입장에서 사용하기 어렵다는 뜻이기도 하다. 그래서 내가 필요한 기능만 최대한 라이트하게 개발을 하기 위해서는 Flask처럼 가벼운 프레임워크가 딱이다.  

## Flask 설치
```
pip install Flask
```  

## Flask로 Hello, World!  
```
from flask import Flask

app = Flask(__name__)

@app.route('/')
def home():
    return 'Hello, World!'

if __name__ == '__main__':
    app.run(debug=True)
```  
- 확인 주소는 localhost:5000/ 로 확인할 수 있다.
- app.run() 괄호 안에 debug=True라고 명시하면 해당 파일의 코드를 수정할 때마다 Flask가 변경된 것을 인식하고 다시 시작한다.  

## Route (URL에 웹페이지 연결하기)
- @app.route(/home) 데코레이터를 사용한다. 자바의 애노테이션같은 느낌이다.
```
@app.route('/hello')
def home():
    return 'Hello, World!'
```
- 이번에는 route에 /home 으로 정의해줬으니 localhost:5000/hello 로 들어가야 확인할 수 있다.

## 동적 URL 다루는 방법
- <> 안에 매개변수처럼 받고싶은 변수명을 적으면  localhost:5000/user/sam/1515 라는 url로 넘겨 sma과 1515 받을수 있다.
- URL은 문자열이기 때문에 <converter : variable_name> 구문을 사용해서 URL에서 받은 변수 타입을 변형할 수 있다. converter로 사용할 수 있는 옵션은 아래와 같다 
- 반환되는 값으로 f-string 포맷을 활용해서 문자열에 변수를 넣고 Html에 적용 시킬 수 있다.
[f-string 포맷이란?]()
```
@app.route('/user/<user_name>/<int:user_id>')
def user(user_name, user_id):
    return f'Hello, {user_name}({user_id})!'
``` 

## HTML 렌더링
- @app.route()아래 적어주는 뷰 함수에서 return하는 응답은 일반 텍스트, 데이터 등 다양한 형식이 될 수 있는데, 일반적으로는 웹 페이지에서 렌더링 할 HTML을 직접 반환하게 될 거다.
```
@app.route('/')
@app.route('/home')
def home():
    return '''
    <h1>이건 h1 제목</h1>
    <p>이건 p 본문 </p>
    <a href="https://flask.palletsprojects.com">Flask 홈페이지 바로가기</a>
    ''' 
```




