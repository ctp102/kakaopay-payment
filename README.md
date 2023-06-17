1. 결제하기
   1. 결제 시도 로그
   2. 결제 성공 로그
   3. 결제 실패 로그
   4. 결제 취소 로그
2. 결제내역 조회
   1. 시도/성공/실패/취소


--------
## 카카오페이 결제 이해하기
https://developers.kakao.com/docs/latest/ko/kakaopay/common

## 카카오페이 단건결제
https://developers.kakao.com/docs/latest/ko/kakaopay/single-payment

- Admin Key
8a3d390b4aeeefa6411d87fd6102121e

테스트 결제 가맹점 코드(CID)
TC0ONETIME

결제 준비하기
https://kapi.kakao.com/v1/payment/ready
Authorization: KakaoAK ${SERVICE_APP_ADMIN_KEY}
Content-type: application/x-www-form-urlencoded;charset=utf-8
응답은 JSON으로 받음

https://kapi.kakao.com/v1/payment/approve
