## PyJWT 설치
```
pip install pyjwt
```

## 토큰을 만드는 예제
```
@app.route('/sign_in', methods=['POST'])
def sign_in():
    # 로그인
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']

    pw_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    result = db.users.find_one({'username': username_receive, 'password': pw_hash})

    if result is not None:
        payload = {
         'id': username_receive,
         'exp': datetime.utcnow() + timedelta(seconds=60 * 60 * 24)  # 로그인 24시간 유지
        }
        token = jwt.encode(payload, SECRET_KEY, algorithm='HS256').decode('utf-8')

        return jsonify({'result': 'success', 'token': token})
    # 찾지 못하면
    else:
        return jsonify({'result': 'fail', 'msg': '아이디/비밀번호가 일치하지 않습니다.'})
```

## 토큰을 이용해 해독하는 예제  

```
@app.route('/main-page/<genre>')
def mainPage(genre):
    try:
        token_receive = request.cookies.get('mytoken')  //쿠키에 원하는 키로 데이터를 가져올 수 있다.
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")
```

## ubuntu환경에서 에러 
- ec2 ubuntu환경에서 이런에러가 발생했다.
- date타입같은건 직열화할 때 잘 안되는 경우가 있다고한다.
```
default raise typeerror(f'object of type {o.__class__.__name__} ' 
typeerror: object of type bytes is not json serializable
```
- 에러가 터진 코드  
```
@app.route('/sign-in', methods=['POST'])
def sign_in():
    # 로그인
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']

    pw_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    result = db.users.find_one({'id': username_receive, 'password': pw_hash})

    if result is not None:
        payload = {
            'id': username_receive,
            'exp': datetime.utcnow() + timedelta(seconds=60 * 60 * 24)  # 로그인 24시간 유지
        }
        token = jwt.encode(payload, SECRET_KEY, algorithm='HS256')

        return jsonify({'result': 'success', 'token': token})
    # 찾지 못하면
    else:
        return jsonify({'result': 'fail', 'msg': '아이디/비밀번호가 일치하지 않습니다.'})

```
- return jsonify({'result': 'success', 'token': token}) 이분분에서 에러가 발생했다.
- 이미 토큰으로 인코딩된 상태라 왜 직열화를 못시켰는지 아직 이해하진 못했다.
```
token = jwt.encode(payload, SECRET_KEY, algorithm='HS256').decode('utf-8'); //  ".decode('utf-8')" 추가로 해결하였다.
```






