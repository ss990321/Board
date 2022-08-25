# week04

<hr>

## API 명세서

기능 | Method |  URL | Request | Response
---|---|---|---|---|
회원가입 | POST | /api/member/signup | {<br>"nickname": "zintest",<br>"password": "zin123",<br>"passwordConfirm": "zin123"<br>} | {<br>"success": true,<br>"data": {<br>"id": 5,<br>"nickname": "zintest",<br>"createdAt": "2022-07-27T23:22:28.702489",<br>"modifiedAt": "2022-07-27T23:22:28.702489"<br>},<br>"error": null<br>}
로그인 | POST | /api/member/login | POST 
게시글 작성 | POST | /api/auth/posts | {<br>“title”:“title”,<br>“username”:“username”,<br>“contents”:“contents”,<br>“password”:“password”<br>} | {<br>"createdAt":"2022-08-18T11:45:52.611009",<br>"modifiedAt":"2022-08-18T11:45:52.611009",<br>"id":1,<br>"username":"username",<br>"contents":"contents",<br>"title":"title",<br>"password":"password"<br>}
게시글 전체 조회 | GET | /api/posts | | {<br>"id":1,<br>"createdAt":"2022-08-18T11:45:52.611009",<br>"modifiedAt":"2022-08-18T11:45:52.611009",<br>"title":"title",<br>"username":"username",<br>"contents": "contents"<br>}<br>{<br>"id":2,<br>"createdAt":"2022-08-18T03:53:17.715",<br>"modifiedAt":"2022-08-18T03:53:17.715",<br>"title":"title",<br>"username":"username",<br>"contents":"contents"<br>}
게시글 조회 | GET | /api/auth/posts/{id} | | {<br>"id":2,<br>"createdAt":"2022-08-18T03:53:17.715",<br>"modifiedAt":"2022-08-18T03:53:17.715",<br>"title":"title2",<br>"username":"username2",<br>"contents": "contents2",<br>}
게시글 수정 | PUT | /api/auth/posts/{id} | {<br>"title":"modifiedtitle",<br>"username":"modifiedusername",<br>"contents":"modifiedcontents",<br>"password":"modifiedpassword"<br>} | {<br>"createdAt":"2022-08-18T11:45:52.611009",<br>"modifiedtAt":"2022-08-18T04:01:32.353",<br>"id":1,<br>"username":"modifiedusername",<br>"contents":"modifiedcontents",<br>"title":"modifiedtitle",<br>"password":"modifiedpassword"<br>}
게시글 삭제 | DELETE | /api/posts/{id} | | 1
댓글 생성 | POST | /api/auth/comment | POST 
댓글목록조회 | GET | /api/comment/{id} | GET 
댓글 수정 | PUT | /api/auth/comment/{id} | PUT 
댓글 삭제 | DELETE | /api/auth/comment/{id} | DELETE
