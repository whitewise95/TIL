
## jinja2 란?
- 파이썬에서 많이 사용하는 템플릿엔진이다.
- 템플릿을 렌더링하는 태스크를 위해, Flask는 Jinja2라는 강력한 템플릿 엔진을 사용한다
  >템플릿? 
  >>응답 텍스트를 포함하고 있는 파일 (밑에서 자세히 설명)<br>
  >>동적 파트에 대한 변수를 포함한다

  >렌더링?
  >>변수들을 실제 값으로 바꾸고 최종 응답 문자열을 리턴하는 프로세스
<br>
<br>

## 솔직히 말해서 무슨 말인지 절대 모르겠다.... 똑똑한 사람들만 알아들을 수 있는 설명?!
- 내가 jinja2를 사용할 때 이해한 점
> 1. HTML이 로딩되고 바로 보여줘야하는 데이터를 서버와 통신해 받아야한다면 비동기통신(대표로 ajax)을 이용할 것이다. 그 통신간에 시간이 길어서 빈화면이 보여질 수도있는것이다. 하지만 이 jinja2를 이용하면 HTML보여질 때 데이터도 이미 로딩이 되어 빈화면이 보여지거나 보여지면 부자연스러운부분을 커버할 수 있다.  
> 2. 서버에서 내린 데이터를 편하게 사용할 수 있다.

## jinja2는 서버 사이드 렌더링이다.
- 서버사이드렌더링이란?
> 서버에서 페이지를 그려 클라이언트(브라우저)로 보낸 후 화면에 표시하는 기법을 의미합니다.

## 문법

-서버에서 데이터 보내기
> list를  render_template()를 메소드를 이용해 list라는 변수에 담아(list=list) html를 렌더링한다.
```
@app.route('/') 
def main():
  list = {
      name : '현명',
      age : 20,
      alive : True
  }
	return render_template("index.html" , list=list )
```  
- 만약 리스트 일경우  
```
{% for list in post %}
                {% set name = list['name'] %}
                {% set date = list['date'] %}
                {% set image = list['image'] %}
                {% set sleep = list['sleep'] %}
                {% set poop = list['poop'] %}
                {% set condition = list['condition'] %}
                {% set feeding = list['feeding'] %}
                {% set another = list['another'] %}
                {% set file = list['file'] %}
{% endfor %}
```  
-  조건문
```
{% if list.alive %}
    <h5 id="pronunciation" style="display: inline;">/{{ result.pronunciation }}/</h5>
{% endif %}


{% if list.age == 20 %}
    <h5 id="pronunciation" style="display: inline;">{{ list.age }}</h5>
{% else %}
    <h5 id="pronunciation" style="display: inline;"></h5>
{% endif %}

```
-자바스크립트  (따음표 주의하자!)
```
funtion printAge(){
   let age = '{{list.age}}';
   console.log(age);
}
```





