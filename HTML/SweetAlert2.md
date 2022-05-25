# 목적
- 이번 항해99를 하면서 전에 다른프로젝트에 중간 투입 되었을 때 써봤던 SweetAlert이 기억나 접목시키고싶어서 알아보았고 다시 알아보는 시간을 줄이기위해 정리하게되었다,

#SweetAlert2 설치
-install
```
npm install sweetalert2
```
-CDN
```
<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
```

#SweetAlert2 사용법
- 사용방법을 하나씩 적을까도 했지만 공식페이지에서 직접사용해볼 수 있게 잘되어있어서 확인해보는게 좋을거 같다 .
- 기본alert 외에도 정말 많은 alert이 있으니 페이지 전체를 
[SweetAlert2](https://sweetalert2.github.io/#download)

# 현재 자주 사용하는 js
![1](https://user-images.githubusercontent.com/81284265/170153622-739655f3-e710-4e9c-948f-3baa5a487f7b.png)  

```
 Swal.fire("massage");
```

![2](https://user-images.githubusercontent.com/81284265/170153635-3a7ae3e4-a1a9-4c8f-ae68-3a5a8073204c.png)

```
Swal.fire({
  title: 'Do you want to save the changes?',
  showDenyButton: true,
  showCancelButton: true,
  confirmButtonText: 'Save',
  denyButtonText: `Don't save`,
}).then((result) => {
  /* Read more about isConfirmed, isDenied below */
  if (result.isConfirmed) {
    Swal.fire('Saved!', '', 'success')
  } else if (result.isDenied) {
    Swal.fire('Changes are not saved', '', 'info')
  }
})
```

  
![ajax](https://user-images.githubusercontent.com/81284265/170153671-b996df03-0425-429d-a60d-63c4c27af270.png)

```
Swal.fire({
  title: 'Submit your Github username',
  input: 'text',
  inputAttributes: {
    autocapitalize: 'off'
  },
  showCancelButton: true,
  confirmButtonText: 'Look up',
  showLoaderOnConfirm: true,
  preConfirm: (login) => {
    return fetch(`//api.github.com/users/${login}`)
      .then(response => {
        if (!response.ok) {
          throw new Error(response.statusText)
        }
        return response.json()
      })
      .catch(error => {
        Swal.showValidationMessage(
          `Request failed: ${error}`
        )
      })
  },
  allowOutsideClick: () => !Swal.isLoading()
}).then((result) => {
  if (result.isConfirmed) {
    Swal.fire({
      title: `${result.value.login}'s avatar`,
      imageUrl: result.value.avatar_url
    })
  }
})
```
```
