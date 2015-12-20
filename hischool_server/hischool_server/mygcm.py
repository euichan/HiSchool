# -*- coding:utf-8 -*-
from gcm import GCM
gcm = GCM("AIzaSyDxhSbm0PVsM9Y2eMk9Bpq3773Esazj9f0")
#data = {'message': u'얘들아 내일까지 모콘 마감이란다!'}

#data = {'message': u'등교 마감시간입니다. 지각 학생을 체크하세요!'}

#data = {'message': u'박재민 학생이 등교를 시작합니다'}

data = {'message': u'숙제가 등록되었습니다. 확인하세요!'}
# JSON request
reg_ids = ['APA91bGc0zFtwbYW56BfzAf5P16GUzVx2HzdYL8PyJlk_OfEE4BVjrhpwlxBLXwcPdViVMSRxU1rNBLeb4Bfx49B1SErgVqbkmT9lrMyPp4kV2DMIccOfrgEmL9w7YPqQUnZTHp3QU6WnnzuEX6_wcTjk0i3e6tnyw']
response = gcm.json_request(registration_ids=reg_ids, data=data)

print response
