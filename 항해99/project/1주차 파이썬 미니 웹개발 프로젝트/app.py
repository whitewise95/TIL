import datetime
import hashlib
from datetime import datetime, timedelta

import jwt
from flask import Flask, render_template, jsonify, request, redirect, url_for
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient('mongodb+srv://test:sparta@sparta.rffmf.mongodb.net/sparta?retryWrites=true&w=majority')
db = client.lyrics_guide

SECRET_KEY = 'SPARTA'


@app.route('/')
def home():
    msg = request.args.get("msg")
    return render_template('login.html', msg=msg)


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


@app.route('/sign-up/save', methods=['POST'])
def sign_up():
    username_receive = request.form['username_give']
    password_receive = request.form['password_give']
    password_hash = hashlib.sha256(password_receive.encode('utf-8')).hexdigest()
    doc = {
        "id": username_receive,  # 아이디
        "password": password_hash,  # 비밀번호
    }
    db.users.insert_one(doc)
    return jsonify({'result': 'success'})


@app.route('/sign-up/check-dup', methods=['POST'])
def check_dup():
    username_receive = request.form['username_give']
    exists = bool(db.users.find_one({"id": username_receive}))
    return jsonify({'result': 'success', 'exists': exists})


@app.route('/main-page/<genre>')
def mainPage(genre):
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
        if user is None:
            return render_template("login.html")
        if genre == "all":
            lyrics = list(db.board.find({}, {'_id': False}))
        else:
            lyrics = list(db.board.find({'genre': genre}, {'_id': False}))
        return render_template("mainPage.html", lyrics_arr=lyrics, id=user['id'], genre=genre)
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route('/lyrics/<num>')
def write_lyrics(num):
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
        if user is None:
            return render_template("login.html")
        else:
            if num == 'new':
                lyrics = []
                return render_template("detail.html", lyrics=lyrics)
            else:
                lyrics = db.board.find_one({'num': int(num)})

            return render_template("detail.html", lyrics=lyrics)
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route('/board/lyrics', methods=['POST'])
def lyrics_update():
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
        if user is None:
            return render_template("login.html")
        else:
            writer = payload["id"]

            subject_receive = request.form["subject_give"]
            genre_receive = request.form['genre_give']
            lyrics_receive = request.form["lyrics_give"]
            num = request.form["num_give"]

            new_doc = {
                "subject": subject_receive,
                "genre": genre_receive,
                "lyrics": lyrics_receive,
                "num": int(num)
            }
            db.board.update_one({'num': int(num)}, {'$set': new_doc})
        return jsonify({"result": "success", 'msg': '수정 완료'})
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route('/board/lyrics/<num>', methods=['POST'])
def lyrice_delete(num):
    token_receive = request.cookies.get('mytoken')
    payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
    user = db.users.find_one({'id': payload['id']})

    find_board = db.board.find_one({"writer": user['id'], "num":int(num)})

    if find_board is None:
        return jsonify({'result': 'fail', 'msg': f'글쓴이가 아닙니다.'})
    else:
        db.board.delete_one({"num": int(num)})
        return jsonify({'result': 'success', 'msg': f'삭제완료'})



@app.route("/lyrics", methods=["GET"])
def lyrics_get():
    lyrics_list = list(db.lyrics.find({}, {'_id': False}))
    return jsonify({'lyrics': lyrics_list})


@app.route("/lyrics", methods=["POST"])
def lyrics_post():
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
        if user is None:
            return render_template("login.html")
        else:
            subject_receive = request.form['subject_give']
            genre_receive = request.form['genre_give']
            lyrics_receive = request.form['lyrics_give']

            lyrics_list = list(db.board.find({}, {'_id': False}))
            num = len(lyrics_list) + 1

            doc = {
                'subject': subject_receive,
                'genre': genre_receive,
                'lyrics': lyrics_receive,
                'num': int(num),
                'writer': payload['id']
            }
            db.board.insert_one(doc)
            return jsonify({'msg': '등록 완료!'})
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route('/board/lyrics/<num>', methods=['GET'])
def user_check(num):
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})

        find_board = db.board.find_one({"writer": user['id'], "num": int(num)})

        if find_board is None:
            return jsonify({'result': 'fail', 'msg': '글쓴이가 아닙니다.'})
        else:
            return jsonify({'result': 'success'})
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route("/detail/comment/<num>", methods=["POST"])
def delete_comment(num):
    db.posts.delete_one({'num': int(num)})
    return jsonify({"result": "success", "msg": "삭제 완료"})


@app.route("/detail/comment", methods=["POST"])
def update_comment():
    num_receive = request.form["num_give"]
    comment_receive = request.form["comment_give"]
    db.posts.update_one({'num': int(num_receive)}, {'$set': {'comment': comment_receive}})
    return jsonify({"result": "success", "msg": "수정완료"})


@app.route("/detail/comment/new", methods=["POST"])
def post_comment():
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user_info = db.users.find_one({"id": payload["id"]})
        comment_receive = request.form["comment_give"]
        date_receive = request.form["date_give"]
        board_num_receive = request.form["board_num_give"]

        all_posts = list(db.posts.find({}, {'_id': False}))
        num = len(all_posts) + 1
        doc = {
            "id": user_info["id"],
            "comment": comment_receive,
            "date": date_receive,
            "num": num,
            "board_num": board_num_receive
        }

        db.posts.insert_one(doc)
        return jsonify({"result": "success", "msg": "댓글 완료"})
    except (jwt.ExpiredSigatureError, jwt.exceptions.DecodeError):
        return redirect(url_for("home"))


@app.route('/detail/<num>')
def detail(num):
    try:
        token_receive = request.cookies.get('mytoken')
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user = db.users.find_one({'id': payload['id']})
        if user is None:
            return render_template("login.html")
        else:
            lyrics = db.board.find_one({'num': int(num)})
            posts = list(db.posts.find({"board_num": num}))
            for post in posts:
                post["_id"] = str(post["_id"])
                post["count_heart"] = db.likes.count_documents({"post_id": post["_id"], "type": "heart"})
                post["heart_by_me"] = bool(
                    db.likes.find_one({"post_id": post["_id"], "type": "heart", "id": payload['id']})
                )
            return render_template("lyrics_detail.html", lyrics=lyrics, user=user['id'], posts=posts)
    except jwt.ExpiredSignatureError:
        return render_template("login.html")
    except jwt.exceptions.DecodeError:
        return render_template("login.html")


@app.route("/detail/comment/like", methods=["POST"])
def update_like():
    token_receive = request.cookies.get('mytoken')
    try:
        payload = jwt.decode(token_receive, SECRET_KEY, algorithms=['HS256'])
        user_info = db.users.find_one({"id": payload["id"]})
        post_id_receive = request.form["post_id_give"]
        type_receive = request.form["type_give"]
        action_receive = request.form["action_give"]
        doc = {
            "post_id": post_id_receive,
            "id": user_info["id"],
            "type": type_receive
        }
        if action_receive == "like":
            db.likes.insert_one(doc)
        else:
            db.likes.delete_one(doc)
        count = db.likes.count_documents({"post_id": post_id_receive, "type": type_receive})
        return jsonify({"result": "success", 'msg': 'updated', "count": count})
    except (jwt.ExpiredSigatureError, jwt.exceptions.DecodeError):
        return redirect(url_for("home"))


if __name__ == '__main__':
    app.run('0.0.0.0', port=5000, debug=True)
