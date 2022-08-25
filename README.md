# week04

<hr>

## ERP 설계

![게시판 (3)](https://user-images.githubusercontent.com/110081578/186587358-fc9d3eff-2224-4995-adc4-d967538b1f54.png)



<hr>

## API 명세서

기능 | Method |  URL | Request | Response
---|---|---|---|---|
회원가입 | POST | /api/member/signup | {<br>"nickname": "zintest",<br>"password": "zin123",<br>"passwordConfirm": "zin123"<br>} | {<br>"success": true,<br>"data": {<br>"id": 5,<br>"nickname": "zintest",<br>"createdAt": "2022-07-27T23:22:28.702489",<br>"modifiedAt": "2022-07-27T23:22:28.702489"<br>},<br>"error": null<br>}
로그인 | POST | /api/member/login | {<br>"nickname": "zintest",<br>"password": "zin123"<br>} | {<br>"success": true,<br>"data": {<br>"id": 5,<br>"nickname": "zintest",<br>"createdAt": "2022-07-27T23:22:28.702489",<br>"modifiedAt": "2022-07-27T23:22:28.702489"<br>},<br>"error": null<br>}
게시글 작성 | POST | /api/auth/posts | {<br>“title”:“title”,<br>“contents”:“contents”,<br>} | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"title": "title",<br>"content": "content",<br>"author": "zintest",<br>"createdAt": "2022-07-28T00:10:57.293612",<br>"modifiedAt": "2022-07-28T00:10:57.293612"<br>},<br>"error": null<br>}
게시글 전체 조회 | GET | /api/posts | | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"title": "title",<br>"content": "content",<br>"author": "zintest",<br>"createdAt": "2022-07-28T00:10:57.293612",<br>"modifiedAt": "2022-07-28T00:10:57.293612"<br>},<br>"error": null<br>}
게시글 조회 | GET | /api/auth/posts/{id} | | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"title": "title",<br>"content": "content",<br>"author": "zintest",<br>"createdAt": "2022-07-28T00:10:57.293612",<br>"modifiedAt": "2022-07-28T00:10:57.293612"<br>},<br>"error": null<br>}
게시글 수정 | PUT | /api/auth/posts/{id} | {<br>"title": "제목입니다.2",<br>"content": "내용입니다.2"<br>} | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"title": "제목입니다.2",<br>"content": "내용입니다.2",<br>"author": "zintest",<br>"createdAt": "2022-07-28T00:10:57.293612",<br>"modifiedAt": "2022-07-28T00:10:57.293612"<br>},<br>"error": null<br>}
게시글 삭제 | DELETE | /api/posts/{id} | | {<br>"success": true,<br>"data": "delete success",<br>"error": null<br>}
댓글 생성 | POST | /api/auth/comment | {<br>"postId": 2,<br>"content": "댓글입니다."<br>} | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"author": "zintest",<br>"content": "댓글입니다.",<br>"createdAt": "2022-07-28T00:15:20.954309",<br>"modifiedAt": "2022-07-28T00:15:20.954309"<br>},<br>"error": null<br>} 
댓글목록조회 | GET | /api/comment/{id} | | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"author": "zintest",<br>"content": "댓글입니다.",<br>"createdAt": "2022-07-28T00:15:20.954309",<br>"modifiedAt": "2022-07-28T00:15:20.954309"<br>},<br>"error": null<br>} 
댓글 수정 | PUT | /api/auth/comment/{id} | {<br>"postId": 2,<br>"content": "댓글입니다.3"<br>} | {<br>"success": true,<br>"data": {<br>"id": 1,<br>"author": "zintest",<br>"content": "댓글입니다.3",<br>"createdAt": "2022-07-28T00:15:20.954309",<br>"modifiedAt": "2022-07-28T00:15:20.954309"<br>},<br>"error": null<br>} 
댓글 삭제 | DELETE | /api/auth/comment/{id} | | {<br>"success": true,<br>"data": "delete success",<br>"error": null<br>}
